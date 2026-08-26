package main;

import java.util.Scanner; // ctrl +shift + o

import service.StudentService;

 class StudentMain {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		StudentService service = new StudentService();
				
		while(true) {
		System.out.println("학적관리 프로그램");
		System.out.println("1. 학생 정보 등록");
		System.out.println("2. 학생 정보 삭제");
		System.out.println("3. 학생 정보 수정");
		System.out.println("4. 학생 정보 조회");
		System.out.println("5. 전체 학생정보 조회");
		System.out.println("0. 프로그램 종료");
		System.out.println("원하시는 메뉴 번호를 입력하시오");
		int no = sc.nextInt(); sc.nextLine();// 입력 버퍼에 남아있는 개행 문자 제거
		
		//0번 입력시 프로그램 종료
		if(no==0) {
			System.out.println("프로그램을 종료합니다");
			break;
		}
		//메뉴 번호
		switch(no) {
		case 1:
			service.appendStudentVO(sc);
			break;
		case 2:
			service.deleteStudentVO(sc);
			break;
		case 4:
			service.searchStudentVO(sc);
			break;
		case 3:
			service.updateStudentVO(sc);
			break;
		case 5:
			service.printAllStudentVO();
			break;
		}
		
	  }
		sc.close(); // 자원 반납
   }
}