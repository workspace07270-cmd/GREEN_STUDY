package vo;

import java.util.Objects;

/**
 * 학생 정보를 저장하는 Value Object 클래스
 */
public class StudentVO {
	// 필드(데이터)
	// 학번은 한 번 정해지면 변경되지 않도록 final로 선언
	private final String no;    
	private String name;        // 이름
	private String majorName;   // 학과명
	private double score;       // 평점

	/**
	 * 모든 필드를 초기화하는 생성자
	 * @param no 학번 (고유 식별자)
	 * @param name 이름
	 * @param majorName 학과명
	 * @param score 평점
	 */
	public StudentVO(String no, String name, String majorName, double score) {
		this.no = no;
		this.name = name;
		this.majorName = majorName;
		this.score = score;
	}
	
	/**
	 * 학번을 제외한 나머지 학생 정보를 수정하는 메서드
	 */
	public void updateStudentVO(String name, String majorName, double score) {
		this.name = name;
		this.majorName = majorName;
		this.score = score;
	}
	
	/**
	 * 학생 정보를 공백으로 구분하여 한 줄로 출력
	 */
	public void printInfo() {
		System.out.println(no + " " + name + " " + majorName + " " + score);
	}
	
	/**
	 * 학번 Getter
	 */
	public String getNo() {
		return no;
	}

	/**
	 * 이름 Getter
	 */
	public String getName() {
		return name;
	}

	/**
	 * 이름 Setter
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 학과명 Getter
	 */
	public String getMajorName() {
		return majorName;
	}

	/**
	 * 학과명 Setter
	 */
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	/**
	 * 평점 Getter
	 */
	public double getScore() {
		return score;
	}

	/**
	 * 평점 Setter
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * 학번(no)을 기준으로 해시코드를 생성함
	 */
	@Override
	public int hashCode() {
		return Objects.hash(no);
	}

	/**
	 * 학번(no)을 기준으로 객체의 동일성을 비교함
	 * list.indexOf() 등에서 활용됨
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentVO))
			return false;
		StudentVO temp = (StudentVO) obj;
		return no.equals(temp.no);
	}

	
}




