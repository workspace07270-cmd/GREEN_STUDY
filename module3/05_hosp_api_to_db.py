import os
import requests
from dotenv import load_dotenv
from sqlalchemy import create_engine, Column, Integer, String, Float, Text
from sqlalchemy.orm import sessionmaker, declarative_base

# 1. 환경변수 로드
load_dotenv()

DB_HOST = os.getenv("DB_HOST")
DB_PORT = os.getenv("DB_PORT")
DB_NAME = os.getenv("DB_NAME")
DB_USER = os.getenv("DB_USER")
DB_PASSWORD = os.getenv("DB_PASSWORD")
API_KEY = os.getenv("HOSP_SERVICE_KEY") # .env에 추가 필요

print(DB_PORT)
# 2. Database 연결 설정
DATABASE_URL = f"mysql+pymysql://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}"
engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

# 3. ORM 모델 정의
class Hospital(Base):
    __tablename__ = "hospitals"

    id = Column(Integer, primary_key=True, autoincrement=True)
    ykiho = Column(String(100), unique=True, nullable=False) # 요양기호 (고유키)
    yadmNm = Column(String(200)) # 병원명
    clCd = Column(String(20))    # 종별코드
    clCdNm = Column(String(100)) # 종별코드명
    sidoCdNm = Column(String(50))# 시도명
    sgguCdNm = Column(String(50))# 시군구명
    emdongNm = Column(String(50))# 읍면동명
    postNo = Column(String(10))  # 우편번호
    addr = Column(Text)          # 주소
    telno = Column(String(20))   # 전화번호
    hospUrl = Column(Text)       # 홈페이지
    estbDd = Column(String(20))  # 개설일자
    drTotCnt = Column(Integer)   # 의사총수
    XPos = Column(Float)         # X좌표
    YPos = Column(Float)         # Y좌표

# 테이블 생성
Base.metadata.create_all(bind=engine)

def fetch_and_save_hospitals():
    url = "https://apis.data.go.kr/B551182/hospInfoServicev2/getHospBasisList"
    
    # 강남구 코드 설정: 서울(110000), 강남구(110001)
    params = {
        'serviceKey': '27f55e035a48478c505a3dd0e84124d59ec7136b0c7f56049fcaf8761191fb04',
        'pageNo': '1',
        'numOfRows': '1000', # 한 번에 100건씩 (필요시 반복문으로 전체 수집 가능)
        'sidoCd': '110000',
        'sgguCd': '110001',
        '_type': 'json'
    }

    try:
        print("API 호출 중...")
        response = requests.get(url, params=params)
        
        if response.status_code != 200:
            print(f"API 요청 실패: {response.status_code}")
            return

        data = response.json()
        header = data.get('response', {}).get('header', {})
        
        if header.get('resultCode') != '00':
            print(f"API 서비스 에러: {header.get('resultMsg')}")
            return

        items = data.get('response', {}).get('body', {}).get('items', {}).get('item', [])
        
        if not items:
            print("조회된 데이터가 없습니다.")
            return

        db = SessionLocal()
        count = 0
        
        for item in items:
            # 기존 데이터 확인 (ykiho 기준)
            exists = db.query(Hospital).filter(Hospital.ykiho == item.get('ykiho')).first()
            if exists:
                continue

            hosp = Hospital(
                ykiho=item.get('ykiho'),
                yadmNm=item.get('yadmNm'),
                clCd=item.get('clCd'),
                clCdNm=item.get('clCdNm'),
                sidoCdNm=item.get('sidoCdNm'),
                sgguCdNm=item.get('sgguCdNm'),
                emdongNm=item.get('emdongNm'),
                postNo=item.get('postNo'),
                addr=item.get('addr'),
                telno=item.get('telno'),
                hospUrl=item.get('hospUrl'),
                estbDd=str(item.get('estbDd', '')),
                drTotCnt=int(item.get('drTotCnt', 0)),
                XPos=float(item.get('XPos')) if item.get('XPos') else None,
                YPos=float(item.get('YPos')) if item.get('YPos') else None
            )
            db.add(hosp)
            count += 1
        
        db.commit()
        db.close()
        print(f"새로운 병원 정보 {count}건 저장 완료!")

    except Exception as e:
        print(f"에러 발생: {e}")

