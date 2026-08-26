package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import view.ModelAndView;

import java.io.IOException;

import controller.Controller;
import controller.HandlerMapping;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length()+1);
		System.out.println(uri);
		System.out.println(contextPath);
		System.out.println(path);
		//작업 시작
		Controller controller = HandlerMapping.getInstance().createController(path);
		
		// 페이지 이동할 곳 정보를 담고 있는 객체 ModelAndView
		ModelAndView view = null;
		if(controller != null)
			view = controller.execute(request, response);
		
		//페이지 이동
		if(view == null) {
			response.sendRedirect(contextPath +"/main.do");
		}else {
			if(view.isRedirect()) {
				//redirect
				response.sendRedirect(contextPath +"/" + view.getPath());
			}else {
				//forward
				request.getRequestDispatcher(view.getPath()).forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
