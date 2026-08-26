package ex_io;

/**
 * 직접 구현한 I/O 기능을 테스트하는 클래스
 */
public class IOTestMain {

	public static void main(String[] args) {
		IOTest io = new IOTest();
		
		// 1. 키보드로부터 줄 단위로 읽어오기 테스트
		String input = io.readline();
		System.out.println("읽어온 결과: " + input);
		
		// 2. 화면에 문자열 출력 테스트
		io.println("Test Output Message");
		
	}

}