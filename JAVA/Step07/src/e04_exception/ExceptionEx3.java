package e04_exception;

public class ExceptionEx3 {

	public static void main(String[] args) {
		try {
			System.out.println(1);
			System.out.println("나누기 결과 : "+ 5/0);// 에러발생부분
			System.out.println(2);
		}catch (ArithmeticException e) {
			System.out.println(3);
		}finally {
			// try 영역에서 작업이 정상적으로 끝나든,
			// Exception 발생해서 강제로 프로그램이 종료되든 catch가 실행되든
			// 무조건 실행되는 영역
		System.out.println(4);
		}
		System.out.println(5);
	}
	
}

