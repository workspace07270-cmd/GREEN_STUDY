import requests
from bs4 import BeautifulSoup

# 1. 크롤링할 웹사이트 주소
url = "https://books.toscrape.com"

# 2. 서버에 페이지 정보 요청하기
response = requests.get(url)
response.encoding = 'utf-8'

# 3. BeautifulSoup 객체로 변환
soup = BeautifulSoup(response.text, 'html.parser')

# 4. (사용자가 요청한) 아주 상세한 선택자를 사용하여 특정 요소 하나만 가져오기
# 이 선택자는 "두 번째 책의 제목(h3)"을 가리킵니다.
specific_selector = "#default > div > div > div > div > section > div:nth-child(2) > ol > li:nth-child(2) > article > h3"
target_element = soup.select_one(specific_selector)

print("--- [특정 선택자로 찾은 결과] ---")
if target_element:
    # h3 태그 안의 <a> 태그 텍스트 출력
    print(f"두 번째 책 제목: {target_element.get_text(strip=True)}")
else:
    print("해당 요소를 찾을 수 없습니다.")

print("\n" + "="*30 + "\n")

# 5. (기존 방식) 전체 책 목록 가져오기 
books = soup.select('article.product_pod')
print(f"--- [전체 책 목록 ({len(books)}개)] ---")

for book in books:
    title = book.select_one('h3 > a')['title']
    price = book.select_one('p.price_color').text
    print(f"제목: {title} / 가격: {price}")

