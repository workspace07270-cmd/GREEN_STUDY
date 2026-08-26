import os
import sys
from typing import Any, Dict, List, Optional

from dotenv import load_dotenv
from fastapi import Depends, FastAPI, HTTPException, Query
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import FileResponse
from fastapi.staticfiles import StaticFiles
from sqlalchemy.orm import Session

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
CROLLING_DIR = os.path.join(os.path.dirname(BASE_DIR), "MiniTest_crolling")
if CROLLING_DIR not in sys.path:
    sys.path.insert(0, CROLLING_DIR)

FRONTEND_BUILD_DIR = os.path.join(os.path.dirname(BASE_DIR), "MiniTest_front", "build")
FRONTEND_BUILD_EXISTS = os.path.isdir(FRONTEND_BUILD_DIR)

load_dotenv(os.path.join(CROLLING_DIR, ".env"))
from Mini import Hospital, SessionLocal

DB_HOST = os.getenv("DB_HOST", "localhost")
DB_PORT = os.getenv("DB_PORT", "3306")
DB_NAME = os.getenv("DB_NAME")
DB_USER = os.getenv("DB_USER")
DB_PASSWORD = os.getenv("DB_PASSWORD")

if not all([DB_USER, DB_PASSWORD, DB_HOST, DB_PORT, DB_NAME]):
    raise RuntimeError(
        "Missing required .env configuration. Please set DB_USER, DB_PASSWORD, "
        "DB_HOST, DB_PORT, DB_NAME in your .env file."
    )

app = FastAPI(
    title="양천구 병원 정보 API",
    description="MySQL에 저장된 양천구 병원 정보를 조회하고 병원명으로 검색하는 API입니다.",
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

if FRONTEND_BUILD_EXISTS:
    app.mount(
        "/static",
        StaticFiles(directory=os.path.join(FRONTEND_BUILD_DIR, "static")),
        name="static",
    )


def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


def hospital_to_dict(hospital: Hospital) -> Dict[str, Any]:
    return {
        "ykiho": hospital.ykiho,
        "yadmNm": hospital.yadmNm,
        "clCd": hospital.clCd,
        "clCdNm": hospital.clCdNm,
        "sidoCdNm": hospital.sidoCdNm,
        "sgguCdNm": hospital.sgguCdNm,
        "emdongNm": hospital.emdongNm,
        "addr": hospital.addr,
        "postNo": hospital.postNo,
        "telno": hospital.telno,
        "hospUrl": hospital.hospUrl,
        "estbDd": hospital.estbDd,
        "xPos": hospital.xPos,
        "yPos": hospital.yPos,
        "drTotCnt": hospital.drTotCnt,
        "mdeptGdrCnt": hospital.mdeptGdrCnt,
        "detySdrCnt": hospital.detySdrCnt,
        "created_at": hospital.created_at.isoformat() if hospital.created_at else None,
        "updated_at": hospital.updated_at.isoformat() if hospital.updated_at else None,
    }


@app.get("/hospitals", response_model=List[Dict[str, Any]])
async def list_hospitals(
    skip: int = Query(0, ge=0, description="건너뛸 항목 수"),
    limit: int = Query(100, ge=1, le=1000, description="반환할 최대 항목 수"),
    db: Session = Depends(get_db),
):
    """양천구에 저장된 병원 목록을 조회합니다."""
    hospitals = (
        db.query(Hospital)
        .filter(Hospital.sgguCdNm == "양천구")
        .offset(skip)
        .limit(limit)
        .all()
    )
    return [hospital_to_dict(h) for h in hospitals]


@app.get("/hospitals/search", response_model=List[Dict[str, Any]])
async def search_hospitals(
    name: str = Query(..., min_length=1, description="검색할 병원 이름"),
    skip: int = Query(0, ge=0, description="건너뛸 항목 수"),
    limit: int = Query(100, ge=1, le=1000, description="반환할 최대 항목 수"),
    db: Session = Depends(get_db),
):
    """병원명(yadmNm)으로 양천구 병원을 검색합니다."""
    hospitals = (
        db.query(Hospital)
        .filter(
            Hospital.sgguCdNm == "양천구",
            Hospital.yadmNm.like(f"%{name}%"),
        )
        .offset(skip)
        .limit(limit)
        .all()
    )
    if not hospitals:
        raise HTTPException(status_code=404, detail="검색된 병원이 없습니다.")
    return [hospital_to_dict(h) for h in hospitals]


@app.get("/")
async def root():
    if FRONTEND_BUILD_EXISTS:
        index_path = os.path.join(FRONTEND_BUILD_DIR, "index.html")
        return FileResponse(index_path)
    return {
        "message": "양천구 병원 정보 API",
        "endpoints": [
            "/hospitals",
            "/hospitals/search?name={병원이름}",
        ],
    }


@app.get("/{full_path:path}", include_in_schema=False)
async def serve_frontend(full_path: str):
    if not FRONTEND_BUILD_EXISTS:
        raise HTTPException(status_code=404, detail="Page not found")

    file_path = os.path.join(FRONTEND_BUILD_DIR, full_path)
    if os.path.isfile(file_path):
        return FileResponse(file_path)
    return FileResponse(os.path.join(FRONTEND_BUILD_DIR, "index.html"))


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8000)
