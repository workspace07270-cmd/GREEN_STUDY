
public class E01_ArrayEx01 {
	/*
	 * 배열(Array)
	 *  변수를 선언했을때 하나의 변수명으로
	 *  동시에 여러개 값을 저장할 공간을 만드는 형태
	 * 인덱스(Index) - 0 ~ 배열길이 -1
	 *  동시에 여러개 선언된 배열의 요소들을 구분하기위한 번호표
	 * 배열 생성 방법
	 *  변수타입[] 변수명 = new 변수타입[개수]
	 */
	public static void main(String[] args) {
		//길이가 5인 정수형 배열
		int[] arr = new int[5];
		//데이터 초기화
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		arr[3] = 4;
		arr[4] = 5;
		// arr[5] = 100; // 인덱스 범위가 벗어나면 런타임 에러 발생한다.
		
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		System.out.println(arr[3]);
		System.out.println(arr[4]);
		
	}
}
