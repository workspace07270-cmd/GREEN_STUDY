package e01_inner;

import e01_inner.OutterStaticInnerClass.InnerStaticClass;

/**
 * 정적 내부 클래스를 테스트하는 메인 클래스
 */
public class OutterStaticMain {

	public static void main(String[] args) {
		// 정적 내부 클래스는 외부 클래스의 인스턴스 생성 없이도 바로 생성이 가능합니다.
		InnerStaticClass inStatic = new InnerStaticClass(100);
		
		// 정적 내부 클래스의 메서드 호출
		System.out.println("결과: " + inStatic.sum());
	}

}