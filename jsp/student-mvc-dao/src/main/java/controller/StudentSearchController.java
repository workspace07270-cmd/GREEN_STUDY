package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

public class StudentSearchController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    // 1) 파라미터 읽기
	    String name = request.getParameter("name");

	    // 2) 서비스 호출
	    vo.StudentVO vo = null;
	    if (name != null && !name.trim().isEmpty()) {
	        vo = service.StudentService.getInstance().searchStudentVOForName(name.trim());
	    }

	    // 3) request에 저장
	    request.setAttribute("searchKeyword", name);
	    request.setAttribute("result", vo); // vo가 null이면 결과 없음

	    // 4) 뷰로 포워드
	    return new view.ModelAndView("search_result", false);
	}

}
