package e02_text_io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 텍스트 파일을 읽어오는 예제 (전통적인 try-catch-finally 방식)
 */
public class TextFileReaderMain1 {

	public static void main(String[] args) {
		FileReader fr = null;     // 파일과 직접 연결되는 노드 스트림
		BufferedReader br = null; // 성능 향상 및 줄 단위 읽기 기능을 제공하는 보조 스트림
		
		try {
			// 1. 노드 스트림 생성 (파일과 통로를 연결)
			fr = new FileReader("hello.txt");
			
			// 2. 보조 스트림 연결 (버퍼를 사용하여 읽기 효율 증대)
			br = new BufferedReader(fr);
			
			// 3. 파일 내용 읽기 (한 줄씩)
			while(true) {
				String str = br.readLine(); // 더 이상 읽을 내용이 없으면 null 반환
				if(str == null) break; 
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("읽기 오류 발생: " + e.getMessage());
		} finally {
			// 4. 사용이 끝난 스트림은 반드시 닫아주어야 자원 낭비를 막을 수 있습니다.
			try {
				if(br != null) br.close(); // 보조 스트림을 닫으면 연결된 노드 스트림도 함께 닫힙니다.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}