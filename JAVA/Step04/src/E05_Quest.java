
public class E05_Quest {
	public static void main(String[] args) {
		int[] arr = new int[]{23,54,34,234,6,7,237,643,111,33};
 /*
  * 1.배열에 저장된 값의 총합
  * 2.배열에 저장된 값들중 제일 큰값
  * 3.배열에 저장된 값들중 제일 작은값
  */	
		int total = 0;
  		int max = arr[0];
  		int min = arr[0];
  		
		for(int i=0; i<arr.length; i++) {
			//총합
			total = total + arr[i];
			//최대값
			if(max<arr[i]) {
				max = arr[i];
			//최소값	
			}
			if(min>arr[i]) {
				min = arr[i];
			}
		}
		System.out.println("배열에 저장된 값의 총합 : " + total);
		System.out.println("배열에 저장된 값들중 제일 큰값 : " + max);
		System.out.println("배열에 저장된 값들중 제일 작은값 : "+ min);
		
		
//		int total = 0;
//  		int max = arr[0];
//  		int min = arr[0];
//  		
//		for(int num:arr){
//			total =total+num;
//		if(num>max){
//			max=num;}
//		if(num<min){
//			min=num;}
//		}
//		
//		System.out.println("배열에 저장된 값의 총합 : " + total);
//		System.out.println("배열에 저장된 값들중 제일 큰값 : " + max);
//		System.out.println("배열에 저장된 값들중 제일 작은값 : "+ min);
		
  }
}
