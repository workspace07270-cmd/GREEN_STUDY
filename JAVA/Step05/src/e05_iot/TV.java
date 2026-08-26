package e05_iot;

public class TV extends Power{
	private boolean power;

	@Override
	public void powerOnOff() {
		power=!power;
		System.out.println(power ? "TV 전원 On" : "TV 전원 Off");
	}

	@Override
	public void powerOn() {
		power=true;
		System.out.println("TV 전원 On");
	}

	@Override
	public void powerOff() {
		power= false;
		System.out.println("TV 전원 Off");
	}	
}
