package e02_text_io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 텍스트 파일 쓰기 예제
 */
public class TextWriteMain {

	/*
	 * 파일 쓰기 모드:
	 * 1. 새 파일 모드: 파일이 있으면 덮어쓰고, 없으면 새로 생성 (기본값)
	 * 2. 추가 모드 (Append): 기존 내용 뒤에 이어서 작성
	 */
	public static void main(String[] args) {
		// 1. 노드 스트림(FileWriter)과 보조 스트림(PrintWriter) 생성
		// FileWriter의 두 번째 인자가 true이면 '추가 모드'로 작동합니다.
		try(FileWriter fw = new FileWriter("write.txt", true);
			PrintWriter pw = new PrintWriter(fw);){
			
			// 2. 데이터 출력 (System.out.println과 사용법이 유사하여 편리함)
			pw.println("Hello World");
			pw.println("안녕하세요.");
			pw.println(123456);
			
			// pw.flush(); // 버퍼의 내용을 즉시 파일로 밀어냄 (close 시 자동 호출됨)
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("write.txt 파일 쓰기 완료");
	}

}