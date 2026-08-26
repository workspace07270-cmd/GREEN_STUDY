use new_student_db

SELECT CONCAT ('홍','길동','입니다.')as text;

-- student table에서 학생이름과 전화번호를 묶어서 이름(전화번호) 형식으로 조회하시오
select concat (name, '(',phone,')') from student;
-- course 테이블에서 과목명과 학점을 묶어 "[과목명] - 학점: 점수" 형식으로 출력하세여
select concat ('[',name,'] - 학점:' , score) from course;

select*from student where name like concat('%', '지우', '%');

-- SUBSTRING: 특정문자열 추출
-- 3번째에서 2개뽑겠다. 실사례 주민번호
select SUBSTRING('1234567890',3,2), substring('1234567890',5,4);
-- 날짜문자열에서 연도만 추출 (2026-04-14)
select SUBSTRING('2024-04-14', 1,4);

-- student 테이블에서 학번(no)은 8자리 (예:20261234)로 구성되어있습니다.
-- 학번 앞 4자리를 추출하여 '입학년도'라는 별칭으로 조회하세요.
select SUBSTRING(no ,1,4) as 입학년도 from student;
-- major 테이블에서 전화번호(tel)의 첫 3자리 (지역번호 등)만 추출하여 조회하세요
select SUBSTRING(tel,1,2)  from major;
select if(CHAR_LENGTH(tel)>10,SUBSTRING(tel,1,2),SUBSTRING(tel,1,1))from major;

-- replace: 텍스트 변경
select replace('010-1234-5678', '-','') as phone_number;
select replace('AAaa','A','B');
-- major 테이블에서 건물명(buliding)에'관'이라는 글자가 들어간다면
-- 이들 '빌딩'으로 바꾸어 조회하세요. (예: 공학관->공학빌딩)
select REPLACE( building, '관', '빌딩') from major;

-- student 테이블에서 학생의 전화번호(phone)중
-- '010'으로 시작하는 부분을 '8210'(국가코드)로 변경하여 조회하세요.
select REPLACE(phone,'010', '8210') from student;

-- upper/lower 소문자 대문자 변환
select upper('mysql') as upper_case, lower('mysql') as lower_case;

-- char_length: 글자개수
select CHAR_LENGTH('ABC'), CHAR_LENGTH('안녕하');

-- trim 좌우공백 날리기
select trim('               a                     '), CHAR_LENGTH(TRIM('               a                     '));
select ltrim('   a       '), rtrim('        a                       ');

-- lpad, rpad
select lpad('1234',10,'0'), rpad('1234',10,'0');
select lpad('AAA',10,'123'),rpad('AAA',10,'123');

-- major 테이블에 학과번호 3자리 압나다.이를 총4자리로 표시하겠습니다.
-- 빈 앞자린는 알파벳 M을 붙이도록 하겠습니다.
-- M003
select lpad(no,4,'M000') from major;

-- student 테이블에서 phone 컬럼의 데이터를앞에 5자리까지만 부분 추출후에
-- 나머지 뒷자리는 '*'로 마스킹처리 후 출력
-- 01012******
select rpad(SUBSTRING(phone, 1,5),11,'*')from student;
select concat(SUBSTRINg(phone, 1,5),'******')from student;

-- 이름 연락처
-- 김*수 010****1234

select concat (SUBSTRING(name, 1,1),'*',SUBSTRING(name,CHARACTER_LENGTH(name),1)) as name,
concat(SUBSTRING(phone,1,3),'****',SUBSTRING(phone,8,4))as phone from student;

select concat (left(name,1),'*', if(CHAR_LENGTH(name)=2,'',right (name,1))) as name,
concat(left(phone,3),'****',right(phone,4))as phone from student;

-- instr: 특정 단어가 몇번째 글자에 있는지 검색하는 함수
select instr('ABCDEF','C'),instr('ABCDEF','X');
-- round(숫자, 자리수) 반올림
select
    round('12345.12345',-3),
    round('12345.12345',-2),
    round('12345.12345',-1),
    round('12345.12345',0),
    round('12345.12345',1),
    round('12345.12345',2),
    round('12345.12345',3);
