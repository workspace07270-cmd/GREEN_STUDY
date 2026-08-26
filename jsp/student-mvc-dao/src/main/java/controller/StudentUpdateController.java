package controller;

import java.io.IOException;
import java.util.Scanner;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import view.ModelAndView;
import vo.StudentVO;

/**
 * 학번을 입력받아 해당 학생의 정보를 수정하는 기능을 담당하는 컨트롤러
 */
public class StudentUpdateController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//수정할 데이터 받아오기
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String majorName = request.getParameter("majorName");
		double score = Double.parseDouble(request.getParameter("score"));
		//수정할 학생정보 읽어옴
		StudentVO vo = StudentService.getInstance().searchStudentVO2(no);
		//데이터 수정
		vo.setMajorName(majorName);
		vo.setName(name);
		vo.setScore(score);
		//main.do로 이동
		
		return new ModelAndView("/main.do", true);
	}
}






