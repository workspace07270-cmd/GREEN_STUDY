
public class E04_Quest {
	public static void main(String[] args) {
		int[] arr = new int[]{23,54,34,234,6,7,237,643,111,33};
		int count = 0;
		//배열에 저장된 값들 중 홀수의 개수 출력
		
		for (int num: arr) {
			if (num % 2 == 1) {
				//System.out.println(num);
				count++;
			}	
		}
		System.out.println("배열에 저장된 홀수의 개수 : " + count);
	}
}
