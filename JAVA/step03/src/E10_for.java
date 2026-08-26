import java.util.Scanner;

public class E10_for {
	/* 숫자 입력>1
	 * 숫자 입력>10
	 * 1~10까지의 합:55
	 * ----------------
	 * 숫자 입력>10
	 * 숫자 입력>1
	 * 1~10까지의 합:55
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자 입력 : ");
		int n1 = sc.nextInt();
		System.out.print("숫자 입력 : ");
		int n2 = sc.nextInt();
		System.out.println(n1 + "/" + n2);
		
		//총합 저장할 변수
		int total = 0;
//		//작은 숫자부터 큰 숫자까지 합
//		if(n1 > n2) {
//			for(; n2 <= n1; n2++) {
//				total += n2;
//			}
//		}else {
//			for(; n1 <= n2; n1++) {
//				total += n1;
//			}
//		}
//		System.out.println("1~10까지의 합 : " + total);
		
		//총합 저장할변수
		if(n1>n2) {
			int temp =n1;
			n1 = n2;
			n2 = temp;
		}
		
		for(;n1<=n2; n1++) {
			total += n1; 
		}
		System.out.println(total);
	}
}