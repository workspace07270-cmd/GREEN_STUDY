package e02_echo_multi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClientMain {

	public static void main(String[] args) {
		//1. 서버 접속 - 192.168.40.90  127.0.0.1
		try(Socket server = new Socket("127.0.0.1", 1234)){
			//2. 입출력 스트림 생성
			try(PrintWriter pw = new PrintWriter(server.getOutputStream());
				BufferedReader br = new BufferedReader(
						new InputStreamReader(server.getInputStream()));
				Scanner sc = new Scanner(System.in)){
				while(true) {
					//3. 데이터 입출력
					//3-1. 사용자로부터 메세지 입력 받음
					System.out.print("메세지 입력 > ");
					String str = sc.nextLine();
					//3-2. 서버에게 데이터를 보냄
					pw.println(str);
					pw.flush();
					//3-3. exit 입력 확인 후 종료
					if(str.equals("exit")) break;
					//3-4. 서버가 보낸 내용 받아서 출력
					String msg = br.readLine();
					System.out.println("서버가 보낸 메세지 : " + msg);
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}