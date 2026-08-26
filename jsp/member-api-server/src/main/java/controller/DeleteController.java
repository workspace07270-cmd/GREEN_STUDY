package controller;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;


public class DeleteController implements Controller {

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object>map = new HashMap<String, Object>();
		
		String no = request.getParameter("no");
		
		int result= MemberService.getInstance().deleteMember(no);
		
		if(result == 1) {
			map.put("result", 1);
			map.put("msg", "사용할 수 있는 아이디 입니다.");
		}else {
			map.put("result", 0);
			map.put("msg", "중복된 아이디 입니다.");
		}
		return map;
	}

}
