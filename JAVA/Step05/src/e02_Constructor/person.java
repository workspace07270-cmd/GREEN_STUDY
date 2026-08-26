package e02_Constructor;

/**
 * 생성자(Constructor)를 설명하기 위한 클래스
 */
public class person {
	// 필드(Field): 객체의 상태를 저장하는 변수
	private String name;
	private int age;
	
	/**
	 * 생성자: 객체가 생성될 때(new 연산자 사용 시) 호출되는 특수한 메서드
	 * 1. 이름이 클래스 명과 동일해야 함
	 * 2. 반환 타입이 없음 (void도 적지 않음)
	 * 3. 객체의 필드 초기화를 주 목적으로 함
	 */
	public person(String name, int age) {
		// this: 현재 객체 자신을 가리키는 참조 변수
		// 매개변수 이름과 필드 이름이 같을 때 구분하기 위해 사용
		this.name = name;
		this.age = age;
		System.out.println("Person 객체가 생성되었습니다.");
	}
	
	// 메서드: 객체의 동작을 정의
	public void printInfo() {
		System.out.println(name + " / " + age);
	}
}