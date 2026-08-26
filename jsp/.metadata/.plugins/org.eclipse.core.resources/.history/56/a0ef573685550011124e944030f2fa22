package controller;

import java.io.IOException;
import java.util.List;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

public class MainController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<MemberDTO> list = MemberService.getInstance().selectAllMember();
		request.setAttribute("list", list);
		return new ModelAndView("main", false);
	}

}


