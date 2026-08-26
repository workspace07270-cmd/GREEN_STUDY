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
 * [회원 검색 컨트롤러]
 * 사용자가 입력한 검색 조건과 검색어를 받아 회원 정보를 찾아주는 기능을 담당합니다.
 * 이 컨트롤러는 페이지 이동 대신 JSON 형식으로 데이터를 응답하는 AJAX 전용으로 설계되었습니다.
 */
public class MemberSearchController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 클라이언트(브라우저)에서 보낸 검색 파라미터를 읽어옵니다.
		// kind: 검색 조건 (예: 이름, 아이디 등), search: 실제 검색 키워드
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		
		// 2. 비즈니스 로직을 처리하는 Service 계층에 검색을 요청합니다.
		// 싱글톤 패턴으로 구현된 MemberService 인스턴스를 가져와 searchMembers 메서드를 호출합니다.
		List<MemberDTO> list = MemberService.getInstance().searchMembers(kind, search);	
		
		// 3. 결과를 JSON 형태로 브라우저에 돌려주기 위해 Map 객체에 데이터를 담습니다.
		// Map은 키(Key)와 값(Value)의 쌍으로 데이터를 관리합니다.
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "검색 완료");      // 처리 결과 메시지
		map.put("kind", kind);           // 사용자가 선택했던 검색 조건
		map.put("search", search);       // 사용자가 입력했던 검색어
		map.put("list", list);           // DB에서 검색해온 회원 목록 데이터
		
		// 4. Map에 담긴 데이터를 JSON 형식의 문자열로 변환합니다.
		// JSONObject 라이브러리를 사용하면 자바 객체를 JSON 문자열로 쉽게 바꿀 수 있습니다.
		JSONObject json = new JSONObject(map);
		
		// 5. 변환된 JSON 문자열을 응답 본문(Response Body)에 씁니다.
		// 이를 통해 브라우저의 JavaScript(AJAX)가 이 데이터를 받아 처리하게 됩니다.
		response.getWriter().println(json.toString());
		
		// AJAX 통신에서는 새로운 HTML 페이지로 이동하지 않고 데이터만 보내기 때문에,
		// ModelAndView 객체(페이지 정보)를 반환하지 않고 null을 반환합니다.
		return null;
	}

}