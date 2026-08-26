package e05_iot;
/**
 * Power를 상속받은 클래스(TV, Light, Aircon) 전원 관리하는 앱
 */
public class IotApp {
	//관리할 기기 등록할 배열
	private Power[] arr;
	
	public IotApp() {
		arr = new Power[] {new TV(),new Aircon(),new Light(),new Aircon()};
	}
	//전체 전원을 켜는 메서드
	public void allDevicePowerOn() {
		for (int i = 0; i < arr.length; i++) {
			arr[i].powerOn();
		}
	}
	//전체 전원을 끄는 메서드
	public void allDevicePowerOff() {
		for (int i = 0; i < arr.length; i++) {
			arr[i].powerOff();
		}
	}
	
}