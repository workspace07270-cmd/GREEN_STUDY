package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import api.NaverAPICall;

/**
 * Servlet implementation class NaverBlogservlet
 */
@WebServlet("/blog.do")
public class NaverBlogservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public NaverBlogservlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색어는 query 파라미터로 받고
		String query= request.getParameter("query");
		// 사용자에게 NaverAPICall 클래스에 있는 callApi 실행한 후
		NaverAPICall apiCall = new NaverAPICall();
		String result = apiCall.callapi(query);
		// 결과를 사용자에게 Writer를 이용해서 전송
		response.getWriter().write(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
