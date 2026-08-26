package e03_inheritance;
/**
 * Animal 클래스를 상속받는 자식 클래스(Child Class / Sub Class)
 * extends 키워드를 사용하여 상속을 구현함
 */
public class Human extends animal{
	public Human() {
		System.out.println("Human Constructor");
	}
	
	/**
	 * 메서드 오버라이딩(Method Overriding):
	 * 부모로부터 물려받은 메서드를 자식 클래스에 맞게 재정의하는 것
	 */
	@Override // 오버라이딩임을 명시적으로 나타내는 어노테이션
	public void eat() {
		System.out.println("사람이 밥을 먹습니다");
	} 
	public void run() {
		System.out.println("동물이 달립니다.");
	}
	 
}
