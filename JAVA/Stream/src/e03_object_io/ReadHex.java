package e03_object_io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadHex {

	public static void main(String[] args) {
		try(FileInputStream fis = new FileInputStream("data.dat");){
			int data = 0;
			
			while(true) {
				data = fis.read();
				if(data == -1) break;
				System.out.print(String.format("%02X ", data));
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}