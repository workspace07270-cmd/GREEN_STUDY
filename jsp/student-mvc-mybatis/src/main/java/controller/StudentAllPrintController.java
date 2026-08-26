package controller;

import java.io.IOException;
import java.util.List;

import dto.StudentDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import view.ModelAndView;

/**
 * DB에서 모든 학생 정보를 가져와서 화면에 보여주도록 지시하는 컨트롤러입니다.
 */
public class StudentAllPrintController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 1. 서비스를 통해 DB에 저장된 모든 학생 목록을 불러옵니다.
		List<StudentDTO> list = StudentService.getInstance().selectAllStudent();
		
		// 2. 화면(JSP)에서 사용할 수 있도록 짐꾸러미(request)에 담아둡니다.
		request.setAttribute("list", list);
		
		// 3. 메인 화면(main.jsp)으로 이동하라고 명령합니다.
		ModelAndView view = new ModelAndView("main", false);		

		return view;
	}
}




