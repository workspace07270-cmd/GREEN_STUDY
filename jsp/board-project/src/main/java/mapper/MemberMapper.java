package mapper;

import java.util.HashMap;
import java.util.List;

import dto.MemberDTO;

/**
 * [회원 관리 매퍼 인터페이스]
 * 자바 코드와 SQL 문(member-mapper.xml)을 연결해주는 다리 역할을 합니다.
 * 메서드 이름은 XML 파일의 <select>, <insert>, <update>, <delete> 태그의 id와 일치해야 합니다.
 */
public interface MemberMapper {

	/** 전체 회원 목록을 DB에서 조회해오는 메서드 */
	List<MemberDTO> selectAllMember();

	/** 특정 아이디를 가진 회원의 상세 정보를 조회하는 메서드 */
	MemberDTO selectMemberById(String id);

	/** 새로운 회원 정보를 DB 테이블에 저장하는 메서드 */
	int insertMember(MemberDTO memberDTO);

	/** 아이디와 비밀번호가 일치하는 회원 정보를 찾는 메서드 (로그인용) */
	MemberDTO login(HashMap<String, String> map);

	/** 고유 번호(NO)를 기준으로 회원 데이터를 삭제하는 메서드 */
	int deleteMember(String no);

	/** 선택한 검색 조건과 검색어에 맞는 회원들을 찾아오는 메서드 */
	List<MemberDTO> searchMembers(HashMap<String, String> map);

	/** 고유 번호(NO)를 기준으로 특정 회원 한 명의 정보를 가져오는 메서드 */
	MemberDTO selectMemberByNo(String no);

	/** 변경된 회원 정보를 DB에 업데이트하는 메서드 */
	int updateMember(MemberDTO memberDTO);

}




