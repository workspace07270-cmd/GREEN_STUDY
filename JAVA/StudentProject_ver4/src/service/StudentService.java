package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import vo.StudentVO;

/**
 * 학생 데이터를 관리(저장, 검색)하는 핵심 서비스 클래스
 * 싱글톤 패턴을 적용하여 프로그램 전체에서 동일한 데이터 저장소(list)를 공유함
 * CSV 파일을 통한 데이터 입출력 기능을 제공함
 */
public class StudentService {
	// 싱글톤 인스턴스 (지연 생성)
	private static StudentService instance = null;

	// 데이터 저장용 리스트
	private ArrayList<StudentVO> list;

	/**
	 * 외부에서 직접 객체 생성을 못하도록 생성자를 private으로 설정
	 */
	private StudentService() {
		list = new ArrayList<StudentVO>();
	}

	/**
	 * 어디서든 동일한 서비스 객체에 접근할 수 있도록 인스턴스를 반환하는 메서드
	 */
	public static StudentService getInstance() {
		if (instance == null)
			instance = new StudentService();
		return instance;
	}

	public List<StudentVO> getList() {
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
		return list.stream().anyMatch(t -> t.getNo().equals(no));
	}

	/**
	 * 학번으로 학생 객체 자체를 검색하여 반환함
	 *
	 * @param no 검색할 학번
	 * @return 찾으면 StudentVO 객체, 못 찾으면 null
	 */
	public StudentVO searchStudentVO2(String no) {
		for (StudentVO vo : list) {
			if (vo.getNo().equals(no))
				return vo;
		}
		return null;
	}

	public boolean appendStudentVO(StudentVO vo) {
		return list.add(vo);
	}

	public boolean deleteStudentVO(String no) {
		int i = searchStudentVO(no);
		if (i == -1)
			return false;
		list.remove(i);
		return true;
	}

	/**
	 * 학생 이름을 키워드로 검색하여 일치하는 첫 번째 객체를 반환함
	 * (부분 일치 검색 지원)
	 * @param name 검색할 이름 (또는 키워드)
	 * @return 찾으면 StudentVO 객체, 못 찾으면 null
	 */
	public StudentVO searchStudentVOForName(String name) {
		for (StudentVO vo : list) {
			if (vo.getName() != null && vo.getName().contains(name)) {
				return vo;
			}
		}
		return null;
	}

	/**
	 * CSV 파일에서 학생 데이터를 읽어와 리스트를 초기화함.
	 * 파일 인코딩은 UTF-8로 읽음.
	 * 각 라인은: 학번,이름,학과,평점
	 */
	public void loadFromCSV(String filePath) {
		list.clear();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				String[] parts = line.split(",");
				if (parts.length < 4)
					continue; // 잘못된 라인 무시
				String no = parts[0].trim();
				String name = parts[1].trim();
				String major = parts[2].trim();
				double score = 0.0;
				try {
					score = Double.parseDouble(parts[3].trim());
				} catch (NumberFormatException e) {
					// 기본값 0.0 사용
				}
				list.add(new StudentVO(no, name, major, score));
			}
		} catch (Exception e) {
			// 파일이 없거나 읽기 실패하면 현재 리스트는 비어있음
			// 호출부에서 에러 메시지를 출력하도록 함
			// e.printStackTrace();
		}
	}

	/**
	 * 현재 리스트의 내용을 CSV 파일로 저장함. UTF-8로 쓰기.
	 */
	public void saveToCSV(String filePath) {
		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
			for (StudentVO vo : list) {
				if (vo == null)
					continue;
				String line = String.format("%s,%s,%s,%s", vo.getNo(), vo.getName(), vo.getMajorName(), vo.getScore());
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
		} catch (Exception e) {
			// 쓰기 실패 시 호출부에서 알림 필요
			// e.printStackTrace();
		}
	}
}





