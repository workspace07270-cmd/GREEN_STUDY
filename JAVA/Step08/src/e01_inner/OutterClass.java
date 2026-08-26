package e01_inner;

/**
 * 중첩 클래스(Inner Class) 예제
 * 외부 클래스(Outter Class) 내부에 내부 클래스(Inner Class)를 정의합니다.
 */
public class OutterClass {
	private int outter; // 외부 클래스의 인스턴스 변수

	public OutterClass(int outter) {
		this.outter = outter;
	}
	
	public void printOutter() {
		System.out.println("outter : " + outter);
	}
	
	public void setOutter(int outter) {
		this.outter = outter;
	}
	
	/**
	 * 내부 클래스 (인스턴스 내부 클래스)
	 * 외부 클래스의 객체가 먼저 생성되어야만 생성할 수 있는 클래스입니다.
	 * 외부 클래스의 멤버(변수, 메서드)에 자유롭게 접근할 수 있습니다.
	 */
	public class InnerClass {
		private int inner; // 내부 클래스의 인스턴스 변수

		public InnerClass(int inner) {
			this.inner = inner;
		}
		
		/**
		 * 다른 OutterClass 객체를 매개변수로 받아 그 객체의 private 멤버에 접근하는 예제
		 */
		public void test(OutterClass o) {
			System.out.println(inner + " -> " + o.outter);
		}
		
		/**
		 * 내부 클래스에서 외부 클래스의 private 변수(outter)에 직접 접근하는 것을 보여줍니다.
		 */
		public void printInner() {
			System.out.println(outter + " -> " + inner);
		}
		
	}
}
