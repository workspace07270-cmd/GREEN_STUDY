package service;

import java.util.HashMap;
import java.util.List;

import config.DBManager;
import dto.MemberDTO;
import mapper.MemberMapper;

public class MemberService {
	private static MemberService instance = new MemberService();
	private MemberMapper mapper;
	
	private MemberService() {	
		mapper = DBManager.getInstance().getSession().getMapper(MemberMapper.class);
	}
	
	public static MemberService getInstance() {
		if(instance == null)
			instance = new MemberService();
		return instance;
	}

	public List<MemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}

	public MemberDTO selectMemberById(String id) {
		return mapper.selectMemberById(id);
	}

	public int insertMember(MemberDTO memberDTO) {
		int result = 0;
		System.out.println(memberDTO);
		try {
			result = mapper.insertMember(memberDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public MemberDTO login(String id, String passwd) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("passwd", passwd);
		
		MemberDTO dto = mapper.login(map);
		
		return dto;
	}

	public int deleteMember(String no) {
		return mapper.deleteMember(no);
		
	}

	public List<MemberDTO> searchMembers(String kind, String search) {
	HashMap<String, String>map = new HashMap<String, String>();
	map.put("kind", kind);
	map.put("search", search);
		return mapper.searchMembers(map);
	}
	public MemberDTO selectMemberByNo(String no) {
		return mapper.selectMemberByNo(no);
	}

	public int updateMember(MemberDTO memberDTO) {
		return mapper.updateMember(memberDTO);
		
	}

	
}