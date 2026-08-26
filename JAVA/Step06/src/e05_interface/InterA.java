package e05_interface;

public interface InterA {
	double PI = 3.1415;
	public void interA();
	//디폴트 메서드 --> JDK8부터 지원
	default double circleArea(double r) {
		return r * r * PI;
	}
}