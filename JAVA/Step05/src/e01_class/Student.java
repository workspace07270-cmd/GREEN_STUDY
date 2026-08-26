package e01_class;

public class Student {
	//필드
	//학번
	private String no;
	//이름
	private String name;
	//학과명
	private String majorName;
	//평점
	double score;
	
	Student() {
		System.out.println("Student 기본 생성자");
	}
	
	//메서드
	//초기화 메서드
	void init(String s, String n, String mn, double sc) {	
	no = s;
	name = n;
	majorName = mn;
	score = sc;
	}
	//학생정보 출력하는 메서드
	String toStringStudent(){
		return no +"/"+ name +"/"+ majorName +"/"+ score;
	}
	
	
	
}
