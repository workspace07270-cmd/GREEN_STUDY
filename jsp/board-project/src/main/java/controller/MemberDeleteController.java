package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

/**
 * 회원 삭제를 담당하는 컨트롤러입니다.
 * 특정 회원의 고유 번호(no)를 받아 DB에서 삭제합니다.
 */
public class MemberDeleteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 삭제할 회원의 번호(no)를 파라미터에서 가져옵니다.
		String no = request.getParameter("no");
		
		// 2. MemberService를 통해 해당 번호의 회원을 삭제합니다.
		MemberService.getInstance().deleteMember(no);		
		
		// 3. 삭제 완료 후 메인 페이지로 리다이렉트하여 목록을 갱신합니다.
		return new ModelAndView("./main.do", true);
	}

}