package e06_diamond;

public interface A {
	public void methodA();
	//추상메서드는 상속과정 최종 구현단계까지 중복되도 괞찮음
	//마지막에 구현만 하면 괜찮음
	public void methodB();
	
	default void method() {
		System.out.println("A");
	}
	
		
}