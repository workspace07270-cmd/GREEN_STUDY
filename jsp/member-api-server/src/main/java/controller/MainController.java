package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;

/**
 * 메인 페이지 표시를 담당하는 컨트롤러입니다.
 * 전체 회원 목록을 조회하여 화면에 전달합니다.
 */
public class MainController implements Controller {

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<MemberDTO> list = MemberService.getInstance().selectAllMember();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		map.put("resulTime", sdf.format(new Date()));
		map.put("dateSize", list.size());
		map.put("list", list);
		
		return map;
	}

}