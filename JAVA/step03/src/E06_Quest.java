/**
 * 이 클래스는 성적(점수)에 따라 등급을 나누는 연습 예제입니다.
 * 다양한 조건문(if-else, switch)을 사용하여 성적 처리 로직을 구현합니다.
 */
public class E06_Quest {
	/*
	 * 문제: 0~100 사이의 랜덤한 점수를 생성하고, 다음 기준에 따라 학점을 출력하세요.
	 * 90점 이상 : A
	 * 80점 이상 : B
	 * 70점 이상 : C
	 * 60점 이상 : D
	 * 나머지 : F
	 */
	public static void main(String[] args) {
		// 0~100 사이의 랜덤 점수 생성
		// Math.random() : 0.0 이상 1.0 미만의 실수 생성
		// Math.random() * 101 : 0.0 이상 101.0 미만의 실수
		// Math.floor(...) : 소수점 이하 내림 처리
		int n = (int)Math.floor(Math.random() * 101);
		System.out.println("점수 : " + n);
		
		// 1. if-else if-else 문을 이용한 기본 학점 계산
		String grade = "F";
		if(n >= 90) grade = "A";
		else if(n >= 80) grade = "B";
		else if(n >= 70) grade = "C";
		else if(n >= 60) grade = "D";
		
		System.out.println("기본 학점(if문): " + grade);
		
		// 2. switch-case 문을 이용한 학점 계산 (점수를 10으로 나눈 몫 활용)
		grade = "F";
		switch(n / 10) {
		case 10: // 100점
		case 9:  // 90~99점
			grade = "A";
			break;
		case 8:  // 80~89점
			grade = "B";
			break;
		case 7:  // 70~79점
			grade = "C";
			break;
		case 6:  // 60~69점
			grade = "D";
			break;
		}
		
		// 3. 세부 등급 설정 (+ 또는 0 추가)
		// 60점 이상인 경우, 일의 자리가 5 이상이면 '+', 아니면 '0' 추가
		if(n >= 60 && n % 10 >= 5) {
			grade += "+";
		} else if(n >= 60) {
			grade += "0";
		}
		
		System.out.println("세부 학점(switch+if): " + grade);

		// 4. switch-case 문을 이용한 세부 학점 계산 (점수를 5로 나눈 몫 활용)
		// 100~95: A+, 94~90: A, 89~85: B+ ...
		switch(n / 5) {
		case 20: // 100점
		case 19: // 95~99점
			grade = "A+";
			break;
		case 18: // 90~94점
			grade = "A";
			break;
		case 17: // 85~89점
			grade = "B+";
			break;
		case 16: // 80~84점
			grade = "B";
			break;
		case 15: // 75~79점
			grade = "C+";
			break;
		case 14: // 70~74점
			grade = "C";
			break;
		case 13: // 65~69점
			grade = "D+";
			break;
		case 12: // 60~64점
			grade = "D";
			break;
		default: // 60점 미만
			grade = "F";
			break;
		}
		System.out.println("세부 학점(범위 switch): " + grade);
		
	}
}




