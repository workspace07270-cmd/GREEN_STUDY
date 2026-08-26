import os
from fastapi import FastAPI, Depends, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy import create_engine, Column, Integer, String, Float
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, Session
from dotenv import load_dotenv
from typing import List, Optional

# .env 로드
load_dotenv()

# DB 접속 정보
DB_HOST = os.getenv("DB_HOST")
DB_PORT = os.getenv("DB_PORT")
DB_NAME = os.getenv("DB_NAME")
DB_USER = os.getenv("DB_USER")
DB_PASS = os.getenv("DB_PASSWORD")

DATABASE_URL = f"mysql+pymysql://{DB_USER}:{DB_PASS}@{DB_HOST}:{DB_PORT}/{DB_NAME}"

# SQLAlchemy 설정
engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

# ORM 모델 (books 테이블)
class Book(Base):
    __tablename__ = "books"
    id = Column(Integer, primary_key=True, index=True)
    title = Column(String(255), nullable=False)
    price = Column(Float)
    availability = Column(String(50))
    rating = Column(String(20))

# FastAPI 앱 초기화
app = FastAPI(title="Books API (ToScrape)")

# CORS 설정 - 모든 도메인에서 호출 가능
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 모든 도메인 허용
    allow_credentials=True,
    allow_methods=["*"],  # 모든 HTTP 메서드 허용
    allow_headers=["*"],  # 모든 헤더 허용
)

# DB 세션 의존성 주입 (Dependency Injection)
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# 1. 모든 도서 목록 조회
@app.get("/books")
def get_all_books(skip: int = 0, limit: int = 20, db: Session = Depends(get_db)):
    books = db.query(Book).offset(skip).limit(limit).all()
    return books

# 2. 도서 제목(title)으로 검색
@app.get("/books/search")
def search_books(title: str, db: Session = Depends(get_db)):
    # LIKE 연산자를 이용한 부분 일치 검색
    results = db.query(Book).filter(Book.title.contains(title)).all()
    
    if not results:
        return {"message": f"'{title}'을(를) 포함하는 검색 결과가 없습니다."}
    
    return results

# 3. 도서 상세 정보 (ID 기준)
@app.get("/books/{book_id}")
def get_book_detail(book_id: int, db: Session = Depends(get_db)):
    book = db.query(Book).filter(Book.id == book_id).first()
    if book is None:
        raise HTTPException(status_code=404, detail="도서를 찾을 수 없습니다.")
    return book

if __name__ == "__main__":
    import uvicorn
    # 서버 실행 (uvicorn)
    uvicorn.run(app, host="0.0.0.0", port=8001)
