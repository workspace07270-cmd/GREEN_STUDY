package controller;

import java.io.IOException;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import view.ModelAndView;

/**
 * [회원 수정 페이지 이동 컨트롤러]
 * 수정하고 싶은 회원의 기존 정보를 DB에서 가져와서 수정 폼(JSP)에 전달합니다.
 */
public class MemberUpdateViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. URL 파라미터로 넘어온 회원의 고유 번호(no)를 읽어옵니다.
		String no = request.getParameter("no");
		
		// 2. 서비스 계층을 통해 해당 번호를 가진 회원의 전체 정보를 조회합니다.
		// (수정 폼에 기존 이름, 닉네임 등을 미리 채워두기 위함)
		MemberDTO dto = MemberService.getInstance().selectMemberByNo(no);
		
		// 3. 조회된 결과(DTO)를 request 영역에 저장합니다. 
		// 이렇게 담아두어야 이동한 JSP 페이지에서 \${dto.userName} 처럼 꺼내 쓸 수 있습니다.
		request.setAttribute("dto", dto);
		
		// 4. member-update.jsp 페이지로 포워딩(false)하여 이동합니다.
		return new ModelAndView("member-update", false);
	}

}




