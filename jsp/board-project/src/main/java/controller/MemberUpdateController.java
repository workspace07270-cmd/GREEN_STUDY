package controller;

import java.io.IOException;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

/**
 * [실제 회원 정보 수정 처리 컨트롤러]
 * 수정 폼에서 입력한 데이터를 받아 DB에 업데이트하고 메인 페이지로 보냅니다.
 */
public class MemberUpdateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 사용자가 수정 폼에 입력해서 보낸 데이터들을 파라미터에서 읽어옵니다.
		int no = Integer.parseInt(request.getParameter("no")); // 회원의 고유 번호 (숫자로 변환)
		String passwd = request.getParameter("passwd");       // 변경할 비밀번호
		String name = request.getParameter("name");           // 변경할 이름
		String nick = request.getParameter("nick");           // 변경할 닉네임
		
		// 2. 읽어온 데이터들을 MemberDTO 객체 하나로 묶습니다.
		// (수정 시에는 아이디는 바꾸지 않으므로 null로 처리)
		MemberDTO memberDTO = new MemberDTO(no, null, passwd, name, nick);
		
		// 3. 서비스 계층의 updateMember 메서드를 호출하여 실제 DB 수정을 진행합니다.
		MemberService.getInstance().updateMember(memberDTO);
		
		// 4. 수정이 끝난 후에는 메인 페이지(main.do)로 '리다이렉트(true)' 시킵니다.
		// 데이터가 변경된 후에는 페이지 이동을 통해 중복 요청을 방지하는 것이 좋습니다.
		return new ModelAndView("./main.do", true);
	}

}





