package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardFileDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import view.ModelAndView;

public class BoardViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1. 조회할 게시글 글번호 받아옴
		int bno = Integer.parseInt(request.getParameter("bno"));
		//2. 글번호에 해당하는 게시글 받아옴
		BoardDTO board = BoardService.getInstance().selectBoard(bno);
		//3. 글번호에 해당하는 첨부파일 목록 받아옴
		List<BoardFileDTO> flist = BoardService.getInstance().selectFileList(bno);
		//4. 글번호에 해당하는 댓글 목록 받아옴
		List<BoardCommentDTO> clist=
				BoardService.getInstance().selectBoardCommentList(bno);
		//5. 2,3,4에서 받은 객체와 리스트를 저장
		request.setAttribute("board", board);
		request.setAttribute("flist", flist);
		request.setAttribute("clist", clist);
		System.out.println(clist);
		//6. 조회수 증가 처리
		HttpSession session = request.getSession();
		HashSet<Integer>set = (HashSet<Integer>)session.getAttribute("history");
		if(set == null) {
			set = new HashSet<Integer>();
			session.setAttribute("history", set);
		}
		if(set.add(bno))
			BoardService.getInstance().updateBoardCount(bno);
		
		return new ModelAndView("board_view", false);
	}

}



