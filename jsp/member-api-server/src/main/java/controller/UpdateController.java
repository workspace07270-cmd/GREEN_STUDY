package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;

/**
 * [실제 회원 정보 수정 처리 컨트롤러]
 * 수정 폼에서 입력한 데이터를 받아 DB에 업데이트하고 메인 페이지로 보냅니다.
 */
public class UpdateController implements Controller {

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		//axios로 보낸 body 영역 데이터를 뽑는 부분
		//json 형태의 텍스트로 보냄
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(request.getReader())){
			String line = null;
			while((line = br.readLine()) != null)
				sb.append(line);
		}
		System.out.println(sb.toString());
		JSONObject json = new JSONObject(sb.toString());
		int no = json.getInt("no");
		String passwd = json.getString("passwd");
		String name = json.getString("userName");
		String nick = json.getString("nickName");		
		
		MemberDTO memberDTO = new MemberDTO(no, null, passwd, name, nick);
		System.out.println(memberDTO);
		int result = MemberService.getInstance().updateMember(memberDTO);

		if(result == 1) {
			map.put("result", 1); 
			map.put("msg", "회원 정보 수정 성공.");
		} else {
			map.put("result", 0); 
			map.put("msg", "회원 정보 수정 실패.");
		}
		return map;
	}

}










