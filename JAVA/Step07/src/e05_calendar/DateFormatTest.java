package e05_calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateFormatTest {
	public static void main(String[] args) {
	
		//날짜생성
		Calendar today = Calendar.getInstance();
		//날짜 서식 문자열을 이용해서 원하는 형태로 문자열을 만들어주는 클래스
		SimpleDateFormat sdf= new SimpleDateFormat();
		//연도 4자리
		sdf.applyPattern("yyyy");
		System.out.println(sdf.format(today.getTime()));
		//연도 2자리
		sdf.applyPattern("yy");
		System.out.println(sdf.format(today.getTime()));
		//월 1자리
		sdf.applyPattern("M");
		System.out.println(sdf.format(today.getTime()));
		//월 2자리
		sdf.applyPattern("MM");
		System.out.println(sdf.format(today.getTime()));
		//월 약자 - 로컬형식
		sdf.applyPattern("MMM");
		System.out.println(sdf.format(today.getTime()));
		//월 풀네임 - 로컬형식
		sdf.applyPattern("MMMM");
		System.out.println(sdf.format(today.getTime()));
		//일 1자리
		sdf.applyPattern("d");
		System.out.println(sdf.format(today.getTime()));
		//일 2자리
		sdf.applyPattern("dd");
		System.out.println(sdf.format(today.getTime()));
		//요일 -로컬형식
		sdf.applyPattern("E");
		System.out.println(sdf.format(today.getTime()));
		//요일-로컬형식
		sdf.applyPattern("EEE");
		System.out.println(sdf.format(today.getTime()));
		sdf.applyPattern("EEEE");
		System.out.println(sdf.format(today.getTime()));
		//시간
		sdf.applyPattern("h"); // 12시간기준 한자리시간
		System.out.println(sdf.format(today.getTime()));
		sdf.applyPattern("hh");// 12시간 기준 2자리
		System.out.println(sdf.format(today.getTime()));
		sdf.applyPattern("H");// 24시기준
		System.out.println(sdf.format(today.getTime()));
		sdf.applyPattern("HH"); // 24시기준
		System.out.println(sdf.format(today.getTime()));
		//초
		sdf.applyPattern("s");
		System.out.println(sdf.format(today.getTime()));
		sdf.applyPattern("ss");
		System.out.println(sdf.format(today.getTime()));
		//2026_04_29_17_22_44
//		sdf.applyPattern("yyyy_MM_dd_HH_mm_ss");
//		System.out.println(sdf.format(today.getTime()));
		
		sdf.applyPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		System.out.println(sdf.format(today.getTime()));
		
		//날짜 로컬형식 변경
		sdf = new SimpleDateFormat("MMM MMMM E EEE EEEE",Locale.ENGLISH);
		sdf = new SimpleDateFormat("MMM MMMM E EEE EEEE",Locale.CHINA);
		
		System.out.println(sdf.format(today.getTime()));
		}
	}