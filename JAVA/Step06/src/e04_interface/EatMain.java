package e04_interface;

public class EatMain {
	
	public static void main(String[] args) {
		Person p = new Person();
		Employee e = new Employee();
		Dog d= new Dog();
		
		p.eat();
		e.eat();
		d.eat();
		
		//인터페이스로도 형변환이 가능
		Eat a= p;
		a.eat();
		a=e;
		a.eat();
		a=d;
		a.eat();
	}
}