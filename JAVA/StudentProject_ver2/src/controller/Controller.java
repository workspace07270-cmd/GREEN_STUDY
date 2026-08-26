package controller;

import java.util.Scanner;

/**
 * 모든 컨트롤러의 부모가 되는 인터페이스 역할을 하는 클래스
 * 다형성을 활용하여 여러 기능을 일관된 방식으로 실행하기 위해 정의함
 */
public class Controller {
	/**
	 * 각 기능(컨트롤러)이 실행할 로직을 구현하는 메서드
	 * @param sc 입력을 위한 Scanner 객체
	 */
	public void execute(Scanner sc) {}
}