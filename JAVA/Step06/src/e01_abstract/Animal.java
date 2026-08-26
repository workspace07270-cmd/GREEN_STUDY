package e01_abstract;
/*
 *  추상 클래스(Abstract Class)
 *  	- 일반적으로 최상위 클래스로 존재(최초에 클래스의 구조를 잡는 용도)
 *  	- 추상 클래스는 직접적으로 생성되지 않음
 *   	- 자식 클래스가 생성이 될떄만 생성됨
 *   	- class 앞에 abstract가 붙음
 *  추상 메서드
 *  	- 몸통이 없는 메서드
 *  	- 해당 추상클래스를 상속받는 자식 클래스는 반드시 재정의 해야함
 *  	  안하면 추상클래스로 만들어야함.
 *  	- 추상 메서드를 만들려면 해당 클래스가 추상 클래스여야함.
 */
public abstract class Animal {

	public Animal() {
		System.out.println("Animal 생성자");
	}
	
	public void eat() {
		System.out.println("동물이 먹이를 먹습니다.");
	}
	//추상 메서드
	public abstract void run();
}