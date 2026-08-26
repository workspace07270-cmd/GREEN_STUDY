-- student
create database student_db;

use student_db;

-- 학생테이블
-- 학번, 이름, 입학년도, 이메일
-- 201611111, 홍길동, 경영정보학과, 2026, hong1234@test.ac.kr

create table student(
no char(8) primary key,
name varchar(10) not null,
major varchar(20),
im_year year,
email varchar(50)
); 
-- 샘플 데이터 10건
insert  INTO student (no, name, major, im_year, email)
VALUES ('20240001', '김철수', '컴퓨터공학', 2024, 'chulsoo.kim@example.com');

select*from student;

insert  INTO student (no, name, major, im_year, email)
VALUES ('20240021', '퉁퉁이', '스포츠과학', 2020, 'ddongsoo.kim@example.com'); 
    
commit;
rollback;
-- 데이터 조회
select*from student;
-- 특정 컬럼 조회
-- 이름, 학과명, 이메일
select name, major, email from student;
select no, email, major from student;
-- 입학년도가 2024년인 학생데이터만 조회
select*from student where im_year = 2024;
-- 입학년도가 2025년 아닌 학생만
select *from student where im_year !=2025;
-- 학과명이 컴퓨터공학과인 학생데이터만 조회
select *from student where major = '컴퓨터공학과';
-- 이름이 김씨인 학생만 조회
select *from student where name like '김%';
-- 이름이 현으로 끝나는 학생만
select *from student where name like '%현';
-- 이름에 서 가있는 학생만
select *from student where name like '%서%';
-- 학과명에 공학과로 끝나는데, 공학과 앞에 반드시 2글자가 와야함, _의갯수
select *from student where major like '___공학과';
-- 학생이메일이 네이버메일인 학생을 조회
select*from student where email like '%@naver.com';
-- 입학년도가 2023, 2024인 학생들만 조회
select*from student where im_year = 2023 or im_year =2024;
select *from student where im_year in(2023,2024);
select *from student where im_year between 2023 and 2024;
-- not 사용법
select *from student where major not like '__공학과';
select *from student where im_year not in(2023,2024);
select *from student where  not im_year =2023;






-- 정열 - order by 컬럼명 asc, 컬럼명 desc
-- 오림차순
select*from student order by im_year;
-- 내림차순
-- asc가 오름차순 desc 내림차순
select *from student order by im_year desc;
select *from student order by im_year desc, no asc;

-- 이름 기준으로 내림차순 정렬
select *from student order by name desc;

-- 고정길이 문자열, 가변길이 문자열
-------------------------------------------------------------------------
CREATE TABLE string_test (
    char_col CHAR(10),
    varchar_col VARCHAR(10)
);

INSERT INTO string_test VALUES ('Apple', 'Apple');

select*from string_test;

select*from string_test where char_col='Apple';
-- 다른 db는 조회가 안됨
-- 고정길이 문자열은 남은 공간을 공백으로 저장, 공백까지 비교
select*from string_test where char_col like'Apple';
select CHARACTER_LENGTH(char_col), 
CHARACTER_LENGTH(varchar_col) from string_test;

-- 학생테이블에 있는 학과 목록만 조회, 단 중복된 학과는 제거
select distinct major from student;


-- alter: create로만든 개체를 수정할때 사용
-- 사용자 비밀번호 변경
alter user 'car_admin'@'localhost' identified by '123456789';


-- 테이블 이름 변경------------------------------------------------------------
alter table string_test rename to new_string_test;
-- 컬럼 추가
alter table new_string_test add column num int default 100;
-- 학생 테이블에 성별 컬럼추가 boolean 기본값 false
alter table student add column gender boolean;
-- 성별 컬러 지우기
alter table student drop column gender;
-- 컬럼 타입 변경
alter table student modify column name varchar(15) not null;
-- 타입 변경시 기존데이터가 변환 되는 건지 체크 , 안되는게 정상
alter table student modify column name int not null;
alter table student modify column name varchar(2) not null;
-- 컬럼 이름 변경
alter table student change column gender new_gender boolean default false;

-- ------------------------------------------------------------------------
-- 업데이트 수정
-- update 테이블명 set 컬럼명 = 수정할 값, ....where 조건식
update student set im_year= 2022 where im_year = 2023;
-- delete 삭제
-- delete from 테이블명 where 조건식
-- 김씨 학생 데이터만 삭제
delete from student where name like '김%';

