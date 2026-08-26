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

public class BoardLikeController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bno =  Integer.parseInt(request.getParameter("bno"));
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if(member == null) {
			map.put("resultCode", 1);
			map.put("msg", "로그인 하셔야 이용하실 수 있습니다.");
		}else {
			// 좋아요 처리
			map.put("resultCode", 0);
			try {
				BoardService.getInstance()
						.insertBoardLike(member.getNo(),bno);
				map.put("msg", "해당 게시글에 좋아요를 하셨습니다");
			}catch (Exception e) {
				// 좋아요를 취소한 경우
				BoardService.getInstance()
						.deleteBoardLike(member.getNo(),bno);
				map.put("msg", "해당 게시글에 좋아요를 취소 하셨습니다");
			}
			int count = BoardService.getInstance().selectBoardLikeCount(bno);
			map.put("count", count);
		}
		
		// 결과를 json으로 보냄
		JSONObject json = new JSONObject(map);
		response.getWriter().println(json);
		
		
		return null;
	}

}