package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;



import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;


/**
 * 회원 검색을 테스트하는 컨트롤러입니다. (현재는 AJAX 응답 테스트용)
 */
public class SearchController implements Controller {

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object>map = new HashMap<String, Object>();
		
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		
		List<MemberDTO> list = MemberService.getInstance().searchMembers(kind, search);	
		
		map.put("msg", "검색 완료");
		map.put("kind", kind);
		map.put("search", search);
		map.put("list", list);
		
	
		return map;
	}

}