package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;
import dto.BoardFileDTO;
import dto.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import service.BoardService;
import view.ModelAndView;

public class BoardWriteController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//로그인한 계정의 회원번호 읽어옴
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		int mno = member.getNo();
		//title, content
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO board = new BoardDTO();
		board.setMno(mno);
		board.setTitle(title);
		board.setContent(content);
		
		//board table에 데이터 등록
		BoardService.getInstance().insertBoard(board);
		
		System.out.println(board);
		//파일 쓰기 처리
		try {
			File root = new File("c:\\fileupload");
			//파일 업로드할 경로 확인 후 없으면 생성
			if(!root.exists()) {
				root.mkdirs();
			}
			//파일 업로드할 정보를 리스트로 보관
			List<BoardFileDTO> list = new ArrayList<>();
			
			request.getParts().forEach(part -> {
				if(part.getSubmittedFileName() == null || part.getSize() == 0) return;
				
				//경로를 완성하고 파일 쓰기
				String path = root.getAbsolutePath() + 
						File.separator + part.getSubmittedFileName();
				try {
					part.write(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//리스트에 파일 정보를 저장
				list.add(new BoardFileDTO(board.getBno(),path));
			});
			
			System.out.println(list);
			//board_file 테이블에 파일 내용을 등록
			BoardService.getInstance().insertBoardFile(list);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
		
		
		return new ModelAndView("./main.do", true);
	}

}






