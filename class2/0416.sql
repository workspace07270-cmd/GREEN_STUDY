
-- ----
-- 회원 테이블
CREATE TABLE IF NOT EXISTS members (
  member_id  INT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(50)  NOT NULL,
  email      VARCHAR(100) UNIQUE NOT NULL,
  phone      VARCHAR(20),
  grade      ENUM('BRONZE','SILVER','GOLD') DEFAULT 'BRONZE',
  point      INT DEFAULT 0,
  joined_at  DATETIME DEFAULT NOW()
);

-- 구매 테이블
CREATE TABLE IF NOT EXISTS purchases (
  purchase_id  INT AUTO_INCREMENT PRIMARY KEY,
  member_id    INT NOT NULL,
  item_name    VARCHAR(100) NOT NULL,
  price        INT NOT NULL,
  discount     INT DEFAULT 0,
  purchased_at DATETIME DEFAULT NOW(),
  FOREIGN KEY (member_id) REFERENCES members(member_id)
);

-- 샘플 데이터
INSERT INTO members (name, email, phone, grade, point) VALUES
  ('김영희', 'kim@test.com',  '010-1111-0001', 'GOLD',   1500),
  ('이준호', 'lee@test.com',  '010-1111-0002', 'SILVER', 800),
  ('박지연', 'park@test.com', '010-1111-0003', 'BRONZE', 200),
  ('정수연', 'jung@test.com', '010-1111-0004', 'GOLD',   3200),
  ('최현우', 'choi@test.com', '010-1111-0005', 'BRONZE',    0);

INSERT INTO purchases (member_id, item_name, price, discount, purchased_at) VALUES
  (1, '무선 키보드', 50000, 5000, '2025-03-01 10:00:00'),
  (1, '어댓패드',     30000, 3000, '2025-03-15 14:30:00'),
  (2, '미케이블츼',  15000,    0, '2025-02-20 09:00:00'),
  (3, 'USB 허브',      12000, 2000, '2025-04-01 11:00:00'),
  (4, '모니터',     250000,20000, '2025-01-10 16:00:00'),
  (4, '노트북 거치대', 40000,    0, '2025-02-05 13:00:00');

-- 단일행: 가장 포인트가 높은회원
-- 1. 포인트 최대값 조회 
select MAX(point) from members;
-- 2. 회원 테이블에서 포인트 컬럼이 1번 쿼리와 같은값만 조회
select*from members where point = (select max(point) from members);
-- 3. 회원 테이블에서 포인트가 평균 이상인 회원만 조회
select*from members where point >= (select avg(point) from members);

-- 다중행: gold 등급 회원의 구매내역
select*from members m 
join purchases p on m.member_id = p.member_id
where m.grade ='GOLD';

select*from purchases p
where p.member_id in(select member_id from members m where m.grade = 'GOLD');

-- 4. 인라인 뷰: 회원별 총 구매금액 계산 후에 평균 이상만 필터

-- 4-1.회원별 총구매금액 조회
select member_id, sum(price -discount) as tool
from purchases
group by member_id;

-- 4-2. 인라인뷰는 1번의 조회결과 from 절에 지정
select *
from 
 (select member_id, sum(price - discount) as tool
 	from purchases group by member_id ) t ;

-- ------------------------------------------------------------------------------------------------------------------------------------

-- 차량 테이블
CREATE TABLE IF NOT EXISTS cars (
  car_id       INT AUTO_INCREMENT PRIMARY KEY,
  brand        VARCHAR(50) NOT NULL,
  model        VARCHAR(50) NOT NULL,
  category     ENUM('소형','중형','대형','SUV') NOT NULL,
  daily_rate   INT NOT NULL,
  status       VARCHAR(20) DEFAULT '가용',
  registered_at DATETIME DEFAULT NOW()
);

-- 고객 테이블
CREATE TABLE IF NOT EXISTS customers (
  customer_id INT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(50)  NOT NULL,
  phone       VARCHAR(20),
  email       VARCHAR(100) UNIQUE,
  license_no  VARCHAR(20)  UNIQUE NOT NULL
);