-- 학과 테이블
-- 1. 학과 목록만 추출
select distinct major from student;
-- 2. 학과 번호 생성하여 학과번호, 학과명 조회
select s.major from(select distinct major from student) s;

select row_number() over(order by major) as no, major as name 
from (select distinct major from student) s;

select CONCAT('M', LPAD(ROW_NUMBER() over (order by major), 3, '0') ) as no,
major as name
from (select distinct major from student) as unique_majors;

-- -------------------------------------------------------------------------
-- 3. 학과 테이블
create table  major(
no char(4) primary key,
name varchar(20)
);

-- 4.학과 테이블에 데이터 추가
insert into major(no, name)
select CONCAT('M', LPAD(ROW_NUMBER() over (order by major), 3, '0') ) as no,
major as name
from (select distinct major from student) as unique_majors;

-- 5.학과 테이블 생성하면서 데이터를 추가 - 추천x
create table major2
as
select CONCAT('M', LPAD(ROW_NUMBER() over (order by major), 3, '0') ) as no,
major as name
from (select distinct major from student) as unique_majors;

-- 6. 학생 테이블에 학과 번호 컬럼 추가 (오늘의 핵심 여서부터~)
alter table student add column mno char(4);

-- 7. 학생 테이블에 학과 번호 값을 업데이트, 학과 테이블을 찹조해서 업데이트 수행
update student set mno = (select no from major where name = major);

-- 8. 학생테이블의 학과명 컬럼을 제거
alter table student drop column major;

-- 9. 간단하게 조인 체험
select s.no, s.name, m.name, s.email 
from student s join major m on s.mno= m.no;


-- -----------------------------------------------------------
-- 도서관리 db 구축
-- -----------------------------------------------------------
-- 1. library_db 생성
-- 2. books 테이블 생성
--   도서고유번호, 도서 제목, 저자, 출판사, ISBM, 제고수량, 등록일 
-- 3. 샘플 데이터 5건 생성해서 저장
-- 4. 도서분류 테이블
--   분류 아이디값, 분류명, 분류설명
-- 5. 도서 카테고리 샘플데이터 5건 생성해서 저장
-- 6. 도서 테이블에 분류 아이디 컬럼 추가
-- 7. 각 테이블에 샘플데이터 추가 

-- 1
create database library_db;

-- drop database library)db;
-- 2
CREATE TABLE books (
    book_id char(20) PRIMARY KEY,            -- 도서고유번호
    title VARCHAR(255) NOT NULL,              -- 도서 제목
    author VARCHAR(100) NOT NULL,             -- 저자
    publisher VARCHAR(100),                   -- 출판사
    isbn VARCHAR(20),                        -- ISBN
    stock INT DEFAULT 0,                     -- 재고수량
    created_at DATE                          -- 등록일
);
 -- drop table books;

-- 3 샘플데이터
INSERT INTO books (book_id, title, author, publisher, isbn, stock, created_at)
VALUES
('BK-2024-00001', '자바의 정석', '남궁성', '도우출판', '9788994492032', 10, '2024-01-10'),
('BK-2024-00002', 'SQLD 완전정복', '홍길동', '한빛미디어', '9788968481234', 5, '2024-02-15'),
('BK-2024-00003', '혼자 공부하는 자바', '신용권', '한빛미디어', '9788968481470', 8, '2024-03-01'),
('BK-2024-00004', '모던 자바스크립트', '이웅모', '길벗', '9788966189382', 12, '2024-03-20'),
('BK-2024-00005', '데이터베이스 개론', '김연희', '한빛아카데미', '9788956748574', 7, '2024-04-05');

select*from books;
commit;
-- 4 도서 분류 테이블 - 분류 아이디 값/ 분류명 /분류설명


-----------------------------------
-- 1. library_db 생성
create database library_db; 

use library_db;

-- 2. books 테이블 생성
--	 도서 고유번호, 도서 제목, 저자, 출판사, ISBN, 재고수량, 등록일
create table books(
	id int AUTO_INCREMENT primary key,
	title varchar(100) not null,
	author varchar(50),
	publisher varchar(50),
	isbn char(13),
	stock int default 0,
	reg_date datetime default now()
);

-- 3. 샘플 데이터 5건 생성해서 저장
insert into books(title, author, publisher, isbn, stock)
values('자바의 정석', '남궁성', '도우출판', '9788968481479', 10),
('파이썬 라이브러리를 활용한 데이터 분석', '웨스 맥키니', '한빛미디어', '9788968482766', 5),
('코딩 인터뷰 완전 분석', '게일 라크만 맥도웰', '인사이트', '9788966263997', 8),
('클린 코드', '로버트 C. 마틴', '인사이트', '9788966266308', 12),
('알고리즘 트레이닝', '구종만', '인사이트', '9788966266315', 7);

