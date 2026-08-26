package controller;

import java.util.Scanner;
import service.StudentService;
import vo.StudentVO;

/**
 * 사용자로부터 학생 정보를 입력받아 시스템에 등록하는 기능을 담당하는 컨트롤러
 */
public class StudentInsertController extends Controller {
	@Override
	public void execute(Scanner sc) {
		System.out.println("학생정보등록을 시작합니다......");
		
		// 1. 학번 입력 및 중복 체크
		// 서비스 클래스의 싱글톤 인스턴스를 활용하여 기존 데이터 확인
		System.out.print("등록할 학생의 학번 : ");
		String no = sc.nextLine();
		while (true) {
			if (StudentService.getInstance().searchStudentVO(no) == -1)
				break; // 중복이 없으면 루프 탈출
			System.out.println("학번이 중복되었습니다.");
			System.out.print("학번을 다시 입력해주세요 : ");
			no = sc.nextLine();
		}

		// 2. 나머지 학생 정보 입력
		System.out.print("등록할 학생의 이름 : ");
		String name = sc.nextLine();
		System.out.print("등록할 학생의 학과명 : ");
		String majorName = sc.nextLine();
		System.out.print("등록할 학생의 평점 : ");
		double score = sc.nextDouble();
		sc.nextLine(); // 입력 버퍼 비우기
		
		// 3. VO 객체를 생성하여 서비스에 등록 요청
		boolean result = StudentService.getInstance()
				.appendStudentVO(new StudentVO(no, name, majorName, score));
		
		// 4. 처리 결과에 따른 메시지 출력
		if(result) {
			System.out.println("학생 정보 등록이 완료되었습니다.");
		} else {
			System.out.println("학생 정보 등록에 실패하였습니다. (저장 공간 부족)");
		}
	}
}