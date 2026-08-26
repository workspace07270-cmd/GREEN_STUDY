package e02_set;


import java.util.Iterator;
import java.util.TreeSet;

public class setObjectExample {

	
	public static void main(String[] args) {
	//	HashSet<Person> set = new HashSet<Person>();
		TreeSet<Person> set = new TreeSet<Person>();
		
		set.add(new Person("홍길동",20));
		set.add(new Person("김철수",24));
		set.add(new Person("김철수",24));
		set.add(new Person("길동아빠",30));
		set.add(new Person("길동엄마",40));

		Iterator<Person>it = set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());	
	}
}
}