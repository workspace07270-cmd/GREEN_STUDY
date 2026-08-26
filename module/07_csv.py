import requests

# --- 1. GET 요청 (데이터 가져오기) ---
# 특정 게시글(1번 게시글) 정보를 요청합니다.
url_get = "https://jsonplaceholder.typicode.com/posts/1"
response = requests.get(url_get)

# 응답 상태 코드 확인 (200: 성공)
if response.status_code == 200:
    # JSON 형식의 응답 데이터를 파이썬 딕셔너리로 변환
    data = response.json()
    print("[GET 요청 성공]")
    print(f"제목: {data['title']}")
else:
    print(f"GET 요청 실패: {response.status_code}")


# --- 2. POST 요청 (데이터 생성하기) ---
# 서버에 보낼 데이터를 딕셔너리 형태로 준비합니다.
url_post = "https://jsonplaceholder.typicode.com/posts"
new_data = {
    "title": "안녕 파이썬",
    "body": "requests 라이브러리 활용 예제입니다.",
    "userId": 1
}

# json 파라미터를 사용하면 자동으로 JSON 형식으로 변환되어 전송됩니다.
response = requests.post(url_post, json=new_data)

if response.status_code == 201:  # 201: Created (성공적으로 생성됨)
    result = response.json()
    print("\n[POST 요청 성공]")
    print(f"생성된 ID: {result['id']}")
    print(f"서버 응답 내용: {result}")
else:
    print(f"\nPOST 요청 실패: {response.status_code}")