-- TRUNCATE(숫자, 자리수): 반(내림)
    select
    TRUNCATE(12345.12345,-3),
    TRUNCATE('12345.12345',-2),
    TRUNCATE('12345.12345',-1),
    TRUNCATE('12345.12345',0),
    TRUNCATE('12345.12345',1),
    TRUNCATE('12345.12345',2),
    TRUNCATE('12345.12345',3);
    
-- CEIL, FLOOR: 올림, 내림 -소수점만제거
select ceil(3.4), floor(3.4);
select ceil(-3.4), floor(-3.4);

-- ABS: 절대값
select abs(100), abs(-100);

-- Mod: 나누기 나머지 값
select mod(5,2), mod(4,2);

-- FORMAT: , 소수점 자리지정/ 반올림
select 
format(1234567.89,0),
format(1234567.895,2);

-- format 의 주사용처  $1.500 
select 
    concat('$',format(1500,0));

-- -----------------------------------
-- NOW: 현재 날짜 시간, curdate: 현재 날짜, curtime: 현재시간
SELECT
    now(),CURDATE(),curtime();

-- 날짜를 더하거나 빼기
-- Date_add: 특정일 기준으로 날짜를 계산하는 함수, 몇일 뒤 날짜, 한달 뒤 날짜
SELECT CURDATE(), DATE_ADD(CURDATE(), interval 30 day) as after_date;
SELECT CURDATE(), DATE_ADD(CURDATE(), interval 1 week) as after_date;
SELECT CURDATE(), DATE_ADD(CURDATE(), interval -1 week) as after_date;
SELECT CURDATE(), DATE_SUB(CURDATE(), interval 1 week) as after_date;
SELECT CURDATE(), DATE_ADD(CURDATE(), interval 1 month) as after_date;
SELECT CURDATE(), DATE_ADD(CURDATE(), interval 1 year) as after_date;
-- 택배일
select curdate(), date_add(curdate(), interval 3 day) as after_date;

-- 날짜 형태
select date_format(now(),'%Y-%m-%d');
select date_format(now(),'%Y년-%m월-%d일');
select date_format(now(),'%Y-%m-%d %H-%i-%s');
select date_format(now(),'%y년 %m월 %d일 %p %h시 %i분');
-- 26년 04월 14일 Tue PM 03시 17분
select date_format(now(),'%y년 %m월 %d일 %a %p %h시 %i분');
-- 2026 April 14th Tuesday PM 03시 17분
select date_format(now(),'%Y %M %D %W %p %h시 %i분');
-- 2026 Apr 14 Tuesday PM 03시 17분
select date_format(now(),'%Y %b %d %W %p %h시 %i분');

-- 날짜 계산: 특정일1 -특정일2 -> 일수 반환
select ABS(DATEDIFF(curdate(),'2026-12-31'));
select DATEDIFF('2026-12-31', CURDATE());
-- 년도만
select year(now()), month(now()), day(now())
     ,hour(now()), minute(now()), second(now());
-- --------------------------------------------------------------------------------------------
-- if(조건, 참, 거짓)
-- 학점 3학점 이상이면, '전공필수', '전공선택'
select *,if(score>=3,'전공필수','전공선택') as course_type from course;
select *,if(score>=3, '전공필수',if(score>=2, '전공필수','전공선택')) as course_type from course;

-- 빈문자열인 연락처 컬럼을 null 변경
update student set phone = null where phone like '';
-- 값이 널이 인것
select *from student where phone is null;
-- 값이 널이 아닌것
select*from student where phone is not null;
-- if null(값1, 값1이 널일때 나타낼값)
select no, major_no, name, ifnull (phone, '연락처미입력') as phone from student; 
-- coalesce(값1, 값2,....)
select COALESCE(phone,'연락처 미입력')as phone from student;
-- ------------------------------------------------------------
-- 그룹: SIM, AVG, COUNT, MAX, MIN 
-- ------------------------------------------------------------

-- 'GROUP BY 컬럼'이 없으면 전체 데이터 기준으로 계산
select count(*) from student;
-- 학과기준
select major_no, count(*) from student GROUP BY major_no;
select *from student;

