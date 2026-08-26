package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) {
		System.out.println("서버프로그램을 실행합니다............");
		
		//1. 서버 오픈 - 포트번호 지정
		try(ServerSocket server = new ServerSocket(1234);){
			//2. 클라이언트 접속 받음
			//	 접속 정보는 Socket 클래스로 관리
			try(Socket client = server.accept()){
				System.out.println(client.getInetAddress() + " 클라이언트 접속");
				//3. 입출력 스트림 생성
				try(PrintWriter pw = new PrintWriter(client.getOutputStream());
					BufferedReader br = new BufferedReader(
							new InputStreamReader(client.getInputStream()));){
					//4. 데이터 입출력
					//4-1. 사용자가 보낸 데이터를 받음
					String msg = br.readLine();
					System.out.println("사용자가 보낸 메세지 : " + msg);
					//4-2. 서버가 사용자에게 데이터를 보냄
					pw.println("고객님 안녕하세요!");
					pw.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}