package e05_iot;

public class Aircon extends Power{
	private boolean power;

	@Override
	public void powerOnOff() {
		power=!power;
		System.out.println(power ? "에어컨 전원 On" : "에어컨 전원 Off");
	}

	@Override
	public void powerOn() {
		power=true;
		System.out.println("에어컨 전원 On");
	}

	@Override
	public void powerOff() {
		power= false;
		System.out.println("에어컨 전원 Off");
	}
	
}
