package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

public class DeleteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String no = request.getParameter("no");
		
		MemberService.getInstance().deleteMember(no);
		
		return new ModelAndView("./main.do",true);
	}

}
