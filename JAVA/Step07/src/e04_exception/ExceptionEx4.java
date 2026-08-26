package e04_exception;

public class ExceptionEx4 {
	public static int div(int n1, int n2) throws ArithmeticException{
		return n1/n2;
	}
	public static void main(String[] args) {
		try {
			int result = div(10,0);
			System.out.println(result);
		}catch (ArithmeticException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("프로그램 종료");
	}
}
