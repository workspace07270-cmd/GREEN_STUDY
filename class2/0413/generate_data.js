const fs = require('fs');

const majors_count = 10;
const students_count = 200;
const courses_count = 30;
const enrollments_count = 100;

const major_names = ["컴퓨터공학", "전자공학", "기계공학", "화학공학", "경영학", "경제학", "심리학", "사회학", "수학", "통계학"];
const buildings = ["공학관", "경상관", "인문관", "자연관", "사회관"];
const last_names = ["김", "이", "박", "최", "정", "강", "조", "윤", "장", "임"];
const first_names = ["서연", "민준", "지우", "서준", "하윤", "주원", "지유", "예준", "채원", "도윤"];
const course_titles = ["데이터베이스", "알고리즘", "자료구조", "운영체제", "네트워크", "웹프로그래밍", "인공지능", "머신러닝", "보안", "클라우드"];
const grades = ["A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F"];

const randomInt = (min, max) => Math.floor(Math.random() * (max - min + 1)) + min;
const randomChoice = (arr) => arr[Math.floor(Math.random() * arr.length)];

// 1. Major Data
const major_data = [];
for (let i = 1; i <= majors_count; i++) {
    major_data.push({
        no: String(100 + i).padStart(3, '0'),
        name: major_names[i - 1],
        building: randomChoice(buildings),
        tel: `02-123-${1000 + i}`
    });
}

// 2. Student Data
const student_data = [];
for (let i = 1; i <= students_count; i++) {
    student_data.push({
        no: `2026${String(i).padStart(4, '0')}`,
        major_no: randomChoice(major_data).no,
        name: randomChoice(last_names) + randomChoice(first_names),
        phone: `010${randomInt(1000, 9999)}${randomInt(1000, 9999)}`
    });
}

// 3. Course Data
const course_data = [];
for (let i = 1; i <= courses_count; i++) {
    course_data.push({
        no: `20261${String(i).padStart(4, '0')}`, // 2026(Year) + 1(Semester) + 0001(Number)
        name: `${randomChoice(course_titles)}_${i}`,
        score: randomInt(1, 3)
    });
}

// 4. Enrollment Data
const enrollment_data = [];
const enrollment_set = new Set();
while (enrollment_data.length < enrollments_count) {
    const c_no = randomChoice(course_data).no;
    const s_no = randomChoice(student_data).no;
    const key = `${c_no}_${s_no}`;
    if (!enrollment_set.has(key)) {
        enrollment_set.add(key);
        enrollment_data.push({
            course_no: c_no,
            student_no: s_no,
            grade: randomChoice(grades)
        });
    }
}

// Helper to write CSV
function saveCSV(filename, data, headers) {
    const content = [headers.join(','), ...data.map(obj => headers.map(h => obj[h]).join(','))].join('\n');
    fs.writeFileSync(filename, content, 'utf8');
}

// Helper to write SQL
function saveSQL(filename) {
    let sql = "USE new_student_db;\n\n";
    
    sql += "-- Major\n";
    major_data.forEach(d => {
        sql += `INSERT INTO major (no, name, building, tel) VALUES ('${d.no}', '${d.name}', '${d.building}', '${d.tel}');\n`;
    });

    sql += "\n-- Student\n";
    student_data.forEach(d => {
        sql += `INSERT INTO student (no, major_no, name, phone) VALUES ('${d.no}', '${d.major_no}', '${d.name}', '${d.phone}');\n`;
    });

    sql += "\n-- Course\n";
    course_data.forEach(d => {
        sql += `INSERT INTO course (no, name, score) VALUES ('${d.no}', '${d.name}', ${d.score});\n`;
    });

    sql += "\n-- Enrollment\n";
    enrollment_data.forEach(d => {
        sql += `INSERT INTO Enrollment (course_no, student_no, grade) VALUES ('${d.course_no}', '${d.student_no}', '${d.grade}');\n`;
    });

    fs.writeFileSync(filename, sql, 'utf8');
}

// Execute
const path = require('path');
const baseDir = 'C:\\class2';
saveCSV(path.join(baseDir, 'major.csv'), major_data, ['no', 'name', 'building', 'tel']);
saveCSV(path.join(baseDir, 'student.csv'), student_data, ['no', 'major_no', 'name', 'phone']);
saveCSV(path.join(baseDir, 'course.csv'), course_data, ['no', 'name', 'score']);
saveCSV(path.join(baseDir, 'enrollment.csv'), enrollment_data, ['course_no', 'student_no', 'grade']);
saveSQL(path.join(baseDir, 'insert_data.sql'));

console.log("Generated in C:\\class2: major.csv, student.csv, course.csv, enrollment.csv, insert_data.sql");
