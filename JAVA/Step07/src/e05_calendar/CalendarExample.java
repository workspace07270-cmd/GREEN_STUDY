package e05_calendar;

import java.util.Calendar;

public class CalendarExample {
	public static void main(String[] args) {
		Calendar calendar =Calendar.getInstance();
		System.out.println(calendar);
		
		//연도
		System.out.println((calendar.get(calendar.YEAR)) + "년");
		//월
		System.out.println((calendar.get(calendar.MONTH)+1) + "월");
		//일
		System.out.println((calendar.get(calendar.DAY_OF_MONTH)) + "일");
		//시
		System.out.println((calendar.get(calendar.HOUR))+ "시");
		//분
		System.out.println((calendar.get(calendar.MINUTE))+"분");
		//초
		System.out.println((calendar.get(calendar.SECOND))+"초");
		//밀리초
		System.out.println((calendar.get(calendar.MILLISECOND))+ "밀리초");
		//날짜 변경
		calendar.set(Calendar.YEAR, 2025); 
		calendar.set(Calendar.MONTH, 4);// 월은 0부터 시작함 -1 해주어야함 
		
		System.out.println(calendar.getTime());
	}	
}
