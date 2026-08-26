package e02_text_io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 텍스트 파일을 읽어오는 예제 (try-with-resources 현대적 방식)
 * JDK 7부터 도입되었으며, 자동으로 스트림을 닫아줍니다.
 */
public class TextFileReaderMain2 {

	public static void main(String[] args) {
		// try() 괄호 안에 객체를 생성하면, try 블록이 끝날 때 자동으로 close()가 호출됩니다.
		try(FileReader fr = new FileReader("hello.txt");
			BufferedReader br = new BufferedReader(fr);){
			
			String str = null;
			// readLine()의 반환값을 str에 대입함과 동시에 null 여부를 체크합니다.
			while((str = br.readLine()) != null) 
				System.out.println(str);
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다.");
		} catch (IOException e) {
			System.out.println("파일 읽기 중 오류가 발생했습니다.");
		}
	}

}