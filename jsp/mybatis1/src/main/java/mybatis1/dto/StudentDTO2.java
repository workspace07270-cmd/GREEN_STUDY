package mybatis1.dto;

/**
 * [학생 정보 데이터 객체 (DTO)]
 * DTO(Data Transfer Object)는 계층 간 데이터를 전달하기 위해 사용하는 바구니 같은 역할을 합니다.
 * 데이터베이스의 학생 테이블(또는 조인된 결과)과 일치하는 구조를 가집니다.
 */
public class StudentDTO2 {
	// 학생 고유 번호
	private String no;
	// 학생 이름
	private String name;
	// 전공 이름 (학과 번호 대신 이름으로 저장)
	private String majorName;
	// 학점 또는 점수
	private double score;

	// 기본 생성자: MyBatis가 객체를 생성할 때 필요합니다.
	public StudentDTO2() {
	}

	// Getter/Setter 메서드: 외부에서 필드에 안전하게 접근하고 값을 설정하기 위해 사용합니다.
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	/** 객체에 담긴 데이터를 문자열로 편하게 확인하기 위해 toString을 재정의합니다. */
	@Override
	public String toString() {
		return "StudentDTO2 [학번=" + no + ", 이름=" + name + ", 전공=" + majorName + ", 점수=" + score + "]";
	}
	
}