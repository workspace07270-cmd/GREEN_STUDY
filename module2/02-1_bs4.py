import requests
from bs4 import BeautifulSoup

# 1. 크롤링할 웹사이트 주소
url = "https://books.toscrape.com"

# 2. 서버에 페이지 정보 요청하기
response = requests.get(url)
response.encoding = 'utf-8' # 한글 깨짐 방지

# 3. HTML 소스를 파이썬이 이해할 수 있는 구조(BeautifulSoup 객체)로 변환
soup = BeautifulSoup(response.text, 'html.parser')

# 4. 책 정보가 담긴 요소(article)들 모두 찾기
# 이 사이트에서 각 책은 <article class="product_pod"> 태그 안에 들어있습니다.
books = soup.select('article.product_pod')

print(f"--- [첫 페이지 책 목록 ({len(books)}개)] ---")

# 5. 찾은 책 목록을 반복문으로 돌며 상세 정보 추출
for book in books:
    # 제목 추출: <h3> 태그 안의 <a> 태그의 'title' 속성에 전체 제목이 들어있음
    title = book.select_one('h3 > a')['title']
    
    # 가격 추출: class가 'price_color'인 <p> 태그의 텍스트
    price = book.select_one('p.price_color').text
    
    # 출력
    print(f"제목: {title}")
    print(f"가격: {price}")
    print("-" * 30)
