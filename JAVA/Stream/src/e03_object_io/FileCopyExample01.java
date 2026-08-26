package e03_object_io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyExample01 {

	public static void main(String[] args) {
		try (FileInputStream fis = new FileInputStream("img.jpg");
				FileOutputStream fos = new FileOutputStream("copy.jpg");) {
			long startTime = System.currentTimeMillis();
			byte[] buffer = new byte[1024];
			while(true) {
				int n = fis.read(buffer);
				if(n == -1) break;
				fos.write(buffer,0,n);
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



