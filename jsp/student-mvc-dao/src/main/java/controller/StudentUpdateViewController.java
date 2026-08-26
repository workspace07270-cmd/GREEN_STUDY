package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import view.ModelAndView;
import vo.StudentVO;

public class StudentUpdateViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정할 학번을 받고
		String no = request.getParameter("no");
		//Service 클래스에서 해당 학생정보를 검색해서 가져옴
		StudentVO vo = StudentService.getInstance().searchStudentVO2(no);
		//request 영역에서 저장후 student_update.jsp로 이동
		request.setAttribute("vo", vo);
		return new ModelAndView("student_update", false);
	}

}