-- 렌탈 테이블
CREATE TABLE IF NOT EXISTS rentals (
  rental_id   INT AUTO_INCREMENT PRIMARY KEY,
  car_id      INT NOT NULL,
  customer_id INT NOT NULL,
  start_date  DATE NOT NULL,
  end_date    DATE NOT NULL,
  actual_return DATE,
  total_fee   INT NOT NULL,
  discount    INT DEFAULT 0,
  rented_at   DATETIME DEFAULT NOW(),
  FOREIGN KEY (car_id)      REFERENCES cars(car_id),
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- 샘플 데이터
INSERT INTO cars (brand, model, category, daily_rate, status) VALUES
  ('현대', '아이오닉',   '소형',  40000, '가용'),
  ('기아', '스토닉',     '중형',  55000, '가용'),
  ('숨셸', 'G80',         '대형',  90000, '대여중'),
  ('펌티앤', '팅커',    'SUV', 120000, '가용'),
  ('테슬라', '모델Y', 'SUV', 150000, '가용');

INSERT INTO customers (name, phone, email, license_no) VALUES
  ('김맑도', '010-2222-0001', 'rip@car.com',  'LC-001'),
  ('이소율', '010-2222-0002', 'soy@car.com',  'LC-002'),
  ('박민준', '010-2222-0003', 'min@car.com',  'LC-003');

INSERT INTO rentals (car_id, customer_id, start_date, end_date, actual_return, total_fee, discount) VALUES
  (1, 1, '2025-03-01', '2025-03-05', '2025-03-05', 160000, 10000),
  (2, 2, '2025-03-10', '2025-03-15', '2025-03-16', 275000,     0),
  (3, 3, '2025-04-01', '2025-04-03', NULL,          180000, 20000),
  (4, 1, '2025-02-14', '2025-02-17', '2025-02-17', 360000, 30000);

-- 단일행: 일일 랜탈료가 가장 비싼 차
select max(daily_rate) from cars;

select* from cars where daily_rate= (select max(daily_rate) from cars);

-- 다중행: suv가 렌탈된 이력
select *
from cars c join rentals r on c.car_id= r.car_id
where c. category= 'SUV';

select*
from cars c
where c.category ='SUV';
-- 위에서 suv 찾고 아래로 

select*from rentals
where car_id in(select car_id from cars where category ='SUV');

-- 인라인 뷰: 차량별 누적 수익 계산 후 평균이상만

select avg(total_fee - discount)
from rentals;


select *
from 
	(select car_id, sum(total_fee -discount) as sum_total
	from rentals group by car_id)r
where
	sum_total >= (select avg(total_fee - discount)from rentals);

-- 스칼라(sclar):서브퀴리가 SELECT 절에 사용됨, 하나의 컬럼 처럼 사용됨.
use group_student;
-- 학번, 이름, 학과명, 연락처;
select count(*) from student;

select s.no, s.name, s.phone, m.name
from student s join major m on s.major_no = m.no;

select s.no, s.name, 
	(select m.name from major m where m.no = s.major_no )as major_name, s.phone
from student s ;

--  학생 수강테이블에 있는 grade를 0.0 ~ 4.5까지 랜덤하게 숫자를 업데이트
-- RAND()
select round(rand() * 4.5,2);
update enrollment set grade = round(rand() * 4.5,2);

-- 1. 전체 학생의 평균 성적보다 낮은 점수를 받은 수강 내역을 조회
select avg(grade)
from enrollment;

select *
from enrollment e
where e.grade < (select avg(r.grade) from enrollment r);

-- 2. 수강인원이 가장많은 강좌 이름을 조회
select*
from enrollment
group by e. cousec
;
-- 3. 한 두번 수강신청을 하지 않은 학생 정보를 조회
-- 학번 이름 학과번호연락처 

select s.no, s.name, s.phone
from student s 
	join enrollment e on s.no= e.student_no
	join major m on s.major_no= m.no;

-- 다른 학생의 예
select s.no, s.name, s.major_no, s.phone 
from student s left outer join enrollment e on s.no= e.student_no
where e.course_no is null;
-- 
SELECT s.no, s.name, s.major_no, s.phone
FROM student s LEFT JOIN enrollment e on s.no = e.student_no
WHERE e.course_no is NULL;
-- 
-- 선생님 거
use group_student;

select* from student s
where s.no not in(select distinct e.student_no from enrollment e);

select* from student left outer join enrollment e on s.no = e.student_no
where e. student_no is null;

-- 위에거 안됨 ...-
-- 4. '사회과학관' 건물을 사용하는 학과 고곳 학생들의 명단 조회

-- select*
-- from major m left outer join student s on m. no = s.major_no
-- where m.buliding'사회과학관';

select no from major where building = '사회과학관';
select* from student s
where s. major_no in(select no from major where building = '사회과학관')

-- 5. 학생 학번 ,이름, 수강한 강좌 개수를 출력(스칼라 서브퀴리)

select s.no,s.name,
	(select count(*) from enrollment e where e.student_no = s.no)
	as std
from student s;

-- -----윈도우
create database window_db;
use window_db
 
CREATE TABLE employees (
  id        INT PRIMARY KEY,
  dept      VARCHAR(20),
  emp_name  VARCHAR(20),
  job_title VARCHAR(20),
  salary    INT,
  hire_date DATE,
  region    VARCHAR(10)
);

INSERT INTO employees VALUES
  (1,  '영업', '김철수', '과장', 5200000, '2020-03-15', '서울'),
  (2,  '영업', '이영희', '대리', 3800000, '2021-07-01', '서울'),
  (3,  '영업', '박민준', '사원', 2800000, '2023-01-10', '부산'),
  (4,  '영업', '최수진', '차장', 6100000, '2018-11-20', '서울'),
  (5,  '개발', '정도현', '수석', 7500000, '2017-05-08', '서울'),
  (6,  '개발', '한지우', '선임', 5900000, '2019-09-22', '서울'),
  (7,  '개발', '오민서', '주임', 4200000, '2022-04-11', '대전'),
  (8,  '개발', '강준혁', '수석', 7500000, '2019-02-14', '서울'),
  (9,  '인사', '윤소연', '차장', 5700000, '2016-12-03', '서울'),
  (10, '인사', '임채원', '대리', 3600000, '2022-08-19', '부산');


select * from employees e;

-- ROW_NUMBER(): 파티션 내에서 중복없이 고유한 순번을 부여

select e.emp_name, e.dept, e.salary,
	row_number() over()
from employees e;

select e.emp_name, e.dept, e.salary,
	row_number() over(partition by e.dept order by salary desc)
from employees e;

-- rank(), DENSE_RANK()
-- RANK(), 동점 순위 이후 순위를 건너뜀(1,1,3,4,5...)
-- DENSE_RANK(): 동정 순위 이후 순위를 이어서 부여함(1,1,2,3,4....)
select e.*,
	rank() over(order by salary desc) as rk,
	dense_rank() over(order by salary desc)as drk
from employees e;
-- 
select e.emp_name, e.salary, e.dept,
	rank() over(partition by dept order by salary desc) as rk,
	dense_rank() over(order by salary desc)as drk
from employees e;

-- NTILE(n) : 데이터를 n개의 동일한 버킷으로 나눔, 사분위 분석에 이용됨.
select e.emp_name, e.salary, ntile(3) over(order by e.salary desc) as nt
from employees e;

select e.emp_name, e.salary, 
	ntile(2) over(partition by e.dept order by e.salary desc) as nt
from employees e;

-- sum(): 파티션 합계, 누적합
 select e.dept, e.emp_name,e.salary,
 	-- 부서별 전체 합계
 	sum(e.salary)over(partition by e.dept)as dept_total,
 	sum(e.salary)
 	over(partition by e.dept 
 	order by salary
 	rows between unbounded preceding and current row
 	)as dept_cur_total
 from employees e;

-- AVG():파티션 평균 -- rows between 1 preceding and 1 following) 앞뒤 평균
select e.emp_name, e.salary,
 	avg(e.salary) over(rows between 1 preceding and 1 following) as avg_salary
