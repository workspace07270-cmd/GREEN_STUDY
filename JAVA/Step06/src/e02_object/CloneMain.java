package e02_object;

/**
 * Object 클래스의 clone() 메서드를 이용한 객체 복제 테스트 클래스
 */
public class CloneMain {

	public static void main(String[] args) throws CloneNotSupportedException {
		// 원본 객체 생성
		Point p1 = new Point(10, 7);
		
		// p1을 복제해서 p2에 저장 (깊은 복사가 아닌 필드 값 복사)
		// clone()은 Object를 반환하므로 형변환이 필요함
		Point p2 = (Point) p1.clone();
		
		// 원본의 값을 변경해도 복제본은 영향을 받지 않음 (기본 타입 필드인 경우)
		p1.setX(30);
		System.out.println("원본 p1: " + p1);
		System.out.println("복제본 p2: " + p2);
	
		// 두 객체의 참조 주소가 다른지 확인 (결과: false)
		System.out.println("p1 == p2: " + (p1 == p2));
		
		// 실제 객체의 고유 메모리 주소 해시값을 확인
		System.out.println("p1 identityHashCode: " + System.identityHashCode(p1));
		System.out.println("p2 identityHashCode: " + System.identityHashCode(p2));
	}

}




