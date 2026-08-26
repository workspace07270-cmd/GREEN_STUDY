package controller;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import view.ModelAndView;

public class BoardHateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int bno = Integer.parseInt(request.getParameter("bno"));
		int mno = 0;
		
		try {
			mno = ((MemberDTO)request.getSession().getAttribute("user")).getNo();
			map.put("resultCode", 0);
			try {
				BoardService.getInstance().insertBoardHate(mno, bno);
				map.put("msg", "해당 게시글에 싫어요를 하셨습니다");
			} catch (Exception e) {
				BoardService.getInstance().deleteBoardHate(mno, bno);
				map.put("msg", "해당 게시글에 싫어요를 취소 하셨습니다");
			}
			int count = BoardService.getInstance().selectBoardHateCount(bno);
			map.put("count", count);
			
		}catch (Exception e) {
			map.put("resultCode", 1);
			map.put("msg", "로그인 하셔야 이용하실 수 있습니다.");
		}
			
		JSONObject json = new JSONObject(map);
		response.getWriter().println(json);

		return null;
	}

}