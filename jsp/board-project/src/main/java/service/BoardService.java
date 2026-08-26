package service;

import java.util.HashMap;
import java.util.List;

import config.DBManager;
import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardFileDTO;
import mapper.BoardMapper;

/**
 * 게시판 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 컨트롤러(요청 처리)와 매퍼(DB 작업) 사이에서 데이터를 가공하거나 전달하는 역할을 합니다.
 * 싱글톤 패턴으로 만들어져 프로그램 전체에서 하나의 객체만 사용합니다.
 */
public class BoardService {
	// 1. 자기 자신의 유일한 인스턴스를 생성해 둡니다.
	private static BoardService instance = new BoardService();
	
	// 2. DB 작업을 수행할 MyBatis 매퍼 객체를 담을 변수입니다.
	private BoardMapper mapper;
	
	// 3. 외부에서 객체를 함부로 만들 수 없게 생성자를 private으로 숨깁니다.
	private BoardService() {
		// DBManager를 통해 세션을 열고, XML 파일(board-mapper.xml)과 연결된 매퍼 객체를 가져옵니다.
		mapper = DBManager.getInstance().getSession().getMapper(BoardMapper.class);
	}
	
	// 4. 외부에서 서비스 객체가 필요할 때 호출하는 메서드입니다.
	public static BoardService getInstance() {
		if(instance == null)
			instance = new BoardService();
		return instance;
	}

	/** 요청한 페이지 번호에 맞는 게시글 목록을 가져옵니다. */
	public List<BoardDTO> selectBoardList(int page) {
		return mapper.selectBoardList(page);
	}

	/** 사용자가 작성한 새 게시글을 DB에 저장합니다. */
	public int insertBoard(BoardDTO board) {
		return mapper.insertBoard(board);
	}

	/** 게시글에 첨부된 파일 정보들을 DB에 한 번에 여러 개 저장합니다. */
	public int insertBoardFile(List<BoardFileDTO> list) {
		return mapper.insertBoardFile(list);
	}

	/** 페이징 처리를 위해 전체 게시글의 개수를 가져옵니다. */
	public int selectBoardCount() {
		return mapper.selectBoardCount();
	}

	/** 글번호(bno)로 하나의 게시글 상세 정보를 가져옵니다. */
	public BoardDTO selectBoard(int bno) {
		return mapper.selectBoard(bno);
	}

	/** 특정 게시글(bno)에 첨부된 파일 목록을 가져옵니다. */
	public List<BoardFileDTO> selectFileList(int bno) {
		return mapper.selectFileList(bno);
	}

	/** 게시글을 읽을 때 조회수를 1 증가시킵니다. */
	public int updateBoardCount(int bno) {
		return mapper.updateBoardCount(bno);
	}

	/** 
	 * 사용자가 특정 게시글에 좋아요를 누르면 DB에 기록합니다.
	 * MyBatis에는 여러 개의 파라미터를 보낼 때 주로 Map을 사용하므로,
	 * 회원번호(no)와 글번호(bno)를 Map으로 묶어서 매퍼에 전달합니다.
	 */
	public int insertBoardLike(int no, int bno) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("bno", bno);
		map.put("mno", no);
		return mapper.insertBoardLike(map);
	}

	/** 사용자가 좋아요를 다시 눌러서 취소(삭제)할 때 호출됩니다. */
	public int deleteBoardLike(int no, int bno) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("bno", bno);
		map.put("mno", no);
		return mapper.deleteBoardLike(map);
	}

	/** 특정 게시글(bno)이 받은 전체 좋아요 개수를 가져옵니다. */
	public int selectBoardLikeCount(int bno) {
		return mapper.selectBoardLikeCount(bno);
	}
	
	public int insertBoardHate(int no, int bno) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("bno", bno);
		map.put("mno", no);
		return mapper.insertBoardHate(map);
	}
	
	public int deleteBoardHate(int no, int bno) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("bno", bno);
		map.put("mno", no);
		return mapper.deleteBoardHate(map);
	}
	
	public int selectBoardHateCount(int bno) {
		return mapper.selectBoardHateCount(bno);
	}

	public int insertBoardComment(BoardCommentDTO comment) {
		return mapper.insertBoardComment(comment);
	}

	public List<BoardCommentDTO> selectBoardCommentList(int bno, int page) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("bno", bno);
		map.put("page", page);
		
		return mapper.selectBoardCommentList(map);
	}

	public List<BoardCommentDTO> selectBoardCommentList(int bno) {
		return selectBoardCommentList(bno,1);
	}

	public int insertBoardCommentLikeHate(int no, int cno, String mode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("mno", no);
		map.put("mode", mode);
		return mapper.insertBoardCommentLikeHate(map);
	}

	public int deleteBoardCommentLikeHate(int no, int cno, String mode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("mno", no);
		map.put("mode", mode);
		return mapper.deleteBoardCommentLikeHate(map);
		
	}

	public int selectBoardCommentLikeHateCount(int cno,String mode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("mode", mode);

		return mapper.selectBoardCommentLikeHateCount(map);
	}
}
