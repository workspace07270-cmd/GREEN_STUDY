package controller;

import java.util.Scanner;

import service.StudentService;

/**
 * 학번을 입력받아 해당 학생 정보를 삭제하는 기능을 담당하는 컨트롤러
 */
public class StudentDeleteController extends Controller {
	@Override
	public void execute(Scanner sc) {
		System.out.println("학생정보 삭제를 시작합니다....");

		// 1. 삭제할 학번 입력
		System.out.print("삭제할 학생의 학번 : ");
		String no = sc.nextLine();
		
		// 2. 서비스를 통해 삭제 수행 및 결과 확인
		if(StudentService.getInstance().deleteStudentVO(no)){
			System.out.println("학생정보 삭제가 완료되었습니다.");
		}else {
			System.out.println("삭제할 학생 정보가 없습니다.");
		}
	}
}







