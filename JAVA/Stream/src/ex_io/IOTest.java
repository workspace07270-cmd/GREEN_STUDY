package ex_io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 저수준 I/O 스트림을 직접 다루어 보는 연습용 클래스
 */
public class IOTest {
	/**
	 * 키보드(System.in)로부터 한 줄을 입력받아 반환하는 메서드
	 */
	public String readline() {
		// 바이트 스트림을 문자 스트림으로 변환해주는 브릿지 스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		char buffer[] = new char[10]; // 초기 버퍼 크기
		String message = null;
		int idx = 0;

		try {
			System.out.print("입력하세요: ");
			do {
				// 배열이 가득 차면 크기를 늘려주는 작업 (동적 배열 구현 원리)
				if (idx == buffer.length - 1)
					buffer = reallocBuffer(buffer);
				
				buffer[idx] = (char) isr.read(); // 한 글자씩 읽어옴
				idx++;
			} while (buffer[idx - 1] != '\n'); // 줄바꿈 문자가 나올 때까지

			// 디버깅용: 입력된 각 문자의 아스키 코드 출력
			for (int i = 0; i < idx; i++) {
				System.out.print((int) buffer[i] + " ");
			}
			System.out.println();
			
			// char 배열의 내용을 String으로 변환 (줄바꿈 문자 \r\n 제외를 위해 idx-2 사용)
			message = String.copyValueOf(buffer, 0, idx - 2);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// isr은 System.in을 사용하므로 닫지 않는 것이 일반적일 수 있으나, 연습을 위해 닫기 처리
			/*
			try {
				if (isr != null) isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
		}

		return message;
	}

	/**
	 * 배열의 용량이 부족할 때 2배 크기로 늘려주는 보조 메서드
	 */
	private char[] reallocBuffer(char[] buffer) {
		char[] temp = buffer; // 기존 배열 보관
		buffer = new char[temp.length * 2]; // 2배 크기 배열 생성

		for (int i = 0; i < temp.length; i++)
			buffer[i] = temp[i]; // 기존 내용 복사

		return buffer;
	}

	/**
	 * 화면(System.out)에 문자열을 출력하는 메서드
	 */
	public void println(String msg) {
		char[] buffer = msg.toCharArray();
		// 문자 스트림을 바이트 스트림으로 변환해주는 브릿지 스트림
		OutputStreamWriter osw = new OutputStreamWriter(System.out);
		try {
			osw.write(buffer);
			osw.flush(); // 버퍼를 비워 즉시 출력
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// System.out 관련 스트림을 닫으면 이후 System.out 사용이 불가능해지므로 주의
			/*
			try {
				if (osw != null) osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
		}
	}

}