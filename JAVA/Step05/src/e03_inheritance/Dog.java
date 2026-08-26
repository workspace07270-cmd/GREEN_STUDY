package e03_inheritance;

public class Dog extends animal{
	public Dog() {
		super(); //반드시 부모 생성자가 먼저 호출되어야함
		System.out.println("Dog Constructor");
	}
	//재정의라고 해서 기존 부모클래스의 기능을 제거하라는게 아님
	//동일한 메서드가 존재함. 타입이 Dog라서 Dog에 있는 eat를 우선적으로 실행
	@Override
	public void eat() {
		// super는 부모클래스를 지칭
		super.eat();
		System.out.println("개가 사료를 먹습니다.");
	}
	@Override
	public void run() {
		System.out.println("개가 4발로 달립니다.");
	}
}
