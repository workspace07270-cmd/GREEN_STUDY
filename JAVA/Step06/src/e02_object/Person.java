package e02_object;

import java.util.Objects;

public class Person {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	/*	객체가 가지고 있는 필드, 메서드의 결과를
	 * 	외부에서 쉽게 확인이 가능하게
	 * 	필드와 메서드의 결과를 문자열로 만들어서 리턴하는 메서드
	 * 
	 * 	재정의하지 않으면
	 * 	타입명@객체의 해시코드(16진수)
	 */
	@Override
	public String toString() {
		return name + " / " + age;
	}
	/*
	 * 해시값을 기반으로 사용되는 컬렉션에서 사용할 정수 값을 제공하는 목적
	 * 두 객체가 동일한 객체인지 확인하기위한 정수값을 제공하는 목적
	 * 
	 * Object 클래스에서는 기본적으로는 객체의 주소값에 해당하는 해시값을 리턴
	 */
	@Override
	public int hashCode() {
//		return name.hashCode() + age;
		return Objects.hash(name,age);
	}

	/*
	 * 객체 내부가 동일한 값인지 비교하기 위한 메서드
	 * 비교할려는 객체를 Object 타입으로 받음
	 */
	@Override
	public boolean equals(Object obj) {
		//1. obj가 null인지 체크
		if(obj == null) return false;
		//2. 메모리 주소 해시값이 같은지 비교
		if(obj == this) return true;
		//3. 객체 해시값이 다른지 확인
		if(hashCode() != obj.hashCode()) return false;
		//4. obj가 현재 클래스와 동일한(비교할 수 있는) 클래스인지
		//obj가 Person 클래스 타입으로 형변환이 되니?
		if(obj instanceof Person) {
			Person temp = (Person)obj;
			//가지고 있는 필드값이랑 비교한 결과값 리턴
			return name.equals(temp.name) && age == temp.age;
		}
		return false;
	}
	
}