-- 4. 도서 분류 테이블
--   분류 아이디값, 분류명, 분류설명
create table category(
	id char(4) primary key,
	name varchar(20) not null,
	description varchar(100)
);

-- 5. 도서 분류샘플데이터 5건 생성해서 저장
insert into category(id, name, description)
values('C001', '컴퓨터공학', '컴퓨터공학 관련 도서'),
('C002', '데이터과학', '데이터과학 관련 도서'),
('C003', '알고리즘', '알고리즘 관련 도서'),
('C004', '소프트웨어공학', '소프트웨어공학 관련 도서'),
('C005', '프로그래밍언어', '프로그래밍언어 관련 도서');

-- 6. 도서 테이블에 분류 아이디 컬럼 추가
alter table books add column category_id char(4);

-- 7. 각 테이블에 샘플 데이터 추가

-- books_extended.csv, category_extended.csv 파일을 활용해서 데이터 추가
-- d beaver에서 넣었음 마우스 오른쪽 데이터가져오기 > 다음 > 넣고 진행




-- 조회
use library_db;
-- 1. books 테이블에서 도서 제목, 저자, 출판사, 재고수량 조회
select *from books;
select* from category;
select title, author, publisher, stock from books;
-- 2. books 테이블에서 출판사 목록만 조회
select DISTINCT publisher from books;
-- 3. books 테이블에서 재고 수량이 20~50인 도서만 조회
select *from books where stock BETWEEN 20 and 50;
SELECT *FROM books where stock>=20 and stock<=50;
-- 4. books 테이블에서 저자이름이 김씨이면서, 출판사가 코딩월드 인 도서만 조회
select *from books where author like '김%' and publisher ='코딩월드';
-- 5. books 테이블에서 등록일이 2025년 6월인 도서만 조회
select* from books where reg_date BETWEEN '2025-06-01' and '2025-06-30 23:59:59';
select year(reg_date), month(reg_date) from books;
--아래거를위한 초석
select*from books where year(reg_date) = 2025 and month(reg_date) = 6;

-- 6. books 테이블에서 출판사가 '코딩월드' 이거나 '디지털북스'인 도서만 조회
select * from books where publisher = '코딩월드' or publisher = '디지털북스';
select * from books where publisher like '코딩월드' or publisher like '디지털북스';
select * from books where publisher in('코딩월드','디지털북스');
select * from books where publisher in('코딩월드','디지털북스') order by stock desc;
select * from books 

-- 7. 도서 제목을 가나다순(오름차순)으로 정렬하여 조회하세요.
select* from books order by title asc;
-- 8. books 테이블에서 코딩월드 도서를 조회, 재고 수량이 높은 순으로 정렬하여 조회. 

-- 9. 재고(stock)가 많은 순서(내림차순)로 정렬하여 상위 5개만 조회하세요.
select *from books order by stock desc limit 5;
--이건 MYSQL만 가능하다
select *from books order by stock desc limit 10;

select *from books order by stock desc limit 5;

select *from books;
-- 삭제
-- 1. ISBN 번호가 '1234567890123'인 도서를 테이블에서 삭제하세요.
delete from books where isbn = '1234567890123';
-- 2. 재고(stock)가 0인 모든 도서 데이터를 삭제하세요.
delete from books order by stock desc limit 5;
-- 3. 2024년 1월 1일 이전에 등록된(reg_date) 도서 중, 재고가 5권 미만인 데이터를 모두 삭제하세요.
delete from books where reg_date < '2024-01-01'and stock < 10;
-- 수정
-- 1. ID(id)가 10번인 도서의 재고(stock)를 50권으로 변경하는 쿼리를 작성하세요.
update books set stock=50 where id= 10;
select * from books where id= 10;
-- 2. 출판사 이름이 'IT미디어'인 모든 도서의 출판사 이름을 'IT커뮤니케이션'으로 한꺼번에 수정하세요.
update books set publisher= 'IT커뮤니케이션' where publisher = 'IT미디어';
-- 3. 저자가 '김철수'이면서 카테고리 ID가 'C001'인 도서들의 등록일(reg_date)을 
-- 현재 시간(now())으로 갱신하세요.
update books set reg_date= now()
where author = '김철수' and category_id = 'C001'

