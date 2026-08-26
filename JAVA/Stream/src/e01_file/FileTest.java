package e01_file;

import java.io.File;
import java.io.IOException;

/**
 * File 클래스 사용법 예제
 * 파일 및 디렉토리(폴더)를 생성, 삭제, 조회하는 기능을 제공합니다.
 */
public class FileTest {
	/*
	 * 	File 클래스의 주요 기능:
	 * 		- 파일/폴더 생성 및 삭제
	 * 		- 경로 정보 조회 (절대 경로, 부모 경로 등)
	 * 		- 파일 속성 확인 (파일 여부, 디렉토리 여부, 크기 등)
	 * 		- 파일 이름 변경
	 */
	public static void main(String[] args) {
		// 1. File 객체 생성 (실제 파일이 없어도 객체는 생성 가능)
		// File.separator는 운영체제에 맞는 경로 구분자(\ 또는 /)를 제공합니다.
		File file = new File("c:\\test" + File.separator + "hello.txt");
		System.out.println("파일 객체: " + file);
		
		// 2. 경로 정보 조회
		System.out.println("절대 경로: " + file.getAbsolutePath());
		System.out.println("부모 폴더 경로: " + file.getParent());
		
		File parent = file.getParentFile();
		System.out.println("부모 폴더 객체: " + parent);
		
		try {
			// 3. 폴더 생성 (존재하지 않을 경우)
			if(!parent.exists()) {
				parent.mkdirs(); // 경로상의 모든 없는 폴더를 한꺼번에 생성
				System.out.println("폴더 생성 완료");
			}
			
			// 4. 파일 생성
			boolean result = file.createNewFile(); // 이미 있으면 생성되지 않음
			System.out.println(result ? "파일 생성 성공" : "파일 이미 존재함");
		} catch (IOException e) {
			System.out.println("에러 발생: " + e.getMessage());
		}
		
		// 5. 파일 및 폴더 정보 확인
		System.out.println("디렉토리인가? " + file.isDirectory());
		System.out.println("파일인가? " + file.isFile());
		System.out.println("파일 크기(byte): " + file.length());
		System.out.println("전체 용량: " + file.getTotalSpace());
		System.out.println("사용 가능 용량: " + file.getUsableSpace());
		System.out.println("파일명: " + file.getName());
		
		// 6. 파일 삭제 (주석 해제 시 삭제됨)
		// System.out.println("파일 삭제 결과: " + file.delete());
		
		// 7. 특정 폴더 내의 파일 목록 조회
		System.out.println("--- 부모 폴더의 파일 목록 ---");
		String[] arr = parent.getParentFile().list();
		if (arr != null) {
			for(String str : arr) {
				System.out.println(str);
			}
		}
	}

}