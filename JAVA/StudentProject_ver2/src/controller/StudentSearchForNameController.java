package controller;

import java.util.Scanner;

import service.StudentService;
import vo.StudentVO;

/**
 * 이름을 입력받아 해당 학생 정보를 검색하여 출력하는 기능을 담당하는 컨트롤러
 */
public class StudentSearchForNameController extends Controller {
	@Override
	public void execute(Scanner sc) {
		System.out.println("학생정보 조회 작업을 시작합니다......");

		// 1. 검색할 이름 입력
		System.out.print("조회할 학생 이름을 입력하세요 : ");
		String name = sc.nextLine();

		// 2. 서비스를 통해 이름으로 학생 검색
		StudentVO vo = StudentService.getInstance().searchStudentVOForName(name);
		
		// 3. 검색 결과 출력
		if(vo == null)
			System.out.println("해당 검색결과가 없습니다.");
		else
			vo.printInfo();
	}
}





