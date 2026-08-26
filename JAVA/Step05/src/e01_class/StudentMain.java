package e01_class;

public class StudentMain {
	//Student 객체 3개 생성하고 데이터 셋팅후 출력하기
	public static void main(String[] args){
	Student s1 = new Student();
	Student s2 = new Student();
	Student s3 = new Student();
	
	s1.init("2023001", "홍길동", "컴퓨터공학과", 4.5);
	s2.init("2023002", "김철수", "전자공학과", 3.5);
	s3.init("2023003", "박영희", "기계공학과", 4.0);
	
	System.out.println(s1.toStringStudent());
	System.out.println(s2.toStringStudent());
	System.out.println(s3.toStringStudent());
	
	}
}