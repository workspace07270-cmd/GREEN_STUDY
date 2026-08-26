package e02_set;

import java.util.HashSet;
import java.util.Iterator;

public class SetExample {
	/*
	 * 	Set
	 * 		1. 데이터가 중복되지 않음
	 * 		2. 자동 정렬
	 */
	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>();
		//데이터 5건 추가
		set.add(10);	set.add(12);
		set.add(10);	set.add(15);
		set.add(7);		set.add(3);
		System.out.println(set.add(10));
		System.out.println(set.add(19));
		System.out.println(set);
		
		//삭제
		set.remove(12);
		System.out.println(set);
		//Set에 저장된 데이터 개수 출력
	    System.out.println(set.size());
		//숫자 15가 있냐?
		System.out.println(set.contains(15));
		
		//데이터 전체 조회 - 1
		Iterator<Integer>it = set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		//데이터 전체 조회 - 2
		for(Integer i : set) {
			System.out.println(i);
		}
		//데이터비우기
		set.clear();
		//너비었냐?
		System.out.println(set.isEmpty());
		
	}

}






