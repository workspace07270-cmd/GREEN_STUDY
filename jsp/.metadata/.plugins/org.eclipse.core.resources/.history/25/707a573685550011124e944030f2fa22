package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

/**
 * 회원 검색을 테스트하는 컨트롤러입니다. (현재는 AJAX 응답 테스트용)
 */
public class SearchController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 검색 조건(kind)과 검색어(search)를 파라미터에서 가져옵니다.
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		
		// 2. 결과 메시지를 담을 Map 객체를 생성합니다.
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		List<MemberDTO> list = MemberService.getInstance().searchMembers(kind, search);	
		
		map.put("msg", "검색 완료");
		map.put("kind", kind);
		map.put("search", search);
		map.put("list", list);
		
		// 3. Map을 JSON 객체로 변환하여 응답 본문에 씁니다.
		JSONObject json = new JSONObject(map);
		response.getWriter().println(json.toString());
		
		// 직접 응답을 작성했으므로 null을 반환합니다.
		return null;
	}

}