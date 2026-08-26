package mapper;

import java.util.HashMap;
import java.util.List;

import dto.BoardCommentDTO;
import dto.BoardDTO;
import dto.BoardFileDTO;

/**
 * 게시판 관련된 데이터베이스 작업을 지시하는 인터페이스입니다.
 * 여기에 적힌 메서드 이름들은 board-mapper.xml 파일 안의 각 SQL 태그 id와 똑같아야 합니다.
 * MyBatis가 알아서 이 인터페이스와 XML 파일을 연결해 줍니다.
 */
public interface BoardMapper {

	/** 선택한 페이지에 보여줄 게시글 목록을 DB에서 가져옵니다. */
	List<BoardDTO> selectBoardList(int page);

	/** 새 게시글의 제목, 내용, 작성자 정보를 DB에 추가합니다. */
	int insertBoard(BoardDTO board);

	/** 게시글에 첨부된 여러 개의 파일 정보를 DB에 한 번에 추가합니다. */
	int insertBoardFile(List<BoardFileDTO> list);

	/** 전체 게시글이 몇 개인지 숫자를 세어 가져옵니다. (페이징용) */
	int selectBoardCount();

	/** 글번호(bno)를 이용해 특정 게시글 한 개의 상세 정보를 가져옵니다. */
	BoardDTO selectBoard(int bno);

	/** 글번호(bno)를 이용해 그 글에 달린 첨부파일 목록을 가져옵니다. */
	List<BoardFileDTO> selectFileList(int bno);

	/** 게시글을 읽을 때마다 해당 글의 조회수를 1 증가시킵니다. */
	int updateBoardCount(int bno);

	/** 좋아요를 누르면 게시글 번호와 회원 번호를 저장하여 좋아요 기록을 남깁니다. */
	int insertBoardLike(HashMap<String, Integer> map);

	/** 좋아요를 다시 누르면 저장된 좋아요 기록을 지웁니다(취소). */
	int deleteBoardLike(HashMap<String, Integer> map);

	/** 특정 게시글의 현재 좋아요 총 개수를 세어 가져옵니다. */
	int selectBoardLikeCount(int bno);

	int insertBoardHate(HashMap<String, Integer> map);
	
	int deleteBoardHate(HashMap<String, Integer> map);
	
	int selectBoardHateCount(int bno);

	int insertBoardComment(BoardCommentDTO comment);

	List<BoardCommentDTO> selectBoardCommentList(HashMap<String, Integer> map);

	int insertBoardCommentLikeHate(HashMap<String, Object> map);

	int deleteBoardCommentLikeHate(HashMap<String, Object> map);

	int selectBoardCommentLikeHateCount(HashMap<String, Object> map);


}



