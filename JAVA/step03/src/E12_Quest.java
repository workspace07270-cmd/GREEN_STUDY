import java.util.Scanner;

/**
 * 이 클래스는 입력받은 숫자가 '완전수'인지 판별하는 예제입니다.
 * 완전수(Perfect Number): 자기 자신을 제외한 약수들의 합이 자기 자신과 같은 수 (예: 6, 28)
 */
public class E12_Quest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("숫자 입력 : ");
		int n = sc.nextInt();
		
		// 약수들의 총합을 저장할 변수
		int total = 0;
		
		/*
		 * [완전수 판별 로직]
		 * Step 1: 1부터 (n / 2)까지 반복하며 약수를 찾습니다.
		 * (어떤 수 n의 약수 중 자신을 제외한 가장 큰 약수는 n/2보다 클 수 없으므로 연산 효율을 위해 n/2까지만 검사합니다.)
		 */
		for(int i = 1; i <= n / 2; i++) {
			/*
			 * Step 2: n을 i로 나누었을 때 나머지가 0이면 i는 n의 약수입니다.
			 */
			if(n % i == 0) {
				// 약수인 경우 total 변수에 누적 합산
				total += i;
			}
		}
		
		/*
		 * Step 3: 약수의 총합(total)이 입력받은 숫자(n)와 같은지 비교하여 결과 출력
		 */
		if(n != 0 && n == total) {
			System.out.println("입력하신 숫자 " + n + "은(는) 완전수 입니다.");
		} else {
			System.out.println("입력하신 숫자 " + n + "은(는) 완전수가 아닙니다.");
		}
		
		sc.close(); // 스캐너 자원 해제
	}

}