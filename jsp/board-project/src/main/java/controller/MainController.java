package controller;

import java.io.IOException;
import java.util.List;

import dto.BoardDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import service.MemberService;
import view.ModelAndView;
import vo.PaggingVO;


public class MainController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 페이지 번호 읽어옴, 만약 페이지 번호가 없다면? 1로 지정
		String pageNo = request.getParameter("page");
		int page = pageNo == null ? 1 : Integer.parseInt(pageNo);
		//전체페이지 개수를 읽어옴
		int total = BoardService.getInstance().selectBoardCount();
		
		PaggingVO pagging = new PaggingVO(total, page);
		// 2. 페이지 번호해당하는 게시글 목록 읽어옴
		List<BoardDTO> list = BoardService.getInstance().selectBoardList(page);
		
		// 3. 읽어온 게시글 리스트를 request 영역에 저장
		request.setAttribute("list", list);
		request.setAttribute("pagging", pagging);
		// 4. main.jsp로 이동		
		return new ModelAndView("main", false);
	}

}