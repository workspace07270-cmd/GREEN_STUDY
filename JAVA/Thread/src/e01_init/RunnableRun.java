package e01_init;

public class RunnableRun implements Runnable{
	private String name;
	
	public RunnableRun(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		for(int i=0; i<1_000_000_000;i++);
		System.out.println(name + "-종료" + System.currentTimeMillis());
	}

}