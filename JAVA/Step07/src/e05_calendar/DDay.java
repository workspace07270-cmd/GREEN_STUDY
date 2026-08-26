package e05_calendar;

import java.util.Calendar;
import java.util.Date;

public class DDay {

	public static void main(String[] args) {
		// 오늘날짜부터 올해 수능일까지 몇일?
		// 2026년 11월 19일
		Date soo = new Date("2026/11/19");
		System.out.println(soo);
		Date today = new Date();
		System.out.println(today);
			
		//수능일까지 몇일?
		//밀리초 -> 일(Day)
		int day = (int)Math.ceil((soo.getTime() - today.getTime()) / (24 * 60 * 60 * 1000.0));
		System.out.println(day);
		
		Calendar cSoo = Calendar.getInstance();
		cSoo.setTime(soo);
		
		Calendar cToday = Calendar.getInstance();
		
		System.out.println(cSoo.get(Calendar.DAY_OF_YEAR));
		System.out.println(cToday.get(Calendar.DAY_OF_YEAR));
		day = cSoo.get(Calendar.DAY_OF_YEAR)-cToday.get(Calendar.DAY_OF_YEAR);
		System.out.println(day);

	}

}




