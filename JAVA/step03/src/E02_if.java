

public class E02_if {
	public static void main(String[] args) {
		int n= (int)Math.floor(Math.random()*5);
		//0,홀수,짝수
		System.out.println(n);
		if(n==0) {System.out.println("0");}
		else if(n%2==1) {System.out.println("홀수");}
		else {System.out.println("짝수");}
		
// -------------------------------------------------------
		
		if(n==0) {System.out.println("0");}
		if(n%2==1) {System.out.println("홀수");}
		if(n%2==0 && n!=0) {System.out.println("짝수");}

		
		}
	
}