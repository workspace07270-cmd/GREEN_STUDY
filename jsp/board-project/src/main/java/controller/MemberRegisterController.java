package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

/**
 * 회원가입 처리를 담당하는 컨트롤러입니다.
 * 사용자가 입력한 정보를 DB에 저장합니다.
 */
public class MemberRegisterController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 회원가입 폼에서 입력한 데이터들을 가져옵니다.
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String nick = request.getParameter("nick");
		
		// 2. 입력받은 데이터를 MemberDTO 객체에 담아서 서비스 클래스로 전달합니다.
		// (번호는 DB에서 자동 증가하므로 0으로 설정)
		int result = MemberService.getInstance().insertMember(
				new MemberDTO(0, id, passwd, name, nick));
		
		// 3. 회원가입 결과에 따라 처리를 분기합니다.
		if(result == 0) {
			// 가입 실패 시: 알림창을 띄우고 이전 페이지로 이동
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('회원가입에 실패 하셨습니다.\\n입력하신 정보를 확인해 주세요');");
			pw.println("history.back();");
			pw.println("</script>");
			
			return null;
		} else {
			// 가입 성공 시: 메인 페이지로 이동 (Redirect)
			return new ModelAndView("./main.do", true);
		}
	}

}