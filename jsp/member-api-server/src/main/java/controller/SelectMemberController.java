package controller;

import java.io.IOException;
import java.util.HashMap;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;

public class SelectMemberController implements Controller {

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String no = request.getParameter("no");
		MemberDTO member = MemberService.getInstance().selectMemberByNo(no);
		map.put("resultCode", member != null ? 1 : 0);
		if(member != null)
			map.put("member", member);
		
		return map;
	}

}








