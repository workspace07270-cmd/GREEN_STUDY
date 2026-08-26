package e06_diamond;

public interface D extends B,C{
	public void methodD();
	//다이아몬드 상속시 이렇게 디폴트 메서드가 동일하면 중복되는 문제가 생김
	@Override
	default void method1() {
		C.super.method1();
		B.super.method1();
	}
}