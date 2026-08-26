import java.util.Arrays;

/**
 * 이 클래스는 중복되지 않는 로또 번호(1~45) 6개를 생성하여 배열에 저장하는 예제입니다.
 * 중복 체크 로직과 반복문 제어 방법을 학습합니다.
 */
public class E06_Quest {

	public static void main(String[] args) {
		// 1. 로또 번호 6개를 저장할 정수형 배열 선언
		int[] arr = new int[6];
		
		/*
		 * [로또 번호 생성 로직]
		 * - 1~45 사이의 랜덤 숫자를 생성합니다.
		 * - 배열에 저장하기 전, 기존에 저장된 숫자들과 중복되는지 확인합니다.
		 * - 중복된 숫자가 있다면 현재 인덱스의 번호를 다시 생성합니다.
		 */
		for (int i = 0; i < arr.length; i++) {
			// (1) 1 ~ 45 사이의 랜덤 숫자 생성
			int n = (int)Math.floor(Math.random() * 45) + 1;
			arr[i] = n;			
			
			// (2) 중복 체크 로직
			// 현재 인덱스(i) 이전까지 저장된 요소들(j)과 새로 생성된 숫자(n)를 비교합니다.
			for(int j = 0; j < i; j++) {
				if(arr[j] == n) {
					// 중복된 숫자가 발견된 경우
					i--;  // 외부 for문의 증감식에서 i가 증가할 것이므로, 현재 인덱스를 다시 채우기 위해 1 감소
					break; // 더 이상의 비교가 의미 없으므로 내부 j 반복문을 빠져나감
				}
			}			
		}
		
		// 2. 생성된 로또 번호 배열 출력
		// Arrays.toString()을 사용하여 배열의 내용을 한눈에 확인합니다.
		System.out.println("이번 주 로또 예상 번호: " + Arrays.toString(arr));
	}

}