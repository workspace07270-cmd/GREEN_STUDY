package e02_Constructor;
// 사번, 이름, 성별, 직급, 급여
// 생성자료 전체 필드 초기화
// 사원정보 출력하는 메소드
// 급여정보만 외부로 반출하는 메서드
public class Employeevo {
	
	private String no;
	private String name;
	private boolean gender;
	private String position;
	private int salary;
	
	public Employeevo(String no, String name, boolean gender, String position, int salary) {
		super();
		this.no = no;
		this.name = name;
		this.gender = gender;
		this.position = position;
		this.salary = salary;
	}	
	public void printInfo() {
		System.out.printf("%s %s %s %s %d/n",no,name,gender ? "남":"여",
				position,salary);	
	}
	public int getSalary() {
		return salary;
	}
	
	
	
	
	
	
	
	
	
	
	
}
