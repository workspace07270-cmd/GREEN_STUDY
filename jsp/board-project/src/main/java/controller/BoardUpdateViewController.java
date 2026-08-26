package controller;

import java.io.IOException;

import dto.BoardDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BoardService;
import view.ModelAndView;

public class BoardUpdateViewController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));

		BoardDTO board = BoardService.getInstance().selectBoard(bno);
		
		request.setAttribute("board", board);
		
		return new ModelAndView("board_update", false);
	}

}