package e01_init;

public class ThreadRun extends Thread{
	
	@Override
	public void run() {
		//스레드가 자동으로 일하는 부분
		for(int i=0;i<1000;i++) {
			System.out.println(i + "번째 일 - " + getName());
		}
		System.out.println(getName() + " 종료");
	}

}