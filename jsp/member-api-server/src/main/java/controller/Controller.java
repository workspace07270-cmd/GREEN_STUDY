package controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 모든 컨트롤러 클래스가 구현해야 하는 표준 인터페이스입니다.
 */
public interface Controller {
	/**
	 * 사용자의 요청을 처리하고 정보를 반환합니다.
	 * @param request 사용자 요청 객체 (파라미터 읽기 등에 사용)
	 * @param response 서버 응답 객체 (직접 데이터 전송 등에 사용)
	 * @return 처리 결과를 담은 내용
	 * @throws IOException 입출력 예외 발생 시
	 */
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}




