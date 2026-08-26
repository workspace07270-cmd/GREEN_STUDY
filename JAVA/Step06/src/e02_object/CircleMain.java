package e02_object;

/**
 * 깊은 복사(Deep Copy)가 정상적으로 이루어지는지 테스트하는 클래스
 */

public class CircleMain {

	public static void main(String[] args) throws CloneNotSupportedException {
		// 1. 원본 객체 생성 (중심점 10, 5 / 반지름 7)
		Circle c1 = new Circle(new Point(10, 5), 7);
		
		// 2. 객체 복제 (깊은 복사 수행)
		Circle c2 = (Circle) c1.clone();
		
		// 초기 상태 출력
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);
		
		// 객체 자체의 주소값이 다른지 확인
		System.out.println("c1 identityHashCode: " + System.identityHashCode(c1));
		System.out.println("c2 identityHashCode: " + System.identityHashCode(c2));
		
		// 3. 원본의 내부 참조 객체(Point) 데이터 변경
		c1.getPos().setX(30);

		// 변경 후 결과 확인
		// 깊은 복사가 되었다면 c1의 X만 30으로 바뀌고, c2의 X는 여전히 10이어야 함
		System.out.println("변경 후 c1: " + c1);
		System.out.println("변경 후 c2: " + c2);
	}
}