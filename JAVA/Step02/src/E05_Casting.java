
public class E05_Casting {
	/*
	 * 데이터 형변환: 데이터 타입을 바꾸는 행위
	 * 
	 * 자동으로 데이터가 형변환: 연산할 때 자동으로 변환
	 *        - 수의 체계상 작은 개념과 큰 개념이 계산 했을 때
	 *             -->실수와 정수를 계산할때 정수가 실수로 바뀜
	 *        - 메모리 바이트수가 작은 타입이 큰 타입과 계산 했을 때
	 *             --> int와 long이 계산했을 때 int가 long으로 형변환
	 *        -short, byte타입에는 int형으로 데이커가 넣을때 자동변환     
	 *             
	 * 
	 * 
	 */
	public static void main(String[] args) {
		int n = 120;
		double d = 5.456;
		// 자동 형변환 되는 경우
		System.out.println(n/d);
		
		//강제 형변환
		double pi =3.1415;
		int i = (int)pi;
		System.out.println(i);
		
		i=65;
		char ch1 = (char)i;
		System.out.println(ch1);
		
		System.out.println((int)'1');
	
	}
}
