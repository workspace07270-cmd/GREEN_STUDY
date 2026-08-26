package e03_object_io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReadTest {

	public static void main(String[] args) {
		try(FileInputStream fis = new FileInputStream("data.dat");
			DataInputStream dis = new DataInputStream(fis)){
			
			System.out.println(dis.readInt());
			System.out.println(dis.readDouble());
			System.out.println(dis.readChar());
			System.out.println(dis.readBoolean());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}






