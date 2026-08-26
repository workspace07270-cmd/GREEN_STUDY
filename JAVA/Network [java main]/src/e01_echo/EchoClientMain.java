package e01_echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientMain {

	public static void main(String[] args) {
		//1. 서버 접속 - 192.168.40.90
		try(Socket server = new Socket("127.0.0.1", 1234)){
			//2. 입출력 스트림 생성
			try(PrintWriter pw = new PrintWriter(server.getOutputStream());
				BufferedReader br = new BufferedReader(
						new InputStreamReader(server.getInputStream()));){
				//3. 데이터 입출력
				//3-1. 사용자가 서버에게 데이터를 보냄
				pw.println("서버야 안녕!");
				pw.flush();
				//3-2. 서버가 사용자에게 보낸 데이터를 받음
				String msg = br.readLine();
				System.out.println("서버가 보낸 메세지 : " + msg);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}