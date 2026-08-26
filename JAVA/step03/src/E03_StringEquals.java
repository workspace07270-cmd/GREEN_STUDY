
public class E03_StringEquals {
	public static void main(String[] args) {
		String str1 = "안녕하세요";
		String str2 = "안녕하세요";
		String str3 = new String("안녕하세요");
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		System.out.println(str2 == str3);
		
		// equals로 비교하는 이유는 실제 저장하고있는 값이 동일한지 체크
		
		System.out.println(str1.equals(str3));
		System.out.println(str2.equals(str3));
		
		
	}
}