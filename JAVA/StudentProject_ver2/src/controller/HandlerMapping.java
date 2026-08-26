package controller;

/**
 * 사용자의 메뉴 선택(no)에 따라 적절한 컨트롤러 객체를 생성하여 반환하는 클래스
 * 싱글톤 패턴으로 구현되어 프로그램 전체에서 하나의 매핑 정보만 관리함
 */
public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {	}

	public static HandlerMapping getInstance() {
		if(instance == null)
			instance = new HandlerMapping();
		return instance;
	}
	
	/**
	 * 메뉴 번호에 해당하는 작업을 하는 컨트롤러를 생성해서 리턴하는 메서드
	 * @param no 사용자가 입력한 메뉴 번호
	 * @return 실행할 컨트롤러 객체 (해당 번호가 없으면 null 반환)
	 */
	public Controller createController(int no) {
		Controller controller = null;
		switch(no) {
		case 1:
			// 학생 정보 등록을 담당하는 컨트롤러 생성
			controller = new StudentInsertController();
			break;
		case 2:
			// 학생 정보 삭제를 담당하는 컨트롤러 생성
			controller = new StudentDeleteController();
			break;
		case 3:
			controller = new StudentUpdateController();
			break;
		case 4:
			controller = new StudentSearchForNameController();
			break;
		case 5:
			// 전체 학생 정보 출력을 담당하는 컨트롤러 생성
			controller = new StudentAllPrintController();
			break;
		}
		return controller;
	}
}