package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;

public class RegisterController implements Controller {

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(request.getReader())){
			String line = null;
			while((line = br.readLine()) != null)
				sb.append(line);
		}
		System.out.println(sb.toString());
		JSONObject json = new JSONObject(sb.toString());

		String id = json.getString("id");
		String passwd = json.getString("passwd");
		String name = json.getString("userName");
		String nick = json.getString("nickName");		
		
		int result = MemberService.getInstance().insertMember(
				new MemberDTO(0, id, passwd, name, nick));
		
			map.put("resultCode", result);
			map.put("msg",result == 0 ? "회원 정보 추가 실패." : "회원 정보 추가 성공.");
		
		return map;
	}

}