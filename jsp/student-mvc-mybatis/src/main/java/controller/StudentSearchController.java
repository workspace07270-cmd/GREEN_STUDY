package controller;

import java.io.IOException;
import java.util.List;

import dto.StudentDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import view.ModelAndView;

public class StudentSearchController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		
		List<StudentDTO> list = StudentService.getInstance().searchStudentVOForName(name);
		request.setAttribute("list", list);
		
		return new ModelAndView("main", false);
	}
}





