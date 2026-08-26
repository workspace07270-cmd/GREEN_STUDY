package controller;

/**
 * 사용자의 요청 경로(command)에 따라 적절한 컨트롤러 객체를 생성하여 반환하는 클래스입니다.
 * 싱글톤 패턴으로 구현되어 프로그램 전체에서 하나의 매핑 정보만 관리합니다.
 */
public class HandlerMapping {
	// 1. 자기 자신의 인스턴스를 하나만 생성합니다.
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	}

	// 2. 외부에서 인스턴스를 얻을 수 있는 메서드입니다.
	public static HandlerMapping getInstance() {
		if (instance == null)
			instance = new HandlerMapping();
		return instance;
	}

	/**
	 * 요청받은 command(경로)에 따라 실행할 컨트롤러 객체를 만들어 줍니다.
	 * @param command 사용자 요청 경로 (예: main.do)
	 * @return 실행할 컨트롤러 객체
	 */
	public Controller createController(String command) {
		Controller controller = null;
		
		// 요청 주소에 따라 적절한 컨트롤러를 생성합니다.
		switch (command) {
		case "main.do":
			controller = new MainController();
			break;
		case "registerView.do":
			controller = new MemberRegisterViewController();
			break;
		case "checkId.do":
			controller = new CheckIdController();
			break;
		case "register.do":
			controller = new RegisterController();
			break;
		case "loginView.do":
			controller = new LoginViewController();
			break;
		case "login.do":
			controller = new LoginController();
			break;
		case "loginOut.do":
			controller = new LogoutController();
			break;
		case "delete.do":
			controller = new DeleteController();
			break;
		case "search.do":
			controller = new SearchController();
			break;
		case "updateView.do":
			controller = new UpdateViewController();
			break;
		case "update.do":
			controller = new UpdateController();
			break;
		}
		return controller;
	}
}




























