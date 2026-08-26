package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

/**
 * 회원가입 화면으로 이동시켜주는 컨트롤러입니다.
 */
public class MemberRegisterViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 'member-register.jsp' 화면을 보여주도록 설정 (Forward 방식)
		return new ModelAndView("member-register", false);
	}

}