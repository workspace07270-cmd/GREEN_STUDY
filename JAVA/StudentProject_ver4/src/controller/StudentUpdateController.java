package controller;

import java.util.Scanner;

import service.StudentService;
import vo.StudentVO;

/**
 * 학번을 입력받아 해당 학생의 정보를 수정하는 기능을 담당하는 컨트롤러
 */
public class StudentUpdateController implements Controller {
	@Override
	public void execute(Scanner sc) {
		System.out.println("학생정보 수정을 시작합니다....");

		// 1. 수정할 학번 입력
		System.out.print("수정할 학생의 학번 : ");
		String no = sc.nextLine();
		
		// 2. 학번으로 해당 학생 객체 가져오기
		StudentVO vo = StudentService.getInstance().searchStudentVO2(no);
		
		// 3. 존재 여부 확인
		if(vo == null) {
			System.out.println("수정할 학생 정보가 없습니다.");
			return;
		}
			
		// 4. 새로운 정보 입력 (학번은 final이므로 수정 불가)
		System.out.print("수정할 학생의 이름 : ");
		String name = sc.nextLine();
		System.out.print("수정할 학생의 학과명 : ");
		String majorName = sc.nextLine();
		System.out.print("수정할 학생의 평점 : ");
		double score = sc.nextDouble();
		sc.nextLine(); // 버퍼 비우기

		// 5. 객체 정보 업데이트
		vo.updateStudentVO(name, majorName, score);
		System.out.println("학생정보 수정이 완료되었습니다.");
	}
}






