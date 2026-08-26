
public class E07_Quest {
	public static void main(String[] args) {
		int n1=(int)Math.floor(Math.random()*100);
		int n2=(int)Math.floor(Math.random()*100);
		
		//n1과 n2에 저장된 숫자 중 큰 숫자를 출력
		System.out.println(n1+" "+n2);
		System.out.println("큰 숫자: " + (n1 > n2 ? n1 : n2));
		System.out.println(n1 >n2 ? n1: n2);
		
		// 두숫자의 뺄셈 결과를 출력
		// 뺄셈 결과는 항상 양수로 출력
		System.out.println(n1 > n2 ? n1 - n2 : n2 - n1);
		System.out.println("뺄셈 결과: " + (n1 > n2 ? n1 - n2 : n2 - n1));
		
		int result = n1-n2;
		result= result < 0 ? -result : result;
		System.out.println(result);
		
		System.out.println(Math.abs(n1-n2));
	}
}