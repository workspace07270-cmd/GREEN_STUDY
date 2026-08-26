
public class E11_Break_Continue {
	public static void main(String[] args) {
		for(int i=1; i<=10; i++) {
			System.out.print(i + " ");
			
			if(i==5)break; //반복문 종료
			
		}
		for(int i=1; i<=10; i++) {
			if(i%2==0) continue;
			System.out.print(i + " ");
		}
		
	}

}
