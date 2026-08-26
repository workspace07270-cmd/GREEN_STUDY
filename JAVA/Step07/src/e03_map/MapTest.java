package e03_map;

import java.util.HashMap;
import java.util.Iterator;

import e01_list.Point;

public class MapTest {
	/*
	 * Map
	 * 키값을 이용해서 데이커를 관리하는 형태
	 * 키와 값 한쌍의 페어로 이루어져 있음
	 * 키값은 중복이 안됨
	 */
	public static void main(String[] args) {
		HashMap<String, Point> map = new HashMap<String, Point>();
		//데이터 추가
		//      키              데이터
		map.put("A", new Point(10,6));
		map.put("Enemy", new Point(20,15));
		map.put("B", new Point(4,17));
		map.put("A", new Point(20,16)); // 키값 중복시 새로값으로 대체됨/( 위에 A 말고 지금 A로 )
		map.put("C", new Point(9,13));
		
		System.out.println(map);
		//데이터 꺼내기
		System.out.println(map.get("A"));
		System.out.println(map.get("Enemy"));
		
		//전체 조회
//		Set<String> KeySet = map.keySet();
//		Iterator<String> it = KeySet.iterator(); 
		Iterator<String> it = map.keySet().iterator(); 
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " " + map.get(key));
		}
		//데이터 삭제
		map.remove("B");
		System.out.println(map);
		//데이터 개수
		System.out.println(map.size());
		//멥에 내용 비우기
		map.clear();
		//맵에 비어 있냐?
		System.out.println(map.isEmpty());
	}
	
}
