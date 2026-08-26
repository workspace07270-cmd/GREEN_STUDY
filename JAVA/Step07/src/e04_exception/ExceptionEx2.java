package e04_exception;

public class ExceptionEx2 {
	public static void main(String[] args) {
		try {
			int[]arr= {1,2,3,0,4,5,6};
			
//			int[] arr= null;
			for(int i=0; i<=arr.length;i++) {
				System.out.println(500/arr[i]);
			}
		} catch (ArithmeticException e) {
			System.out.println("0으로 나눌수없습니다");	
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("배열 인덱스번호가 범위를 벗어났습니다");	
		} catch (NullPointerException e) {
			System.out.println("빈 객체를 실행 했습니다");
		}catch (Exception e) {
			// 모든 Exception 클래스의 부모클래스라서
			// 모든 종류의 Exception을 받을수있다
			System.out.println("알 수 없는 에러발생");
		}
		
		System.out.println("프로그램 종료");
	}
}
