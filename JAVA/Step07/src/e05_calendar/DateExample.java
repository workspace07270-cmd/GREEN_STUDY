package e05_calendar;

import java.util.Date;

public class DateExample {
	public static void main(String[] args) {
		Date today = new Date();
		System.out.println(today);
		System.out.println(today.getTime());
		today.setTime(0);
		System.out.println(today);
		
	}
}
