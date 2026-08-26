package controller;

import java.io.IOException;
import java.util.Scanner;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import view.ModelAndView;

/**
 * 학번을 입력받아 해당 학생 정보를 삭제하는 기능을 담당하는 컨트롤러
 */
public class StudentDeleteController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String no = request.getParameter("no");
		StudentService.getInstance().deleteStudentVO(no);
		return new ModelAndView(request.getContextPath()+"/main.do", true);
	}
}
