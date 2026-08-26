package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

/**
 * 로그아웃을 처리하는 컨트롤러입니다.
 */
public class LogoutController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 현재 세션을 무효화(삭제)하여 로그인 정보를 지웁니다.
		request.getSession().invalidate();
		
		// 2. 로그아웃 후 메인 페이지로 이동합니다. (Redirect 방식)
		return new ModelAndView("./main.do", true);
	}

}