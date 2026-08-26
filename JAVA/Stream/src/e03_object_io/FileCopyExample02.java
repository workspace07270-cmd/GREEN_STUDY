package e03_object_io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyExample02 {

	public static void main(String[] args) {
		// 1. 복사할 원본파일(A)을 스트림으로 연결 --> FileInputStream
		// 2. 복사할 곳 파일(B)을 스트림으로 연결 --> FileOutputStream
		// 3. A에서 데이터를 읽고, B에다가 출력 --> 3번과정을 반복해서 끝나면 파일 복사가 완료되는 시점
		try (FileInputStream fis = new FileInputStream("img.jpg");
				FileOutputStream fos = new FileOutputStream("copy.jpg");) {
			long startTime = System.currentTimeMillis();
			while(true) {
				int n = fis.read();
				if(n == -1) break;
				fos.write(n);
			}
			long endTime = System.currentTimeMillis();
			fos.flush();
			System.out.println("파일 복사 완료 : " + (endTime - startTime) / 1000);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}



