package controller;

/**
 * 사용자의 메뉴 선택(no)에 따라 적절한 컨트롤러 객체를 생성하여 반환하는 클래스 싱글톤 패턴으로 구현되어 프로그램 전체에서 하나의 매핑
 * 정보만 관리함
 */
public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		if (instance == null)
			instance = new HandlerMapping();
		return instance;
	}

	public Controller createController(String command) {
		Controller controller = null;
		switch (command) {
		case "main.do":
			controller = new StudentAllPrintController();
			break;
		case "insertView.do":
			controller = new StudentInertViewController();
			break;
		case "insert.do":
			controller = new StudentInsertController();
			break;
		case "delete.do":
			controller = new StudentDeleteController();
			break;
		case "updateView.do":
			controller = new StudentUpdateViewController();
			break;
		case "update.do":
			controller = new StudentUpdateController();
			break;
		case "search.do":
			controller = new StudentSearchForNameController();
			break;
		}
		return controller;
	}
}








