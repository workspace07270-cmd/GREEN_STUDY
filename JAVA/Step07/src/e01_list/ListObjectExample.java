package e01_list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ListObjectExample {
	public static void main(String[] args) {
		ArrayList<Point> list = new ArrayList<Point>();
		Random r = new Random();
		// 리스트에 데이터 10건
		// 좌표 범위 : x,y 0~20까지 좌표를 랜덤하게 생성해서 저장
		while (list.size() < 10) {
			list.add(new Point(r.nextInt(21), r.nextInt(21)));
		}
		list.add(4, new Point(10, 7));
		Iterator<Point> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		Point search = new Point(10,7);
		System.out.println(list.contains(search));
		System.out.println(list.indexOf(search));
		System.out.println(list.remove(search));
		
		
	}
}





