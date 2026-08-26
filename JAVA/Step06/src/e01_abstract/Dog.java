package e01_abstract;

public class Dog extends Animal {

	@Override
	public void run() {
		//run 메서드는 추상 메서드이기 떄문에
		//super.run()을 할 수 없다. <-- 추상메서드라 안만들어져 있음.
//		super.run();
		System.out.println("개가 네발로 달립니다.");
	}
}