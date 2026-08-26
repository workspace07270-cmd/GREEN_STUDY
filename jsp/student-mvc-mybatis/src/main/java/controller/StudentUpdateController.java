package controller;

import java.io.IOException;
import java.util.Scanner;

import dto.StudentDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import view.ModelAndView;

/**
 * 학번을 입력받아 해당 학생의 정보를 수정하는 기능을 담당하는 컨트롤러
 */
public class StudentUpdateController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정할 데이터 받아옴
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String majorName = request.getParameter("majorName");
		double score = Double.parseDouble(request.getParameter("score"));
		
		StudentService.getInstance().updateStudent(
				new StudentDTO(no, name, majorName, score));
		
		return new ModelAndView("main.do", true);
	}
}














