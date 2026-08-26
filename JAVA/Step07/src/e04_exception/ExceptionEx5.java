package e04_exception;

import java.util.Scanner;

public class ExceptionEx5 {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
	try {
		System.out.println("숫자입력 : "); 
		int n1 = sc.nextInt();
		System.out.println("숫자입력 : ");
		int n2 = sc.nextInt();
		
		if(n2 == 0)
			throw new Exception("0으로 나눌 수 없습니다");// 원하는 시점에서 Exception을 강제로 발생 시킨다
		
		System.out.println(n1 / n2);
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}	
}
}
