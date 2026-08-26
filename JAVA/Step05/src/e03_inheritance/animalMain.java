package e03_inheritance;

public class animalMain {
	public static void	eat(animal a) {
		a.eat();
}
	public static void main(String[] args) {
		animal animal = new animal();
		System.out.println("----------");
		Human human = new Human();
		System.out.println("----------");
		Dog dog= new Dog();
		System.out.println("----------");
		// 1. 일반적인 메서드 호출
		animal.eat();
		human.eat(); 
		dog.eat();
		
		// 2. 다형성을 이용한 호출 (Upcasting)
		// 자식 객체는 부모 타입의 변수에 담길 수 있으며, 메서드 호출 시 자식의 기능이 실행됨
		eat(animal);
		eat(human);
		eat(dog);
	}
}
