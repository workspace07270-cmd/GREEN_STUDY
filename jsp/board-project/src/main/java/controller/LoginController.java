package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;
import view.ModelAndView;

/**
 * 로그인을 처리하는 컨트롤러입니다.
 * 사용자가 입력한 아이디와 비밀번호를 검증하고 세션을 생성합니다.
 */
public class LoginController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 로그인 폼에서 전달된 아이디와 비밀번호를 받습니다.
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		// 2. MemberService를 통해 DB에 해당 아이디와 비밀번호가 맞는지 확인합니다.
		// 비밀번호는 DB 내부에서 암호화(SHA2)되어 비교됩니다.
		MemberDTO member = MemberService.getInstance().login(id, passwd);
		
		if(member == null) {
			// 3-1. 로그인 실패 시: 경고창을 띄우고 이전 페이지(로그인 폼)로 돌아갑니다.
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('로그인 실패 하셨습니다.\\n아이디와 비밀번호를 확인해 주세요');");
			pw.println("history.back();"); // 브라우저의 이전 기록으로 이동
			pw.println("</script>");
			
			return null; // 직접 스크립트로 응답했으므로 ModelAndView는 필요 없음
		} else {
			// 3-2. 로그인 성공 시: 세션(Session)에 회원 정보를 저장합니다.
			// 세션에 저장된 정보는 브라우저를 닫거나 로그아웃 전까지 유지됩니다.
			HttpSession session = request.getSession();
			session.setAttribute("user", member);
			
			// 메인 페이지(main.do)로 리다이렉트합니다.
			return new ModelAndView("./main.do", true);
		}
	}

}