package e04_interface;

public class Employee implements Eat {

	@Override
	public void eat() {
		System.out.println("직원이 점심을 먹습니다.");
	}
}