-- 입학년도별로 묶기,
select lpad(left(no,2),4,'2000'), count(*)
from student group by lpad(left(no,2),4,'2000');

--  student 전부 2026 라서 1개의 그룹만나온다
select left(no,2), count(*)from student group by left(no,2);

-- 입학년도별, 학과별 인원수 2개 묶기
select LPAD(left(no,2),4,'2000'), major_no, count(*)
from student group by LpaD(left(no,2),4,'2000'), major_no;

-- 문제

-- 문제 1. (문자열) student 테이블에서 학번(no)의 앞 4자리(입학년도)와 이름(name)을 하이픈(-)으로 연결하여 '입학년도-이름' 형식(예: 2026-홍길동)으로 조회하세요. (별칭: student_title)
SELECT concat(left(no,4), '-' ,name)as student_title from student;

-- 문제 2. (문자열 + 제어 흐름) student 테이블에서 학생의 연락처(phone)가 있다면 뒤의 4자리를 '**'로 마스킹 처리하고(예: 0101234**), 연락처가 비어있다면(NULL) '번호없음'으로 출력하세요. (별칭: masked_phone) 
-- (힌트: SUBSTRING, CONCAT, IFNULL 또는 IF 활용)ㅇ
select if(phone is  not null ,concat(SUBSTRING(phone,1,7),'****'), '번호없음')  as masked_phone from student;
-- 문제 3. (숫자) course 테이블에서 각 과목의 학점(score)을 1.5배 한 후, 소수점 첫째 자리에서 무조건 올림 처리하여 정수로 조회하세요. (별칭: adjusted_score)
select ceil(score*1.5) as adjusted_score from course;
-- 문제 4. (날짜 + 문자열) student 테이블에서 학번(no)의 앞 4자리(입학년도)를 추출한 뒤, 현재 연도에서 그 값을 빼서 '입학 후 경과 년수'를 계산하여 조회하세요. (별칭: years_passed)
-- (힌트: YEAR, CURDATE 활용)
select YEAR( CURDATE())- interval SUBSTRING(no,1,4) as years_passed  from student;
-- 문제 5. (제어 흐름) Enrollment 테이블에서 부여된 2성적(grade)에 따라 상태를 표시하세요. 

-- 'A' 또는 'B'로 시작하면 ➔ '패스'
-- 'C' 또는 'D'로 시작하면 ➔ '재수강 권장'
-- 'F'면 ➔ '낙제'
-- 아직 입력되지 않은 경우(NULL) ➔ '평가대기' (별칭: grade_status)
-- 문제 6. (문자열) course 테이블에서 과목명(name) 양 끝에 있을지도 모르는 불필요한 공백을 먼저 완벽히 제거한 후, 그 글자 수가 7글자 이상인 과목의 모든 컬럼을 조회하세요.

-- 문제 7. (제어 흐름 + 문자열) major 테이블에서 전화번호(tel)의 앞 2자리가 '02'로 시작하면 '서울 본캠퍼스', 그 외의 번호이거나 비어있다면 '지방/기타 캠퍼스'로 분류하여 학과명(name)과 함께 조회하세요. (별칭: campus_type)

-- 문제 8. (숫자 + 제어 흐름) student 테이블에서 학번(no)을 숫자로 다루어 2로 나눈 나머지를 구하고, 나머지가 0이면 '청백팀', 1이면 '홍백팀'으로 체육대회 팀을 배정하여 학생 이름(name)과 함께 조회하세요. (별칭: team_name)

-- 문제 9. (문자열) major 테이블에서 전공 번호(no)를 총 5자리 문자열로 출력하되, 빈 앞자리는 별표('*')로 채워 출력하세요. (예: 101 ➔ **101) (별칭: masked_major_no)

-- 문제 10. (날짜 포맷팅) 성적 증명서 발급 시간을 시뮬레이션합니다. 현재 시간(NOW())을 기준으로 증명서 발급 일시를 구하되, 보기 좋게 'YYYY/MM/DD PM/AM HH시 MI분' (예: 2026/04/14 PM 05시 45분) 포맷으로 변환하여 단일 값으로 조회하세요. (별칭: print_datetime)