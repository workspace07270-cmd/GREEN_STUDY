package mapper;

import java.util.List;
import java.util.Map;

import dto.StudentDTO;

/**
 * MyBatis의 'Mapper 인터페이스'입니다.
 * 직접 구현 클래스를 만들지 않아도, MyBatis가 XML 파일(student-mapper.xml)과 연결하여
 * SQL문을 실행할 수 있는 객체를 자동으로 생성해줍니다.
 */
public interface StudentMapper {
	// 모든 학생을 조회하는 추상 메소드
	public List<StudentDTO> selectAllStudent();

	// 이름으로 학생을 검색하는 추상 메소드
	public List<StudentDTO> selectForName(String name);

	// 학번으로 학생 한 명을 조회하는 추상 메소드
	public StudentDTO selectForNo(String no);

	// 새로운 학생을 등록하는 추상 메소드
	public int insertStudent(StudentDTO dto);

	// 학생을 삭제하는 추상 메소드
	public int deleteStudent(String no);

	// 학생 정보를 수정하는 추상 메소드
	public int updateStudent(StudentDTO dto);
	
	// 통계 기능: 학과별 학생 수를 Map 형태로 가져오는 추상 메소드
	public List<Map<String, Object>> selectStudentDeptCount();
}





