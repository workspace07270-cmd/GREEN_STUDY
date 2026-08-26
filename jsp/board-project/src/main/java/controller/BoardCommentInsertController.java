package controller;

import java.io.IOException;

import dto.BoardCommentDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardCommentInsertController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int mno = 0;
		ModelAndView view = null;
		try {
			//로그인이 된 상태에서 처리
			mno = ((MemberDTO)request.getSession().getAttribute("user")).getNo();
			int bno = Integer.parseInt(request.getParameter("bno"));
			String content = request.getParameter("content");
			
			BoardCommentDTO comment = new BoardCommentDTO();
			comment.setBno(bno);;
			comment.setContent(content);
			comment.setMno(mno);
			
			BoardService.getInstance().insertBoardComment(comment);
			
			view = new ModelAndView("./boardView.do?bno="+bno, true);
			
		}catch (NullPointerException e) {
			//로그인 세션이 풀렸을때
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("<script>");
			response.getWriter().println("alert('로그인 하셔야 이용하실 수 있습니다.');");
			response.getWriter().println("location.href='./loginView.do';");
			response.getWriter().println("</script>");			
		}
		
		
		return view;
	}

}



 