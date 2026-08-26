package controller;

import java.io.IOException;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

public class UpdateViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String no = request.getParameter("no");
		
		MemberDTO dto = MemberService.getInstance().selectMemberByNo(no);
		
		request.setAttribute("dto", dto);
		
		return new ModelAndView("member-update", false);
	}

}




