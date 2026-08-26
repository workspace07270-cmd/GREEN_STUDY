package controller;

import java.io.IOException;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

public class UpdateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String nick = request.getParameter("nick");

		MemberService.getInstance().updateMember(
				new MemberDTO(no, null, passwd, name, nick));
		
		return new ModelAndView("./main.do", true);
	}

}