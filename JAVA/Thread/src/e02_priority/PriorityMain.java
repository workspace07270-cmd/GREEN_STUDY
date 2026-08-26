package e02_priority;

import java.util.ArrayList;

public class PriorityMain {

	public static void main(String[] args) {
		ArrayList<ThreadPriority> list = new ArrayList<ThreadPriority>();
		for(int i=0;i<10;i++) {
			ThreadPriority t = 
					new ThreadPriority("스레드 - " + i);
			t.setPriority(Thread.MIN_PRIORITY);
			list.add(t);
		}
		//우선 순위 1 ~ 10까지 범위, 1 - 제일 낮음, 10 - 제일 높음
		//맨 마지막 스레드만 우선순위를 10, 나머지는 1 설정
		list.getLast().setPriority(Thread.MAX_PRIORITY);
		
		list.forEach(t -> t.start());
	}

}