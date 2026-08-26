package controller;

import java.util.ArrayList;
import java.util.Scanner;

import service.StudentService;
import vo.StudentVO;

/**
 * 전체 학생 정보를 조회하여 출력하는 기능을 담당하는 컨트롤러
 */
public class StudentAllPrintController implements Controller {
	@Override
	public void execute(Scanner sc) {
		// 싱글톤인 StudentService로부터 데이터(배열)를 직접 받아옴
		//StudentVO[] arr = StudentService.getInstance().getArr();
		ArrayList<StudentVO> list = StudentService.getInstance().getList();
		
		System.out.println("전체 학생정보를 조회합니다.............");
		for (StudentVO vo : list) {
			// 배열에서 데이터가 들어있는 부분까지만 출력하고 종료
			if(vo == null) break;
			vo.printInfo();			
		}
	}
}