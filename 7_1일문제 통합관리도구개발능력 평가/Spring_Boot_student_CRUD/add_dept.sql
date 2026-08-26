USE TEST_STUDENT_DB;

-- dept_name 컬럼이 없으면 추가
ALTER TABLE student ADD COLUMN dept_name VARCHAR(100) AFTER student_name;

-- 기존 데이터에 기본값 설정
UPDATE student SET dept_name = '미지정' WHERE dept_name IS NULL;

-- 확인
SELECT * FROM student LIMIT 5;
