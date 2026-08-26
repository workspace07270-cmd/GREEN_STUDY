public class E07_Quest03_0423 {
//	  [문제 설명]
//
//			  극장의 좌석이 10개 있습니다. (길이가 10인 int 배열 생성)
//			  처음에는 모든 좌석이 0(빈 좌석)으로 초기화되어 있습니다.
//			  사용자로부터 예약할 좌석 번호(1~10)를 입력받습니다.
//			  예약 로직:
//			  사용자가 선택한 좌석이 빈 좌석(0)이면 1로 바꾸고 "예약 되었습니다." 출력.
//			  이미 예약된 좌석(1)이면 "이미 예약된 좌석입니다." 출력.
//			  사용자가 -1을 입력하면 프로그램 종료.
//			  종료 시 현재 좌석의 예약 상태를 모두 출력하세요. [힌트]
//			  Scanner와 while(true) 문을 활용하세요.
//			  배열 인덱스는 0부터 시작하므로 입력값에서 1을 빼야 함에 유의하세요.
	public static void main(String[] args) {
		int[] seats = new int[10]; // 좌석 배열 초기화 (0: 빈 좌석, 1: 예약된 좌석)
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		while (true) {
			System.out.print("예약할 좌석 번호(1~10, 종료하려면 -1 입력): ");
			int seatNumber = sc.nextInt();
			
			if (seatNumber == -1) {
				break; // 프로그램 종료
			}
			
			if (seatNumber < 1 || seatNumber > 10) {
				System.out.println("잘못된 입력입니다. 1~10 사이의 숫자를 입력하세요.");
				continue; // 잘못된 입력 처리
			}
			
			int index = seatNumber - 1; // 배열 인덱스 계산
			
			if (seats[index] == 0) {
				seats[index] = 1; // 좌석 예약
				System.out.println("예약 되었습니다.");
			} else {
				System.out.println("이미 예약된 좌석입니다.");
			}
		}
		
		sc.close(); // 스캐너 자원 해제
		
		// 현재 좌석의 예약 상태 출력
		System.out.println("현재 좌석 예약 상태:");
		for (int i = 0; i < seats.length; i++) {
			System.out.println((i + 1) + "번 좌석: " + (seats[i] == 0 ? "빈 좌석" : "예약된 좌석"));
		}
	}
}