from  employees e;

-- READ(컬럼명 ,OFFSET, 기본값)- 현재행 기준으로 이전 행의 값 
-- LAG(컬럼명, OFFSET, 기본값) - 현재행 기준으로 다음행의 값

select e.emp_name, e.salary,
	lead(e.emp_name,1,'')over(order by salary desc),
	lag(e.emp_name,1,'')over(order by salary desc)
from employees e;

-- first_value() 
-- last_value()
select e.emp_name, e.salary,
	first_value(e.emp_name) over(partition by e.dept order by salary desc),
	last_value(e.emp_name)
		over(partition by e.dept 
		order by salary desc 
		rows between unbounded preceding and unbounded following)
from employees e ;

-- 부서별 급여 상위 2명 조회
select e. dept,e.emp_name, e.salary,
	row_number()over(partition by e.dept order by e.salary desc)as rw
from employees e;

select *from
(select e. dept, e.emp_name, e.salary,
	row_number()over(partition by e.dept order by e.salary desc)as rw
from employees e)s
where s.rw <=2;

-- 전체 급여대비 비율 계산 직원급여/ 전체 급여
select e.dept, e.emp_name, e.salary,
	sum(e.salary) over(partition by e.dept)as dept_total,
	sum(e.salary) over() as all_total,
	TRUNCATE(e.salary / sum(e.salary) over () *100,1) as rate
