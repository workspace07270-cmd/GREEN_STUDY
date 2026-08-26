package e03_final;

public final class Dog extends Animal {
	//final method는 오버라이딩 안됨.
//	public void eat() {
//		System.out.println("동물이 먹이를 먹습니다.");
//	}
	public void run() {
		System.out.println("개가 달립니다.");
	}
}