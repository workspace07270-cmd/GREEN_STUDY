
public class E07_Quest02_0423 {
//문제 설명]

//주사위(1~6)를 총 20번 던집니다.
//던져서 나온 각 눈의 결과를 길이가 20인 배열에 저장합니다.
//저장된 배열을 분석하여 1부터 6까지 각각 몇 번씩 나왔는지 출력하세요. [실행 예시] 1 주사위 결과: [3, 5, 1, 6, 2, 3, ...]
//2 1의 개수: 3번
//3 2의 개수: 2번
//4 ...
//5 6의 개수: 4번 [힌트]
//결과를 저장하는 배열 외에, 각 눈의 개수를 카운팅할 별도의 배열(int[] count = new int[6];)을 만들어 활용하면 훨씬
//쉽습니다.
//
	public static void main(String[] args) {
		
	
		int[] diceResults = new int[20];
		
		int[] count = new int[6]; // 1~6의 개수를 저장할 배열
		
		// 주사위를 20번 던져서 결과 저장
		for (int i = 0; i < diceResults.length; i++) { // 배열의 각 요소에 랜덤한 주사위 결과 저장
			diceResults[i] = (int)(Math.random() * 6) + 1; // 1~6 사이의 랜덤한 정수 생성
			count[diceResults[i] - 1]++; // 해당 눈의 개수 카운팅
		}
		
		// 주사위 결과 출력
		System.out.print("주사위 결과: [");
		for (int i = 0; i < diceResults.length; i++) {
			System.out.print(diceResults[i]);
			if (i < diceResults.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
		
		// 각 눈의 개수 출력
		for (int i = 0; i < count.length; i++) {
			System.out.println((i + 1) + "의 개수: " + count[i] + "번");
		}
	}
}
