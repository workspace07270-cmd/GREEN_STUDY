-- DB 생성
CREATE database STUDENT_DB;

CREATE database STUDENT_DB
	character set utf8mb4
    COLLATE utf8mb4_0900_as_ci;
    
-- DB 선택
USE STUDENT_DB;

-- 학생 테이블 만들기
create table students(
student_id int auto_increment primary key,
name varchar(50) not null,
grade int,
email varchar(200),
enrolled_at datetime default now()
);

-- 데이터베이스목록 확인
show databases;
-- 테이블삭제
drop table students;
-- db 삭제
drop database student_db;

-- -------------------------------------------------------------------------------
-- 자동차 db생성
-- car_tb 오타
create database car_tb 
character set utf8mb4
collate utf8mb4_0900_ai_ci; 

-- 생성된 db 목록확인
show databases;

-- 생성한 db 선택
use car_db;

-- 현재선택된 db확인
select database();
-- ------------------------------------------------------------------------------
-- 계정생성
--              사용자명     호스트(ip, %, localhost)   암호
create user 'car_admin'@'localhost' identified by '12345678';
-- 권한부여
grant all privileges on car_db.*to'car_admin'@'localhost';
-- 권한 즉시 적용
flush privileges;
-- 부여된 권한 확인
show grants for 'car_admin'@'localhost';
-- car 테이블 만들기
CREATE TABLE cars (
  car_id       INT           AUTO_INCREMENT PRIMARY KEY, -- 차량 고유번호
  brand        VARCHAR(50)   NOT NULL,                   -- 브랜드 (현대, BMW 등)
  model        VARCHAR(100)  NOT NULL,                   -- 모델명 (아반떼, 5시리즈 등)
  year         INT,                                      -- 연식
  mileage      INT,                                      -- 주행거리 (km)
  price        DECIMAL(12,0),                            -- 판매가격 (원)
  registered_at DATETIME     DEFAULT NOW()               -- 등록일
);
-- 샘플데이터 추가
INSERT INTO cars (brand, model, year, mileage, price)
VALUES 
('현대', '아반떼 CN7', 2022, 25000, 21000000),
('기아', '쏘렌토 MQ4', 2021, 48000, 35500000),
('제네시스', 'G80 (RG3)', 2023, 12000, 62000000),
('BMW', '5시리즈 (G30)', 2020, 55000, 42000000),
('테슬라', '모델 3', 2021, 38000, 45000000);

-- car 직원사용자---------
-- 사용자명 car_user
create user 'car_user'@'localhost' identified by '12345678';
-- 현재 존재하는 계정 목록
select user, host from mysql.user;
-- 직원은 car_db에있는 테이블 조회권한만 부여
grant select on car_db.* to 'car_user'@'localhost';
show grants for 'car_user'@'localhost';
-- 권한 회수
revoke select on car_db.* from 'car_user'@'localhost';
flush privileges;

-- ------------------------------------------------------------------------
-- ------------------------------------------------------------------------
-- 회원관리 
create database member_db;

use member_db;

CREATE TABLE members (
  member_id   INT          AUTO_INCREMENT PRIMARY KEY, -- 회원 고유번호
  username    VARCHAR(50)  NOT NULL,                   -- 아이디
  email       VARCHAR(100) NOT NULL,                   -- 이메일
  grade       VARCHAR(20)  DEFAULT 'BRONZE',           -- 회원 등급
  is_active   BOOLEAN      DEFAULT TRUE,               -- 활성 여부
  joined_at   DATETIME     DEFAULT NOW()               -- 가입일
);

show databases;
select database();

-- admin --
create user 'member_admin'@'localhost' identified by '12345678';
grant all privileges on member_db.*to'member_admin'@'localhost';
flush privileges;
show grants for 'member_admin'@'localhost';

-- app_user --
create user 'app_user'@'localhost' identified by '12345678';
grant select,insert, update on member_db.* to 'app_user'@'localhost';


-- cs_user--
create user 'cs_user'@'localhost' identified by '12345678';
grant select on member_db.* to 'cs_user'@'localhost';

-- -----------------------------------------------------------------------------------
INSERT INTO members (username, email, grade, is_active, joined_at) VALUES
('kim_coder', 'kim@example.com', 'GOLD', TRUE, '2023-01-15 10:30:00'),
('lee_developer', 'lee@example.com', 'SILVER', TRUE, '2023-05-20 14:15:00'),
('park_star', 'park@example.com', 'BRONZE', TRUE, NOW()),
('choi_admin', 'choi@example.com', 'PLATINUM', TRUE, '2022-12-01 09:00:00'),
('jung_user', 'jung@example.com', 'BRONZE', FALSE, '2024-02-10 18:45:00');

SELECT * FROM members;

-- --------------------------------------------------------------------------
-- DML - insert
-- INSERT INTO 테이블명 VALUES(데이터1, 데이터2,.....)
-- 모든필드에 데이터를 넣을때 사용
INSERT INTO MEMBERS
VALUES(6,'홍길동','TEST@abc.com','BRONZE', FALSE, now());
-- INSERT INTO 테이블명(속성1, 속성2,.....) values(데이터 1, 데이터2,..)
-- 특정필드에만 데이터를 넣을때 사용
INSERT INTO MEMBERS (username,email,grade,is_active)
VALUES('홍길동','TEST@abc.com','BRONZE', FALSE );

-- --------------------------------------------------------------------------------
-- car table 데이터한건 추가
use car_db;

INSERT into cars (brand,model,year,mileage,price,registered_at)
values('kia','k5',2025,44444,22222,now());

select*from cars;

-- -----------------------------------------------------------------------------
-- 조회하기
-- select 기본
-- 작성한필드만 조회
-- select 필드명1, 필드명2 ,..... from 테이블명 
-- 모든필드 전체 데이터 조회
-- select * from 테이블명 

use member_db;
select*from members;
select username, email from members;

-- 카 ------
use car_db;
select*from cars;
select year from cars;
-- cars 테이블에서 연도만 조회, 중복된 내용은 제거
select distinct year from cars;

