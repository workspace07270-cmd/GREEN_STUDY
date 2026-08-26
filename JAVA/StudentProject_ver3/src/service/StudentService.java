package service;

import java.util.ArrayList;


import vo.StudentVO;

/**
 * 학생 데이터를 관리(저장, 검색)하는 핵심 서비스 클래스 싱글톤 패턴을 적용하여 프로그램 전체에서 동일한 데이터 저장소(arr)를 공유함
 */
public class StudentService {
	// 자기 자신의 인스턴스를 하나만 생성하여 유지함 (싱글톤)
	private static StudentService instance = new StudentService();

	// 데이터 저장용 배열과 현재 개수 관리 변수
	private StudentVO[] arr;
	private int idx;
	
	// 데이터 저장용 리스트 생성
	private ArrayList<StudentVO> list;
	
	/**
	 * 외부에서 직접 객체 생성을 못하도록 생성자를 private으로 설정
	 */
	private StudentService() {
		arr = new StudentVO[20]; // 최대 20명 저장 가능

		// 테스트용 샘플 데이터 초기화
		arr[idx++] = new StudentVO("20230001", "홍길동", "컴퓨터공학과", 4.5);
		arr[idx++] = new StudentVO("20230002", "김철수", "경영학과", 3.8);
		arr[idx++] = new StudentVO("20230003", "이영희", "심리학과", 3.2);
		arr[idx++] = new StudentVO("20230004", "박영수", "생활체육학과", 4.0);
		arr[idx++] = new StudentVO("20230005", "최민수", "전자공학과", 3.5);
		
		list = new ArrayList<StudentVO>();
		//리스트에 샘플 데이터 초기화
		list.add(new StudentVO("20230001", "홍길동", "컴퓨터공학과", 4.5));
		list.add( new StudentVO("20230002", "김철수", "경영학과", 3.8));
		list.add(new StudentVO("20230003", "이영희", "심리학과", 3.2));
		list.add(new StudentVO("20230004", "박영수", "생활체육학과", 4.0));
		list.add(new StudentVO("20230005", "최민수", "전자공학과", 3.5));
	}

	/**
	 * 어디서든 동일한 서비스 객체에 접근할 수 있도록 인스턴스를 반환하는 메서드
	 */
	public static StudentService getInstance() {
		if (instance == null)
			instance = new StudentService();
		return instance;
	}

	/**
	 * 컨트롤러에서 데이터를 읽어갈 수 있도록 배열 참조를 반환함
	 */
	public StudentVO[] getArr() {
		return arr;
	}
	
	public ArrayList<StudentVO> getList() {
		return list;
	}

	public int searchStudentVO(String no) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNo().equals(no))
				return i;
		}
		return -1;
	}

	public boolean checkDuplicateStudent(String no) {
		return list.stream().filter(t -> t.getNo().equals(no)).count() != 0;
	}
	
	/**
	 * 학번으로 학생 객체 자체를 검색하여 반환함
	 * 
	 * @param no 검색할 학번
	 * @return 찾으면 StudentVO 객체, 못 찾으면 null
	 */
	public StudentVO searchStudentVO2(String no) {
		for (int i = 0; i < idx; i++) {
			if (arr[i].getNo().equals(no))
				return arr[i];
		}
		return null;
	}

	public boolean appendStudentVO(StudentVO vo) {
		return list.add(vo);
	}

	public boolean deleteStudentVO(String no) {

		// 삭제할 학생 정보가 있는지? 확인
		int i = StudentService.getInstance().searchStudentVO(no);

		if (i == -1) 
			return false;
		
		return list.remove(i) != null;
	}

	/**
	 * 학생 이름을 키워드로 검색하여 일치하는 첫 번째 객체를 반환함
	 * (부분 일치 검색 지원)
	 * @param name 검색할 이름 (또는 키워드)
	 * @return 찾으면 StudentVO 객체, 못 찾으면 null
	 */
	public StudentVO searchStudentVOForName(String name) {
		for (int i = 0; i < idx; i++) {
			if (arr[i].getName().indexOf(name) != -1) {
				return arr[i];
			}
		}
		return null;
	}
}





