package service;

import java.util.Scanner;

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

	/**
	 * 학번으로 학생 존재 여부를 확인하여 인덱스를 반환함
	 * 
	 * @param no 검색할 학번
	 * @return 찾으면 배열 인덱스, 못 찾으면 -1
	 */
	public int searchStudentVO(String no) {
		for (int i = 0; i < idx; i++) {
			if (arr[i].getNo().equals(no))
				return i;
		}
		return -1;
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

	/**
	 * 새로운 학생 정보(객체)를 배열의 빈 공간에 추가함
	 * @param vo 추가할 학생 정보 객체
	 * @return 성공 시 true, 공간 부족 시 false
	 */
	public boolean appendStudentVO(StudentVO vo) {
		if (idx == arr.length) {
			System.out.println("더 이상 저장할 공간이 없습니다.");
			return false;
		}
		arr[idx++] = vo;
		return true;
	}


	/**
	 * 학번을 입력받아 해당 학생 정보를 배열에서 삭제함
	 * 한 칸씩 당겨서 배열의 연속성을 유지함
	 * @param no 삭제할 학번
	 * @return 성공 시 true, 실패 시 false
	 */
	public boolean deleteStudentVO(String no) {

		// 삭제할 학생 정보가 있는지? 확인
		int i = StudentService.getInstance().searchStudentVO(no);

		if (i == -1) 
			return false;

		// 한 칸씩 당겨서 빈 공간 메우기
		for (int j = i; j < idx - 1; j++) {
			arr[j] = arr[j + 1];
		}

		arr[idx - 1] = null;
		idx--;
		
		return true;
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





