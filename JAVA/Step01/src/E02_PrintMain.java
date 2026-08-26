
public class E02_PrintMain {

	public static void main(String[] args) {
		System.out.println(10);
		System.out.println(10 + 5);
		System.out.println(10 - 5);
		
		System.out.println("hello" + "world");
		System.out.println("hello" + 10);
		System.out.println("hello" + 10 + 5);
		
		// \t : 탭키만큼 띄어쓰기 \n : 줄바꿈
		System.out.println("hello\tworld");
		System.out.println("hello\nworld");
		// \\ : \, \" : ", \' : '
		// \는 특수문자이므로 \를 출력하려면 \\로 입력해야 한다.
		// "는 문자열을 감싸는 용도로 사용되므로 "를 출력하려면 \"로 입력해야 한다.
		// '는 문자 리터럴을 감싸는 용도로 사용되므로 '를 출력하려면 \'로 입력해야 한다.
		System.out.println("hello\"world\"");
		System.out.println("hello\\ \'world\'");
	}

}
 