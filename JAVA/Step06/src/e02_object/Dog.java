package e02_object;

/**
 * 강아지 정보를 저장하는 클래스
 * Cloneable 인터페이스를 구현하여 복제 기능을 제공함
 */
public class Dog implements Cloneable {
	private String name; // 이름
	private int age;     // 나이

	public Dog(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + ", age=" + age + "]";
	}

	/**
	 * Object의 clone() 메서드를 재정의
	 * 필드들이 기본 타입이거나 불변 객체(String)인 경우 super.clone()만으로 충분함
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}