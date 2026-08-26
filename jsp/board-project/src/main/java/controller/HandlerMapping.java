package controller;

/**
 * [핸들러 매핑 클래스]
 * 사용자의 URL 요청(예: main.do)을 보고 어떤 컨트롤러가 일을 해야 할지 결정해주는 '안내소' 역할을 합니다.
 * '팩토리 패턴'을 응용하여 요청에 맞는 객체를 생성해줍니다.
 */
public class HandlerMapping {
	// 싱글톤 패턴: 객체를 하나만 생성해서 공유합니다.
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		if (instance == null)
			instance = new HandlerMapping();
		return instance;
	}

	/**
	 * [컨트롤러 생성 메서드]
	 * 사용자가 보낸 주소(command)에 따라 그 일을 처리할 전담 컨트롤러 객체를 만들어 반환합니다.
	 * @param command 사용자 요청 주소
	 * @return 해당 요청을 처리할 컨트롤러 객체
	 */
	public Controller createController(String command) {
		Controller controller = null;
		
		// 스위치 문을 통해 주소별로 컨트롤러를 매칭합니다.
		switch (command) {
		case "main.do": // 메인 페이지 이동
			controller = new MainController();
			break;
		case "registerView.do": // 회원가입 폼 이동
			controller = new MemberRegisterViewController();
			break;
		case "checkId.do": // 아이디 중복 확인 (AJAX)
			controller = new CheckIdController();
			break;
		case "register.do": // 실제 회원가입 처리
			controller = new MemberRegisterController();
			break;
		case "loginView.do": // 로그인 폼 이동
			controller = new LoginViewController();
			break;
		case "login.do": // 실제 로그인 처리
			controller = new LoginController();
			break;
		case "logout.do": // 로그아웃 처리
			controller = new LogoutController();
			break;
		case "delete.do": // 회원 삭제 처리
			controller = new MemberDeleteController();
			break;
		case "search.do": // 회원 검색 처리 (AJAX)
			controller = new MemberSearchController();
			break;
		case "updateView.do": // 회원 수정 폼 이동 (데이터 조회 포함)
			controller = new MemberUpdateViewController();
			break;
		case "update.do": // 실제 회원 정보 수정 처리
			controller = new MemberUpdateController();
			break;
		case "boardWriteView.do":
			controller = new BoardWriteViewController();
			break;
		case "boardWrite.do":
			controller = new BoardWriteController();
			break;
		case "boardView.do":
			controller = new BoardViewController();
			break;
		case "boardLike.do":
			controller = new BoardLikeController();
			break;
		case "boardHate.do":
			controller = new BoardHateController();
			break;
		case "boardCommentInsert.do":
			controller = new BoardCommentInsertController();
			break;
		case "boardCommentLikeHate.do":
			controller = new BoardCommentLikeHateController();
		case "boardUpdateView.do":
			controller = new BoardUpdateViewController();
		}
		return controller;
	}
}




