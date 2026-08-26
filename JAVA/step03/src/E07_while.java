
public class E07_while {
	public static void main(String[] args) {
		int n = (int)Math.floor(Math.random() * 5)+1;
	
		//숫자 n 만큼 Hello World 를 출력하는 While 문을 작성
		//추가변수 없이 구현

		//추가변수 1개 없이 구현
		int i = 0;
		System.out.println("n : " + n);
		while(i<n) {
			System.out.println("Hello World");
			i++; }
		System.out.println("------");
		while(n > 0) {
			System.out.println("Hello World");
			n--;	
		}
//		while(n-- > 0) {
//		System.out.println("Hello World");}
	}	
	}