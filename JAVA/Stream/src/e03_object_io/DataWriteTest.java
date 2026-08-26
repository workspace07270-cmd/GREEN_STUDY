package e03_object_io;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataWriteTest {

	public static void main(String[] args) {
		//저장할 데이터 4개건 생성
		int n = 100;
		double pi = 3.1415;
		char ch = 'A';//65
		boolean flag = true;
		
		//스트림 초기화
		try(FileOutputStream fos = new FileOutputStream("data.dat");
			DataOutputStream dos = new DataOutputStream(fos);){
			//출력
			dos.writeInt(n);
			dos.writeDouble(pi);
			dos.writeChar(ch);
			dos.writeBoolean(flag);
			
			System.out.println("파일 출력 완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}






