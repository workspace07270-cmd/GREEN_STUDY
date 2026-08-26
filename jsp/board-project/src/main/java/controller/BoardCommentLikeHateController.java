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

public class BoardCommentLikeHateController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cno = Integer.parseInt(request.getParameter("cno"));
		String mode = request.getParameter("mode");
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("user");

		HashMap<String, Object> map = new HashMap<String, Object>();

		if (member == null) {
			map.put("resultCode", 1);
			map.put("msg", "로그인 하셔야 이용하실 수 있습니다.");
		} else {
			map.put("resultCode", 0);
			try {
				BoardService.getInstance()
					.insertBoardCommentLikeHate(member.getNo(), cno, mode);
				if(mode.equals("like")) {
					map.put("msg", "해당 댓글에 좋아요를 하셨습니다");
				}else {
					map.put("msg", "해당 댓글에 싫어요를 하셨습니다");
				}
			} catch (Exception e) {
				BoardService.getInstance()
				.deleteBoardCommentLikeHate(member.getNo(), cno, mode);
				if(mode.equals("like")) {
					map.put("msg", "해당 댓글에 좋아요를 취소 하셨습니다");
				}else {
					map.put("msg", "해당 댓글에 싫어요를 취소 하셨습니다");
				}
			}
			int count = BoardService.getInstance().selectBoardCommentLikeHateCount(cno,mode);
			map.put("count", count); // 화면에 표시할 수 있게 맵에 담습니다.
		}

		JSONObject json = new JSONObject(map);

		response.getWriter().println(json);

		return null;
	}

}