
public class E02_ArrayEx02 {
	public static void main(String[] args) {
		//배열 생성과 동시에 초기화 하는 방법 -1
		int[] arr1 = {1,2,3,4,5,6,7};
		
		//arr1 배열 개수 출력
		System.out.println("arr 배열의 개수 : " + arr1.length);
		
		//배열 요소들을 출력
		for (int i = 0; i < arr1.length; i++) {
			System.out.println(arr1[i]);
		}
		System.out.println();
		
// -----------------------------------------------------------------
		
		//배열 생성과 동시에 초기화 하는방법 -2
		int[] arr2 = new int[] {10,11,12,13,14};
		//arr2 배열 개수 출력
		System.out.println("arr2 배열의 개수 : " + arr2.length);
		//배열 요소들을 출력
//		for (int a = 0; a< arr2.length; a++) {
//			System.out.println(arr2[a]);
		//
		for (int i : arr2) {
			System.out.println(i + " ");
		} 
  }
	
}
