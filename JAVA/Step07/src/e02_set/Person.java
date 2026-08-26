package e02_set;

import java.util.Objects;

public class Person implements Comparable<Person>{
	private String name;
	private int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	@Override
	public int hashCode() {
		System.out.println("hashCode");
		return Objects.hash(age, name);
	}
	 
	@Override
	public boolean equals(Object obj) {
		System.out.println("equals");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && Objects.equals(name, other.name);
	}
	
	@Override
	public int compareTo(Person o) {
		System.out.println("compareTo");
		if(this.equals(o)) {
			return hashCode() - o.hashCode();
		}
		return 0;
	}

	
	
}
