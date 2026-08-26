public class E06_Operator {
	public static void main(String[] args) {
		int n1 = 10, n2 = 7;
		//>, <, >=, <=, ==, !=
		System.out.println(n1 > n2);
		System.out.println(n1 < n2);
		System.out.println(n1 >= n2);
		System.out.println(n1 <= n2);
		System.out.println(n1 == n2);
		System.out.println(n1 != n2);
		
		// and &&, or ||, not !
		//n1이 5보다 크고 20보다 작나?
		//n2가 양수이면서 짝수냐?
		//n1이 5보다 크거나 n2가 10보다 작나?
		
		System.out.println(n1 > 5 && n1 < 20);
		System.out.println(n2 > 0 && n2 % 2 == 0);
		System.out.println(n1 > 5 || n2 < 10);
		
		boolean flag = n1 > 5 || n2 <10;
		System.out.println(!flag);
		
		System.out.println(n1 >5 || ++n2 >10); // true
		System.out.println(n2); //7
		System.out.println(n1 < 5 && ++n2 >10); 
		System.out.println(n2); //7
		// 단락회로
		
		int num= (int)Math.floor(Math.random()*100); // 0부터 99까지 랜덤하게 뽑겠다
		String result = num % 2 == 0 ? "짝수" : "홀수";
		System.out.println(result + "(" + num + ")");
		
		
	}
}
