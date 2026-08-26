import os
from dotenv import load_dotenv
from sqlalchemy import create_engine, Column, Integer, String
from sqlalchemy.orm import sessionmaker, declarative_base

# 1. .env 파일로부터 환경변수 로드
# module3 디렉토리에 .env가 있으므로 경로를 지정합니다.
load_dotenv(dotenv_path='.env')

DB_HOST = os.getenv("DB_HOST")
DB_PORT = os.getenv("DB_PORT")
DB_NAME = os.getenv("DB_NAME")
DB_USER = os.getenv("DB_USER")
DB_PASSWORD = os.getenv("DB_PASSWORD")

# 2. Database 연결 설정 (pymysql 드라이버 사용)
DATABASE_URL = f"mysql+pymysql://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

# 3. Model 정의 (User 테이블)
class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(50))
    email = Column(String(100), unique=True)

# 테이블 생성 (없을 경우)
Base.metadata.create_all(bind=engine)

def crud_example():
    db = SessionLocal()
    try:
        # [C] Create - 데이터 추가
        new_user = User(name="홍길동", email="hong@example.com")
        db.add(new_user)
        db.commit()
        db.refresh(new_user)
        print(f"추가된 사용자: {new_user.name}, ID: {new_user.id}")

        # [R] Read - 데이터 조회
        user = db.query(User).filter(User.email == "hong@example.com").first()
        print(f"조회된 사용자: {user.name}")

        # [U] Update - 데이터 수정
        user.name = "홍길순"
        db.commit()
        db.refresh(user)
        print(f"수정된 사용자 이름: {user.name}")

        # [D] Delete - 데이터 삭제
        # db.delete(user)
        # db.commit()
        # print("사용자 삭제 완료")

    except Exception as e:
        db.rollback()
        print(f"에러 발생: {e}")
    finally:
        db.close()

if __name__ == "__main__":
    crud_example()

# --
