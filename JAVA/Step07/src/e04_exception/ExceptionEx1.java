package e04_exception;

public class ExceptionEx1 {
	public static void main(String[] args) {
		/*
		 * complie Error: 문법 에러(실행 x)
		 * Runtime Error: 실행 중에 발생하는 에러(실행 o)
		 */
		try {
			//작업 영역
			//에러가 발생 할 수 있는 영역
			System.out.println(1);
			System.out.println("나누기 결과 : "+ 5/0);// 에러발생부분
			System.out.println(2);
		}catch (ArithmeticException e) {
			//try 영역에서 Exception(런타입 에러)이 발생 했을때 처리하는 영역
			//Exception이 발생 했을 때
			System.out.println(3);
		}
		System.out.println(4);
	}
	
}
