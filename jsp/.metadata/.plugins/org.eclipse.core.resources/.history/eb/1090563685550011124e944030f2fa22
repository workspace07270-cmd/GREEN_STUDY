package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;
import view.ModelAndView;

public class LoginController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result = 0;
		String id= request.getParameter("id");
		String passwd= request.getParameter("passwd");
		
		MemberDTO member = MemberService.getInstance().login(id, passwd);
		
		if(member == null) {
			response.setContentType("text/html;charset=UTF-=8");
		//실패하면 이전페이지
		PrintWriter pw = response.getWriter();
		
		pw.println("<script>");
		pw.println("alert('로그인에 실패 하셨습니다.\\n아이디와 비밀번호를 확인해 주세요');");
		pw.println("history.back();");
		pw.println("</script>");
		
		return null;
		}else {
		//성공하면 메인페이지
		HttpSession Session = request.getSession();
		Session.setAttribute("member", member);
		return new ModelAndView("./main.do", true);
			}
		}
		
	}