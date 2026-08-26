package controller;

import java.io.IOException;
import java.util.HashMap;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;

public class CheckIdController implements Controller {

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		
		MemberDTO member = MemberService.getInstance().selectMemberById(id);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if(member == null) {
			map.put("result", 1); // 성공 코드 (1: 사용 가능)
			map.put("msg", "사용할 수 있는 아이디 입니다.");
		} else {
			map.put("result", 0); // 실패 코드 (0: 중복됨)
			map.put("msg", "중복된 아이디 입니다.");
		}
				
		return map;
	}

}

