package e02_object;

/**
 * 원을 표현하는 클래스
 * 참조 타입 필드(Point)를 가지고 있어 깊은 복사(Deep Copy)가 필요함
 */

public class Circle implements Cloneable {
	private Point pos; // 원의 중심점 (참조 타입)
	private int r;      // 반지름 (기본 타입)

	public Circle(Point pos, int r) {
		this.pos = pos;
		this.r = r;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	@Override
	public String toString() {
		return "Circle [pos=" + pos + ", r=" + r + "]";
	}

	/**
	 * 객체 복제를 수행하는 메서드
	 * 참조형 필드가 있는 경우, 해당 필드도 함께 복제(깊은 복사)해야 함
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// 1. 얕은 복사(Shallow Copy): super.clone()은 참조 주소만 복사함
		// Circle temp = (Circle) super.clone();
		
		// 2. 깊은 복사(Deep Copy): 참조하고 있는 객체(Point)도 새로 생성하여 저장
		// temp.setPos((Point) pos.clone());
		// return temp;
		
		// 아래 방식은 새로운 Circle 객체를 생성하면서 내부 Point 객체도 복제하여 전달함
		return new Circle((Point)pos.clone(), r);
	}
	
}