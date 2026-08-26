package mapper;

import java.util.HashMap;
import java.util.List;
import dto.MemberDTO;

public interface MemberMapper {

	List<MemberDTO> selectAllMember();
	
	MemberDTO selectMemberById(String id);

	int insertMember(MemberDTO memberDTO);

	MemberDTO login(HashMap<String, String> map);

	int deleteMember(String no);

	List<MemberDTO> searchMembers(HashMap<String, String> map);

	MemberDTO selectMemberByNo(String no);

	int updateMember(MemberDTO memberDTO);


}