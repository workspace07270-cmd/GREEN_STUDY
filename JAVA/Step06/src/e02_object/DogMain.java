package e02_object;

public class DogMain {
	public static void main(String[] args) throws CloneNotSupportedException {
		Dog d1= new Dog("흰둥이", 5);
		Dog d2=(Dog) d1.clone();
		
		d1.setName("깜둥이");
		
		System.out.println(d1);
		System.out.println(d2);
	}
}
