package e01_list;

import java.util.ArrayList;
import java.util.Iterator;

public class ListExample {
	/*
	 * 	List 특징
	 * 		1. 데이터 중복
	 * 		2. 리스트에 저장된 데이터 순서가 보장됨
	 * 
	 *  ArrayList : 리스트에서 데이터를 배열로 관리
	 *  LinkedList : 리스트에서 데이터를 노드 단위로 노드끼리 연결해서 관리
	 *  Vector : ArrayList와 동일	, 동기화가 추가
	 * 
	 */
	public static void main(String[] args) {
		//문자열만 저장하는 리스트를 생성
		ArrayList<String> list = new ArrayList<String>();
		//데이터 추가
		list.add("HTMl/CSS");
		list.add("javascript");
		list.add("React");
		list.add("MySQL");
		list.add("jsp/SerVlet");
		list.add("Spring");
		list.add("MySQL");
		list.add("RTOS");
		
		System.out.println(list);
		//데이터 삭제
		list.remove(1);
		System.out.println(list);
		list.remove("MySQL");// 최초 검색된 데이터만 삭제
		System.out.println(list);
		//리스트가 저장하고있는 데이터개수
		System.out.println(list.size());
		//리스트의 특정 인덱스에다가 데이터를 저장
		list.add(2,"Kotlin");
		System.out.println(list);
		//리스트의 특정 인덱스에다사 데이터를 저장
		list.set(2, "Android");
		System.out.println(list);
		
		//리스트에있는 모든 데이터를 삭제
//		list.clear();
//		System.out.println(list);
		
		//해당데이터가 리스트에 있냐?
		System.out.println(list.contains("MySQL"));
		//해당데이터가 몇 번째 인덱스에 있냐?(처음 검색된 값)
		System.out.println(list.indexOf("MySQL"));
		//리스트가 비었냐? (비었으면 true, 안비었으면 false)
//		list.clear();
		System.out.println(list.isEmpty());
		System.out.println("-------------");
		//-------------------------------------------
		//데이터 꺼내는 방법-1
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		//데이터 꺼내는 방법-2
		for(String str: list) {
			System.out.println(str);
		}
		//데이터 꺼내는 방법-3 (interface 활용)
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}