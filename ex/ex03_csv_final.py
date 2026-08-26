# ======================================================
# 최종 실습 — CSV 저장 & 읽기
# 교시: 4교시 (파일 I/O)
# 제한시간: 10분
# ======================================================
import csv

products = [
    {"name": "노트북",  "price": 1200000, "category": "전자기기"},
    {"name": "마우스",  "price": 35000,   "category": "전자기기"},
    {"name": "책상",    "price": 250000,  "category": "가구"},
    {"name": "의자",    "price": 180000,  "category": "가구"},
    {"name": "USB",     "price": 12000,   "category": "전자기기"},
]

# ── STEP 1 ───────────────────────────────────────────────────
# products 데이터를 "products.csv" 파일로 저장하세요.
# 컬럼 순서: name, price, category
# 한글 깨짐 방지 인코딩 사용
file_name = "products.csv"
fieldnames = ["name", "price", "category"]

with open(file_name, "w", encoding="utf-8-sig", newline="") as f:
    writer = csv.DictWriter(f, fieldnames=fieldnames)       
    writer.writeheader()
    writer.writerows(products)

#-----------------------------------------------------------------
with open("products.csv", "w", newline="", encoding="utf-8-sig") as f:
    # fieldnames: CSV 헤더(컬럼 순서) 지정
    writer = csv.DictWriter(f, fieldnames=["name", "price", "category"])
    writer.writeheader()       # 첫 줄에 name,price,category 헤더 작성
    writer.writerows(products) # 딕셔너리 리스트를 한 번에 모두 작성



# ── STEP 2 ───────────────────────────────────────────────────
# "products.csv"를 읽어서 price가 100,000원 이상인 상품만 출력하세요.
# 기대 출력:
# 노트북: 1,200,000원
# 책상: 250,000원
# 의자: 180,000원
print("# STEP 2: 100,000원 이상 상품")
with open(file_name, "r", encoding="utf-8-sig") as f:
    reader = csv.DictReader(f)
    for row in reader:
        price = int(row["price"])
        if price >= 100000:
            print(f"{row['name']}: {price:,}원")

#-----------------------------------------------------------------------------
with open("products.csv", "r", encoding="utf-8-sig") as f:
    reader = csv.DictReader(f)  # 헤더를 key로 사용하는 딕셔너리 형태로 읽기
    for row in reader:
        # CSV는 모든 값이 문자열 → int()로 변환 후 비교
        if int(row["price"]) >= 100000:
            print(f"{row['name']}: {int(row['price']):,}원")    

# ── STEP 3 (도전) ────────────────────────────────────────────
# category별로 상품 수와 평균 가격을 출력하세요.
# 기대 출력:
# 전자기기: 3개, 평균 415,667원
# 가구: 2개, 평균 215,000원
print("\n# STEP 3: 카테고리별 통계")
category_stats = {}

with open(file_name, "r", encoding="utf-8-sig") as f:
    reader = csv.DictReader(f)
    for row in reader:
        cat = row["category"]
        price = int(row["price"])
        
        if cat not in category_stats:
            category_stats[cat] = {"count": 0, "total_price": 0}

        category_stats[cat]["count"] += 1
        category_stats[cat]["total_price"] += price

for cat, stats in category_stats.items():
    avg_price = stats["total_price"] // stats["count"]  # 소수점 없이 정수로 처리
    print(f"{cat}: {stats['count']}개, 평균 {avg_price:,}원")

# -------------------------------------------------------------------------------------------------------- 

    category_map = {}
with open("products.csv", "r", encoding="utf-8-sig") as f:
    reader = csv.DictReader(f)
    for row in reader:
        cat = row["category"]
        # setdefault: key가 없으면 빈 리스트로 초기화 후 반환
        category_map.setdefault(cat, []).append(int(row["price"]))

for cat, prices in category_map.items():
    avg = sum(prices) // len(prices)  # 소수점 버림 (기대 출력 형식 맞춤)
    print(f"{cat}: {len(prices)}개, 평균 {avg:,}원")