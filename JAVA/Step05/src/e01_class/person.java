package e01_class;

public class person {
	//이름
	String name;
	//나이
	int age;
	
	void init(String n, int a) {
		name = n;
		age = a;
	}
	
	void printInfo() {
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
	}
}
