package e02_echo_multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class EchoMultiServerMain {
	private static Vector<ServerWorker> list = new Vector<ServerWorker>();
	
	public static void removeWorker(ServerWorker worker) {
		list.remove(worker);//객체 비교 후 해당 스레드 제거
		System.out.println(list.size() + "명 접속 중입니다.");
	}
	

	public static void main(String[] args) {
		System.out.println("서버프로그램을 실행합니다............");

		// 1. 서버 오픈 - 포트번호 지정
		try (ServerSocket server = new ServerSocket(1234);) {
			while (true) {
				// 2. 클라이언트 접속 받음
				// 접속 정보는 Socket 클래스로 관리
				Socket client = server.accept();
				System.out.println(client.getInetAddress() + " 클라이언트 접속");
				// 3. 서버 스레드 생성
				ServerWorker worker = new ServerWorker(client);
				worker.start();
				list.add(worker);//리스트 추가
				System.out.println(list.size() + "명 접속 중입니다.");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}