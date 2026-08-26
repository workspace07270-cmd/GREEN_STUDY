package e02_echo_multi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerWorker extends Thread {
	private Socket client;

	public ServerWorker(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try (PrintWriter pw = new PrintWriter(client.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));) {
			while (true) {
				// 1. 데이터 입출력
				// 1-1. 사용자가 보낸 데이터를 받음
				String msg = br.readLine();
				if(msg == null || msg.equals("exit")) {
					System.out.println("클라이언트가 접속 종료했습니다.");
					break;
				}
				// 1-2. 서버가 사용자에게 데이터를 보냄
				pw.println(msg);
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				System.out.println(client.getInetAddress() + " 접속 종료");
				EchoMultiServerMain.removeWorker(this);
				
				if(client != null) client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}