if __name__ == "__main__":
    if not API_KEY:
        print(".env 파일에 HOSP_SERVICE_KEY를 설정해주세요.")
    else:
        fetch_and_save_hospitals()

# SQLAlchemy ORM을 사용해서
# 심평원_병원정보서비스 OPEN API를 이용해서 
# 강남구에 있는 병원 정보를 MySQL에 저장하는 코드를 작성해줘.
# .env에 DB 접속 정보는 이미 만들어져 있다.
# **API 호출 방법**

# *   **요청 URL 및 방식:** REST 방식을 사용하며, 병원기본목록 조회를 위한 엔드포인트는 `https://apis.data.go.kr/B551182/hospInfoServicev2/getHospBasisList`입니다.
# *   **응답 포맷 설정:** 기본 응답 포준은 XML이지만, 요청 URL 파라미터에 `&_type=json`을 추가하면 JSON 형태로 데이터를 받을 수 있습니다.
# *   **인코딩 주의:** 병원명과 같이 한글이 포함된 파라미터(`yadmNm` 등)를 넘길 때는 반드시 **UTF-8로 인코딩**하여 요청해야 합니다.
# *   **트래픽 및 승인:** 개발 계정 기준 **일 1,000건**의 트래픽이 제공되며, 인증키 신청 후 공공데이터포털과 건강보험심사평가원의 동기화가 이루어지기까지 약 30분이 소요됩니다.

# **주요 요청 데이터 항목 (Input)**

# API 호출 시 사용할 수 있는 주요 파라미터입니다.
# *   `ServiceKey` **(필수)**: 공공데이터포털에서 발급받은 서비스 인증키 
# *   `pageNo`: 페이지 번호 (옵션, 미입력 시 샘플 데이터 기준 1)
# *   `numOfRows`: 한 페이지 결과 수 (옵션, 미입력 시 샘플 데이터 기준 10)
# *   `yadmNm`: 병원명 (특정 병원 이름으로 검색 시 사용)
# *   `sidoCd`, `sgguCd`, `emdongNm`: 시도코드, 시군구코드, 읍면동명 (지역별 검색 시 사용)
# *   `clCd`, `zipCd`, `dgsbjtCd`: 종별코드, 분류코드, 진료과목코드 (예: 종합병원, 의원, 내과 등 특정 조건 검색 시 사용)
# *   `xPos`, `yPos`, `radius`: 특정 좌표(X, Y)를 기준으로 지정한 반경(m) 내의 병원을 검색할 때 사용

# **주요 응답 데이터 항목 (Output)**

# 응답 결과는 정상 호출 시 결과코드 `00`(NORMAL SERVICE)과 함께 총 조회 건수(`totalCount`) 및 세부 리스트(`Item`)를 반환합니다. DB에 저장할 주요 항목들은 다음과 같습니다.
# *   `ykiho` **(필수 응답)**: **암호화된 요양기호** (데이터베이스에서 병원을 식별하는 고유 PK로 활용하기 적합합니다.)
# *   `yadmNm`: 병원명
# *   `clCdNm` / `clCd`: 종별코드명 (예: 종합병원) 및 종별코드
# *   `sidoCdNm` / `sgguCdNm` / `emdongNm`: 지역별 세부 명칭 (시도명, 시군구명, 읍면동명)
# *   `addr` / `postNo`: 주소 및 우편번호
# *   `telno` / `hospUrl`: 전화번호 및 홈페이지 주소
# *   `estbDd`: 개설일자
# *   `XPos` / `YPos`: 병원 위치의 X, Y 좌표
# *   **의료 인력 정보:** `drTotCnt`(의사총수)를 포함하여 의과, 치과, 한방 각각에 대한 일반의, 인턴, 레지던트, 전문의 인원수가 세분화된 데이터 항목(`mdeptGdrCnt`, `detySdrCnt` 등)으로 제공됩니다.