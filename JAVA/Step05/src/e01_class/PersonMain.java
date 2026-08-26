package e01_class;

public class PersonMain {
	public static void main(String[] args) {
		person p1 = new person();
		person p2 = new person();
		
		p1.init( "홍길동", 20);
		p2.init( "김철수", 33);
		
		p1.printInfo();
		p2.printInfo();
	}
	
}
