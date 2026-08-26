import os
from datetime import datetime
from typing import Any, Dict, List, Optional

import requests
from dotenv import load_dotenv
from sqlalchemy import (Column, DateTime, Float, Integer, MetaData, String,
                        create_engine, inspect, text)
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import Session, sessionmaker

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
load_dotenv(os.path.join(BASE_DIR, ".env"))

DB_HOST = os.getenv("DB_HOST", "localhost")
DB_PORT = os.getenv("DB_PORT", "3306")
DB_NAME = os.getenv("DB_NAME")
DB_USER = os.getenv("DB_USER")
DB_PASSWORD = os.getenv("DB_PASSWORD")
API_SERVICE_KEY = os.getenv("HOSP_SERVICE_KEY") or os.getenv("API_SERVICE_KEY") or os.getenv("SERVICE_KEY")

if not all([DB_USER, DB_PASSWORD, DB_HOST, DB_PORT, DB_NAME, API_SERVICE_KEY]):
    raise RuntimeError(
        "Missing required .env configuration. Please set DB_USER, DB_PASSWORD, "
        "DB_HOST, DB_PORT, DB_NAME, and HOSP_SERVICE_KEY in your .env file."
    )

DATABASE_URL = (
    f"mysql+pymysql://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}?charset=utf8mb4"
)

engine = create_engine(DATABASE_URL, echo=False, future=True)
SessionLocal = sessionmaker(bind=engine, autoflush=False, autocommit=False, future=True)

metadata = MetaData()
Base = declarative_base(metadata=metadata)


def safe_int(value: Any) -> Optional[int]:
    if value is None or value == "":
        return None
    try:
        return int(value)
    except (ValueError, TypeError):
        return None


def safe_float(value: Any) -> Optional[float]:
    if value is None or value == "":
        return None
    try:
        return float(value)
    except (ValueError, TypeError):
        return None


class Hospital(Base):
    __tablename__ = "hospitals"

    ykiho = Column(String(64), primary_key=True, nullable=False)
    yadmNm = Column(String(255), nullable=True)
    clCd = Column(String(32), nullable=True)
    clCdNm = Column(String(255), nullable=True)
    sidoCdNm = Column(String(64), nullable=True)
    sgguCdNm = Column(String(64), nullable=True)
    emdongNm = Column(String(64), nullable=True)
    addr = Column(String(512), nullable=True)
    postNo = Column(String(32), nullable=True)
    telno = Column(String(64), nullable=True)
    hospUrl = Column(String(255), nullable=True)
    estbDd = Column(String(16), nullable=True)
    xPos = Column(Float, nullable=True)
    yPos = Column(Float, nullable=True)
    drTotCnt = Column(Integer, nullable=True)
    mdeptGdrCnt = Column(Integer, nullable=True)
    detySdrCnt = Column(Integer, nullable=True)
    created_at = Column(DateTime, nullable=False, default=datetime.utcnow)
    updated_at = Column(DateTime, nullable=False, default=datetime.utcnow, onupdate=datetime.utcnow)


def create_database() -> None:
    Base.metadata.create_all(bind=engine)

    inspector = inspect(engine)
    if "hospitals" not in inspector.get_table_names():
        return

    existing_columns = {col["name"] for col in inspector.get_columns("hospitals")}
    alter_statements = []

    if "mdeptGdrCnt" not in existing_columns:
        alter_statements.append("ADD COLUMN `mdeptGdrCnt` INT NULL")
    if "detySdrCnt" not in existing_columns:
        alter_statements.append("ADD COLUMN `detySdrCnt` INT NULL")
    if "created_at" not in existing_columns:
        alter_statements.append("ADD COLUMN `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    if "updated_at" not in existing_columns:
        alter_statements.append("ADD COLUMN `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")

    if alter_statements:
        with engine.connect() as conn:
            conn.execute(text(f"ALTER TABLE hospitals {', '.join(alter_statements)}"))
            conn.commit()


def build_api_params(page_no: int = 1, num_of_rows: int = 100) -> Dict[str, Any]:
    return {
        "ServiceKey": API_SERVICE_KEY,
        "pageNo": page_no,
        "numOfRows": num_of_rows,
        "_type": "json",
        "sidoCd": "110000",   # 서울특별시110000
        "sgguCd": "110020",  # 양천구110020
    }


def normalize_item(item: Dict[str, Any]) -> Dict[str, Any]:
    return {
        "ykiho": item.get("ykiho"),
        "yadmNm": item.get("yadmNm"),
        "clCd": item.get("clCd"),
        "clCdNm": item.get("clCdNm"),
        "sidoCdNm": item.get("sidoCdNm"),
        "sgguCdNm": item.get("sgguCdNm"),
        "emdongNm": item.get("emdongNm"),
        "addr": item.get("addr"),
        "postNo": item.get("postNo"),
        "telno": item.get("telno"),
        "hospUrl": item.get("hospUrl"),
        "estbDd": item.get("estbDd"),
        "xPos": safe_float(item.get("XPos") or item.get("xPos")),
        "yPos": safe_float(item.get("YPos") or item.get("yPos")),
        "drTotCnt": safe_int(item.get("drTotCnt")),
        "mdeptGdrCnt": safe_int(item.get("mdeptGdrCnt")),
        "detySdrCnt": safe_int(item.get("detySdrCnt")),
    }


def extract_items(response_json: Dict[str, Any]) -> List[Dict[str, Any]]:
    response = response_json.get("response") or {}
    body = response.get("body") or {}
    items = body.get("items") or {}
    item = items.get("item")

    if item is None:
        return []
    if isinstance(item, list):
        return item
    return [item]


def fetch_hospitals(page_no: int = 1, num_of_rows: int = 100) -> Dict[str, Any]:
    url = "https://apis.data.go.kr/B551182/hospInfoServicev2/getHospBasisList"
    params = build_api_params(page_no=page_no, num_of_rows=num_of_rows)
    response = requests.get(url, params=params, timeout=30)
    response.raise_for_status()
    return response.json()


def save_hospitals(items: List[Dict[str, Any]], db_session: Session) -> int:
    inserted = 0
    for raw_item in items:
        normalized = normalize_item(raw_item)
        if not normalized["ykiho"]:
            continue

        hospital = Hospital(**normalized)
        existing = db_session.get(Hospital, hospital.ykiho)
        if existing is None:
            db_session.add(hospital)
        else:
            for key, value in normalized.items():
                setattr(existing, key, value)
            existing.updated_at = datetime.utcnow()
        inserted += 1

    db_session.commit()
    return inserted


def load_yangcheon_hospitals() -> None:
    create_database()

    with SessionLocal() as db_session:
        total_inserted = 0
        page_no = 1
        num_of_rows = 100

        while True:
            payload = fetch_hospitals(page_no=page_no, num_of_rows=num_of_rows)
            body = payload.get("response", {}).get("body", {})
            total_count = safe_int(body.get("totalCount")) or 0
            items = extract_items(payload)

            if not items:
                break

            inserted = save_hospitals(items, db_session)
            total_inserted += inserted
            print(f"Page {page_no}: 저장된 병원 {inserted}개")

            if page_no * num_of_rows >= total_count:
                break
            page_no += 1

        print(f"총 저장된 양천구 병원 수: {total_inserted}")


if __name__ == "__main__":
    load_yangcheon_hospitals()
