package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

import java.io.IOException;

import controller.Controller;
import controller.HandlerMapping;

/**
 * 모든 사용자 요청(*.do)을 중앙에서 관리하는 입구(프론트 컨트롤러)입니다. 이 서블릿이 먼저 모든 요청을 받은 뒤, 적절한 컨트롤러로
 * 일감을 배분합니다.
 */
@WebServlet("*.do")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 50)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DispatcherServlet() {
		super();
	}

	/** 모든 GET 방식의 요청을 처리합니다. */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자가 요청한 전체 주소(URI)를 가져옵니다.
		String uri = request.getRequestURI();

		// 2. 프로젝트의 경로(Context Path)를 가져옵니다.
		String contextPath = request.getContextPath();

		// 3. 전체 주소에서 프로젝트 경로를 제외한 실제 서비스 경로만 추출합니다. (예: login.do)
		String path = uri.substring(contextPath.length() + 1);

		// 4. HandlerMapping을 통해 요청 경로(path)에 맞는 컨트롤러를 찾습니다.
		Controller controller = HandlerMapping.getInstance().createController(path);

		// 5. 컨트롤러를 실행하고, 실행 후 이동할 페이지 정보를 담은 ModelAndView 객체를 받습니다.
		ModelAndView view = null;
		if (controller != null)
			view = controller.execute(request, response);

		// 6. ModelAndView에 담긴 정보대로 페이지를 이동시킵니다.
		if (view != null) {
			if (view.isRedirect()) {
				// 리다이렉트(Redirect) 방식: 주소가 바뀌면서 새로운 요청을 만듭니다.
				response.sendRedirect(contextPath + "/" + view.getPath());
			} else {
				// 포워드(Forward) 방식: 주소는 바뀌지 않고 서버 내부에서 페이지만 전환합니다.
				request.getRequestDispatcher(view.getPath()).forward(request, response);
			}
		}
	}

	/** 모든 POST 방식의 요청을 처리합니다. (내부적으로 doGet을 호출하여 동일하게 처리) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}