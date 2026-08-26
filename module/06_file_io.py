#파일쓰기
# open()으로 파일을 열고, with 블록이 끝나면 자동으로 닫힙니다.
#'w'모드: 새오쓰기(기존파일이있으면 덮어씁니다)
#encoding="utf-8":한글이 포함된 경우 반드시 지정해야합니다

# 1. 파일열기 (무슨모드로열것인가 주의)
with open('output.txt','w',encoding='utf-8') as f:
# 2. 파일에 문자열 내용출력
    f.write('안녕하세요\n')
    f.writelines(['ㅋㅋㅋㅋ','AAA','\n'])
    f.write('1234567')


#파일쓰기

# 1. 파일열기 (무슨모드로열것인가 주의)
f=open('output.txt','w',encoding='utf-8')
# 2. 파일에 문자열 내용출력
f.write('안녕하세요\n')
f.writelines(['ㅋㅋㅋㅋ','AAA','\n'])
f.write('1234567')
#3. 파일닫기
f.close()

#파일읽기(전체)
#'r' 모드: 읽기전용
#.read()는 파일전체 내용을 문자열 하나로 반환합니다

f=open('output.txt','r', encoding='utf-8')
content = f.read()
print(content)
f.close()
 

with open('output.txt','r', encoding='utf-8')as f:
    content=f.read()
    print(content)
print('파일 읽기 완료')
print('....................')

#이어쓰기(append)
#"a"모드: 기존내용을 유지하고 끝에 이어서 씁니다.
#"W"모드와 달리 파일을 덮어쓰지 않습니다.

with open('output.txt','a', encoding='utf-8') as f:
    f.write('이어쓰기 내용\n')
print('이어쓰기 완료')
print('...........')