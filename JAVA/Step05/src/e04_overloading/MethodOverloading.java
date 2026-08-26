package e04_overloading;

/**
 * [메서드 오버로딩 (Method Overloading)]
 * 1. 개념: 동일한 이름의 메서드를 매개변수의 타입, 개수, 순서를 다르게 하여 여러 개 정의하는 것.
 * 2. 조건: 메서드 이름은 같아야 하며, 매개변수 리스트(파라미터)는 반드시 달라야 함. (반환 타입만 다른 것은 안됨)
 * 3. 목적: 동일한 기능을 수행하지만 입력 데이터 형식이 다를 때, 사용자에게 편리한 인터페이스를 제공하기 위함.
 *    예) System.out.println()은 어떤 타입이든 출력 가능하도록 오버로딩되어 있음.
 *
 * [오버로딩(Overloading) vs 오버라이딩(Overriding)]
 * - 오버로딩: 한 클래스 내에서 "새로운" 메서드를 추가하는 것 (이름만 같은 다른 메서드)
 * - 오버라이딩: 상속 관계에서 부모의 메서드를 "재정의"하여 덮어쓰는 것 (이름, 매개변수, 반환타입 모두 동일)
 */
	public class MethodOverloading {
	
	// 정수 두 개를 더하는 메서드
	public static int plus(int a, int b) {
		System.out.println("plus(int, int) 호출");
		return a + b;
	}
	
	// 실수 두 개를 더하는 메서드 (오버로딩)
	public static double plus(double a, double b) {
		System.out.println("plus(double, double) 호출");
		return a + b;
	}
	
	// 문자 두 개를 더하는 메서드 (오버로딩)
	public static char plus(char a, char b) {
		System.out.println("plus(char, char) 호출");
		return (char)(a + b);
	}
	
	public static void main(String[] args) {
		// 매개변수의 타입에 따라 적절한 메서드가 자동으로 선택됨
		System.out.println(plus(10, 20));      // int 버전 호출
		System.out.println(plus(10.5, 20.0));  // double 버전 호출
		System.out.println(plus('B', 'A'));    // char 버전 호출
		
	}
	
	
}