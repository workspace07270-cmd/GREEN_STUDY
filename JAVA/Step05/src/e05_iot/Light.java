package e05_iot;

public class Light extends Power {
	private int power;
	
	@Override
	public void powerOnOff() {
		power= power ==0 ? 1:0;
		System.out.println(power ==0 ?"전등 Off" : "전등 On");
	}

	@Override
	public void powerOn() {
		power=1;
		System.out.println("전등 On");
	}

	@Override
	public void powerOff() {
		power= 0;
		System.out.println("전등 Off");
}
}