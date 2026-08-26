package e01_inner;

/**
 * 정적 중첩 클래스(Static Inner Class) 예제
 */
public class OutterStaticInnerClass {
	private int outter; // 인스턴스 변수
	private static int n = 100; // 정적 변수

	public OutterStaticInnerClass(int outter) {
		this.outter = outter;
	}
	
	/**
	 * 정적 내부 클래스
	 * 외부 클래스의 인스턴스 생성 없이도 생성할 수 있는 클래스입니다.
	 * static 키워드가 붙어 있으므로 외부 클래스의 non-static 멤버(인스턴스 변수)에는 접근할 수 없습니다.
	 */
	public static class InnerStaticClass{
		private int inner;

		public InnerStaticClass(int inner) {
			this.inner = inner;
		}
		
		public int sum() {
			// [주의] 정적 내부 클래스에서는 외부 클래스의 인스턴스 변수에 접근할 수 없습니다.
			// return outter + inner; // 에러: outter가 누구의 것인지 알 수 없음
			
			// 외부 클래스의 정적(static) 변수에는 접근이 가능합니다.
			return n + inner;
		}
	}
	
}