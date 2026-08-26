
public class E07_Quest01_0423 {
//	  [문제 설명]
//
//			  길이가 10인 정수형 배열을 생성합니다.
//			  배열에 1~100 사이의 랜덤한 정수 10개를 저장합니다.
//			  배열에 저장된 숫자들 중 짝수들만의 합계와 평균을 구하여 출력하세요.
//			  (단, 짝수가 하나도 없을 경우 "짝수가 없습니다"라고 출력해야 합니다.) [힌트]
//			  Math.random() * 100 + 1
//			  if (arr[i] % 2 == 0)
//			  평균은 소수점까지 나오도록 형변환(double)을 활용하세요.
	
	
		public static void main(String[] args) {
			int[] arr = new int[10];
			
			int sum = 0;
			int count = 0;
			
			for (int i = 0; i < arr.length; i++) { // 배열의 각 요소에 랜덤한 정수 저장
				arr[i] = (int)(Math.random() * 100) + 1; // 1~100 사이의 랜덤한 정수 생성
				System.out.print(arr[i] + " "); // 생성된 숫자 출력
				
			if (arr[i] % 2 == 0) { // 짝수인지 판별
				sum += arr[i]; // 짝수인 경우 sum에 누적 합산
				count++; // 짝수의 개수 카운팅
				}
			}
			
			System.out.println(); // 줄 바꿈
			
			if (count > 0) {
				double average = (double)sum / count;
				System.out.println("짝수의 합계: " + sum);
				System.out.println("짝수의 평균: " + average);
			} else {
				System.out.println("짝수가 없습니다");
			}
	}

  }