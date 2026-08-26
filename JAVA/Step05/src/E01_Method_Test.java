
public class E01_Method_Test {
	static int speed = 0, fuel = 100;
	public static void accelator() {
		fuel-=10;
		speed+=10;
		System.out.println("현재 속도 : " + speed + "잔여 연료량 :" + fuel);
	}
	
	public static void main(String[] args) {	
		
		accelator();
		System.out.println("main");
		System.out.println("현재 속도 : " + speed + "잔여 연료량 :" + fuel);
		
	}
}