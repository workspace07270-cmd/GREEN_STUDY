import os
import requests
from bs4 import BeautifulSoup
from sqlalchemy import create_engine, Column, Integer, String, Float
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from dotenv import load_dotenv

# .env 파일 로드
load_dotenv()

# DB 접속 정보 가져오기
DB_HOST = os.getenv("DB_HOST")
DB_PORT = os.getenv("DB_PORT")
DB_NAME = os.getenv("DB_NAME")
DB_USER = os.getenv("DB_USER")
DB_PASS = os.getenv("DB_PASSWORD")

# SQLAlchemy 설정
# MySQL Connection String: mysql+pymysql://user:password@host:port/dbname
DATABASE_URL = f"mysql+pymysql://{DB_USER}:{DB_PASS}@{DB_HOST}:{DB_PORT}/{DB_NAME}"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

# ORM 모델 정의
class Book(Base):
    __tablename__ = "books"

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(255), nullable=False)
    price = Column(Float)
    availability = Column(String(50))
    rating = Column(String(20))

# 테이블 생성 (없을 경우)
Base.metadata.create_all(bind=engine)

def scrape_books():
    base_url = "https://books.toscrape.com/catalogue/page-{}.html"
    books_data = []

    print("크롤링 시작 (1~5페이지)...")
    
    for page in range(1, 6):
        url = base_url.format(page)
        response = requests.get(url)
        
        if response.status_code != 200:
            print(f"{page}페이지를 불러오는데 실패했습니다.")
            continue
            
        soup = BeautifulSoup(response.text, "html.parser")
        products = soup.select(".product_pod")

        for product in products:
            title = product.select_one("h3 a")["title"]
            # 가격 추출 (예: £51.77 -> 51.77)
            price_text = product.select_one(".price_color").text
            price = float(price_text.replace("£", "").replace("Â", ""))
            
            availability = product.select_one(".instock.availability").text.strip()
            
            # 별점 추출 (예: star-rating Three -> Three)
            rating_classes = product.select_one(".star-rating")["class"]
            rating = [c for c in rating_classes if c != "star-rating"][0]

            books_data.append(Book(
                title=title,
                price=price,
                availability=availability,
                rating=rating
            ))
        
        print(f"{page}페이지 완료")

    # DB 저장
    db = SessionLocal()
    try:
        db.add_all(books_data)
        db.commit()
        print(f"총 {len(books_data)}개의 도서 정보를 DB에 저장했습니다.")
    except Exception as e:
        print(f"DB 저장 중 오류 발생: {e}")
        db.rollback()
    finally:
        db.close()

if __name__ == "__main__":
    scrape_books()
