import java.util.Scanner;

/**
 * 이 클래스는 switch 문의 Fall-through(흘러내리기) 특징과 Scanner를 이용한 입력을 설명합니다.
 */
public class E05_Switch {
	/*
	 *  break가 없는 switch 문을 활용하여 재료가 누적되는 과정을 보여줍니다.
	 * 	- 밀크 커피 : 프림 + 설탕 + 커피 + 뜨거운 물
	 * 	- 설탕 커피 : 설탕 + 커피 + 뜨거운 물
	 * 	- 블랙 커피 : 커피 + 뜨거운 물
	 */
	public static void main(String[] args) {
		// 사용자로부터 입력을 받기 위해 Scanner 객체 생성
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. 밀크 커피");
		System.out.println("2. 설탕 커피");
		System.out.println("3. 블랙 커피");
		System.out.print("원하시는 번호를 입력하세요 : ");
		
		// sc.nextInt(): 사용자가 입력한 정수를 읽어옵니다.
		int no = sc.nextInt();
		
		// break가 없으면 해당 case부터 아래 case들을 모두 실행하는 특징(Fall-through)을 이용
		switch(no) {
		case 1:
			System.out.println("프림 추가");
		case 2:
			System.out.println("설탕 추가");
		case 3:
			System.out.println("커피 추가");
			System.out.println("뜨거운물 추가");
		}
		
		sc.close(); // Scanner 리소스 닫기
	}
}