from employees e;

-- 각학과 내에서 학생들의 평군 성적을 기준으로 높은 순서대로 순위를 매겨서 조회하새요.
-- 공동 순위가 있는 경우 다음 순위로 건너뛰는 함수를 사용
-- 학과명, 학번, 학생이름, 평균 평점, 순위
use group_student;
-- 
select m.name, s.no, s.name
from student s join major m on s.major_no =m.no;


select m.name, s.no, s.name, e.grade
from student s 
	join major m on s.major_no =m.no
	join enrollment e on s.no= e.student_no
	
-- 내가해본것 윗 R다지 다행이맞음

select m.name, sv.no, sv.name, sv.avg_grade, sv.rk from
	(select
		s.no, s.name, s.major_no, avg(e.grade) as avg_grade,
		rank() over(partition by s.major_no order by avg(e.grade)desc) as rk
from student s join enrollment e on s.no = e.student_no
group by s.no)sv join major m on sv.major_no=m.on;

-- 아몰라

select m.name, sv.`no`,sv.name,sv.avg_grade, sv.rk from
	(select 
		s.no, s.name, s.major_no , avg(e.grade) as avg_grade,
		rank() over(partition by s.major_no order by avg(e.grade) desc) as rk
from student s join enrollment e on s.no = e.student_no
group by s.no) sv join major m on sv.major_no = m.no ;
	
-- 학생별로 수강한 과목의 성적을 학생번호순으로 나열, 각 학생내에서 성적의 누적합계를 구하시오.
-- student_rk, course_n, grade, cumulative_grade
select e.student_no, c.no, e.grade 
from enrollment e join course c on e.course_no= c.no 
;

select 
	(select
		e.student_no, c.no, e.grade 
from enrollment e join course c on e.course_no= c.no ;
-- 

select 
	e.student_no,s.name, e.course_no, c.name, e.grade,
	sum(e.grade) over(partition by e.student_no order by e.course_no)
	as acc_grade
from enrollment e join student s on e.student_no = s.no
join course c on e.course_no = c.no;

-- ------------------------------------------------------------------------------------------

-- 학과 내 최고 점수와 차이계산 조회
-- 학생 개별 성적과 함께, 해당 학생이 속한 학과 전체의 최고 성적을 나란히 표시하고,
-- 최고점과 점수 차이를 계산해서 조회
-- 학생이름, 학과이름, 성적, 학과 최고성적, 성적 - 학과 최고성적
-- 수강내역 , 학생 테이불
select m.name, sv.`no`,sv.name,sv.avg_grade, sv.rk from
(select 
	s.no, s.name, s.major_no , avg(e.grade) as avg_grade,
	rank() over(partition by s.major_no order by avg(e.grade) desc) as rk
from student s join enrollment e on s.no = e.student_no
group by s.no) sv join major m on sv.major_no = m.no ;
