package e04_interface;

public class Dog implements Eat{
	private int count= 0;
	@Override
	public void eat() {
		count++;
		System.out.println("개가 사료를"+count+ "먹습니다.");
		}
}
