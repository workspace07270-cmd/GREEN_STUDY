name = '홍길동'
age = 20
is_student = True
score = None

print(name,age,is_student,score)

# type()으로 변수 타입을 확인 가능
print(type(name))
print(type(age))
print(type(is_student))
print(type(score))

# -- 문자열 기본적인 조작 --
# 문자열 메서드는 원본을 바꾸지 않고 새 문자열로 반환
greetring = '      안녕하세요 Hello'
print(greetring)
print(greetring.strip()) # 앞뒤 공백제거
print(greetring.strip().upper()) # 대문자
print(greetring.strip().lower()) #소문자
print(len(greetring)) # 문자열 길이
print(len(greetring.strip()))

url = 'https://example.com/board?page=1' #board?page=1 페이지 1번으로 가겠다
print(url.split('?')) # ?를 기준으로 분리 -> 리스트로 반환
print(url.split('?')[1])
arr = url.split('?')
print(arr[len(arr)-1])
print(url.replace('https','http'))

#in을 이용해서 특정문자열 포함여부를 확인
print('example' in url)
print('example1' in url)

#특정 문자열로 시작하는지 확인
print(url. startswith('https'))
#특정 문자열로 끝나는지 확인
