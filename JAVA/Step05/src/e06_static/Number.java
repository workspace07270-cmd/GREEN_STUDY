package e06_static;

public class Number {
	//프로그램 시작 끝날때까지 저장이 되어있음
	//객체 생성과 별개로 메모리에 할당되어 있음.
	private static int num = 0;
	private int n;
	
	public Number() {
		n = ++num;
		System.out.println("num : " + num);
	}
	public int getN() {
		return n;
	}
}