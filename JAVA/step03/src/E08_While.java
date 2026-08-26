
public class E08_While {
	/*
	 * 1~100 사이의 숫자 중에 4와 7의 배수를 출력
	 * 단 4와7 공배수는 1번만 출력
	 * 
	 * 4 7 8 12 14 16 20 21 24 28 30 32 35 ... 98 100
	 * 
	 */
	public static void main(String[] args) {
		
		int i = 1;
		while(i <= 101) {
			if(i % 4 == 0)
				System.out.print(i + " ");
			if(i % 7 == 0)
				System.out.print(i + " ");
			i++;
		}
		
	}
}
