import os
import requests
from bs4 import BeautifulSoup
from dotenv import load_dotenv
from sqlalchemy import create_engine, Column, Integer, String
from sqlalchemy.orm import sessionmaker, declarative_base

# 1. .env 파일로부터 환경변수 로드
load_dotenv(dotenv_path='.env')

DB_HOST = os.getenv("DB_HOST")
DB_PORT = os.getenv("DB_PORT")
DB_NAME = os.getenv("DB_NAME")
DB_USER = os.getenv("DB_USER")
DB_PASSWORD = os.getenv("DB_PASSWORD")

# 2. Database 연결 설정
DATABASE_URL = f"mysql+pymysql://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}"
engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

# 3. ORM 모델 정의 (Book 테이블)
class Book(Base):
    __tablename__ = "books"

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(255))
    price = Column(String(50))
    rating = Column(String(50))

# 테이블 생성
Base.metadata.create_all(bind=engine)

def scrape_books():
    db = SessionLocal()
    base_url = "https://books.toscrape.com/catalogue/page-{}.html"
    
    try:
        print("크롤링을 시작합니다...")
        for page in range(1, 6): # 1페이지부터 5페이지까지
            print(f"현재 {page} 페이지 크롤링 중...")
            url = base_url.format(page)
            response = requests.get(url)
            
            if response.status_code != 200:
                print(f"{page} 페이지를 불러오지 못했습니다.")
                continue
                
            soup = BeautifulSoup(response.text, 'html.parser')
            books = soup.select('article.product_pod')

            for b in books:
                # 데이터 추출
                title = b.h3.a['title']
                price = b.select_one('p.price_color').text
                # 별점 (class="star-rating Three" -> "Three" 추출)
                rating_classes = b.select_one('p.star-rating')['class']
                rating = rating_classes[1] if len(rating_classes) > 1 else "No Rating"

                # DB 저장을 위한 객체 생성
                new_book = Book(title=title, price=price, rating=rating)
                db.add(new_book)
            
            # 페이지별로 커밋 (한꺼번에 해도 되지만 페이지 단위로 안전하게 수행)
            db.commit()
            
        print("모든 데이터 저장 완료!")

    except Exception as e:
        db.rollback()
        print(f"에러 발생: {e}")
    finally:
        db.close()

if __name__ == "__main__":
    scrape_books()
