package e04_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServerMain {
	private static Vector<ServerWorker> list = new Vector<ServerWorker>();

	private static void removeWorker(ServerWorker worker) {
		list.remove(worker);// 객체 비교 후 해당 스레드 제거
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
				list.add(worker);// 리스트 추가
				System.out.println(list.size() + "명 접속 중입니다.");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class ServerWorker extends Thread {
		private Socket client;
		private BufferedReader br;
		private PrintWriter pw;

		public ServerWorker(Socket client) {
			this.client = client;
			try {
				br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				pw = new PrintWriter(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		public void sendMessage(String msg) {
			pw.println(msg);
			pw.flush();
		}

		@Override
		public void run() {
			try {
				while (true) {
					String msg = br.readLine();
					if (msg == null || msg.equals("exit")) {
						System.out.println("클라이언트가 접속 종료했습니다.");
						break;
					}
					//클라이언트가 보낸 메세지를 전체 클라이언트에게 전달
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println(client.getInetAddress() + " 접속 종료");
					removeWorker(this);
					if (client != null)
						client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}