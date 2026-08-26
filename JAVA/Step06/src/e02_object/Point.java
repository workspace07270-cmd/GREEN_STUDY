package e02_object;

import java.util.Objects;

/**
 * 좌표값 X, Y를 저장하는 클래스
 * Cloneable 인터페이스를 구현하여 객체 복제를 지원함
 */
public class Point implements Cloneable {
	private int x; // X 좌표
	private int y; // Y 좌표
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 객체의 현재 좌표 정보를 문자열로 반환
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	/**
	 * X, Y 값을 기반으로 해시코드 생성
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	/**
	 * 좌표 값이 동일한지 비교
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 객체를 복제하여 새로운 인스턴스를 생성함
	 * protected인 clone()을 public으로 넓혀서 외부에서 사용 가능하게 함
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}