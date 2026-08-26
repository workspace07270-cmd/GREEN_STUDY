package mybatis1.dto;

/**
 * 학생의 정보를 담아서 옮기는 바구니(Data Transfer Object) 클래스입니다.
 * DB의 'students' 테이블 구조와 똑같이 설계되었습니다.
 */
public class StudentDTO1 {
	// DB의 컬럼명과 일치하게 필드를 선언합니다.
	private String student_id;
	private String name;
	private String department;
	private double gpa;

	public StudentDTO1() {	}

	public StudentDTO1(String student_id, String name, String department, double gpa) {
		this.student_id = student_id;
		this.name = name;
		this.department = department;
		this.gpa = gpa;
	}

	// 각 데이터를 꺼내거나 넣기 위한 Getter/Setter들입니다.
	
	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * 객체에 담긴 내용을 한 눈에 확인하기 위해 재정의한 메소드입니다.
	 */
	@Override
	public String toString() {
		return "StudentDTO1 [student_id=" + student_id + ", name=" + name + ", department=" + department + ", gpa="
				+ gpa + "]";
	}
}