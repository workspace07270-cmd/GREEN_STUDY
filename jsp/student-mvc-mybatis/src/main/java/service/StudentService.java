package service;

import java.util.List;

import config.DBManager;
import dto.StudentDTO;
import mapper.StudentMapper;

/**
 * 학생 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 직접 DB를 건드리지 않고, StudentDAO에게 일을 시켜서 데이터를 가져옵니다.
 * "어떤 데이터를 어떻게 처리할지" 결정하는 중간 관리자 역할을 합니다.
 */
public class StudentService {
	// 싱글톤 패턴 적용
	private static StudentService instance = new StudentService();
	
	// 데이터를 실제로 관리할 DAO 객체
	private StudentMapper mapper;
	
	private StudentService() {
		mapper = DBManager.getInstance().getSession().getMapper(StudentMapper.class);
	}

	public static StudentService getInstance() {
		if (instance == null)
			instance = new StudentService();
		return instance;
	}
	
	/**
	 * 학번으로 학생 한 명의 정보를 찾아옵니다.
	 */
	public StudentDTO searchStudent(String no) {
		return mapper.selectForNo(no);
	}

	/**
	 * 새로운 학생을 등록합니다.
	 */
	public boolean appendStudentVO(StudentDTO vo) {
		return mapper.insertStudent(vo) != 0;
	}

	/**
	 * 학번을 기준으로 학생을 삭제합니다.
	 */
	public boolean deleteStudentVO(String no) {
		return mapper.deleteStudent(no) != 0;
	}

	/**
	 * 이름으로 학생 목록을 검색합니다.
	 */
	public List<StudentDTO> searchStudentVOForName(String name) {
		return mapper.selectForName(name);
	}

	/**
	 * 모든 학생의 목록을 가져옵니다.
	 */
	public List<StudentDTO> selectAllStudent() {
		return mapper.selectAllStudent();
	}

	/**
	 * 학생의 정보를 수정합니다.
	 */
	public void updateStudent(StudentDTO dto) {
		mapper.updateStudent(dto);		
	}
}





