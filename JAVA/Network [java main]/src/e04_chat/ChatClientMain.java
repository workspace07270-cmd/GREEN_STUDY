package e04_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClientMain {

	public static void main(String[] args) {
		//1. 서버 접속 - 192.168.40.90
		try(Socket server = new Socket("127.0.0.1", 1234)){
			ClientWorker worker = new ClientWorker(server);
			worker.start();
			//2. 입출력 스트림 생성
			try(PrintWriter pw = new PrintWriter(server.getOutputStream());
				Scanner sc = new Scanner(System.in)){
				while(true) {
					//3. 데이터 입출력
					//3-1. 사용자로부터 메세지 입력 받음
					System.out.println("메세지 입력 > ");
					String str = sc.nextLine();
					//3-2. 서버에게 데이터를 보냄
					pw.println(str);
					pw.flush();
					//3-3. exit 입력 확인 후 종료
					if(str.equals("exit")) break;
					
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class ClientWorker extends Thread{
		private Socket server;
		
		public ClientWorker(Socket server) {
			this.server = server;
		}
		
		@Override
		public void run() {
			try(BufferedReader br = 
					new BufferedReader(
							new InputStreamReader(server.getInputStream()))){
				while(true) {
					String str = br.readLine();
					if(str == null) break;
					System.out.println(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}