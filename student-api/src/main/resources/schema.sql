CREATE DATABASE IF NOT EXISTS new_student
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_ai_ci;

USE new_student;

CREATE TABLE IF NOT EXISTS major (
  no CHAR(3) NOT NULL,
  name VARCHAR(20) DEFAULT NULL,
  building VARCHAR(10) DEFAULT NULL,
  tel CHAR(11) DEFAULT NULL,
  PRIMARY KEY (no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS student (
  no CHAR(8) NOT NULL,
  major_no CHAR(3) NOT NULL,
  name VARCHAR(10) DEFAULT NULL,
  phone CHAR(11) DEFAULT NULL,
  PRIMARY KEY (no),
  KEY FK_major_TO_student_1 (major_no),
  CONSTRAINT FK_major_TO_student_1 FOREIGN KEY (major_no) REFERENCES major (no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO major (no, name, building, tel) VALUES
('101', '컴퓨터공학과', '공학관', '0212345678'),
('102', '경영학과', '경영관', '0212345679')
ON DUPLICATE KEY UPDATE name = VALUES(name), building = VALUES(building), tel = VALUES(tel);
