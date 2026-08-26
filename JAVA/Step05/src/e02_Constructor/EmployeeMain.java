package e02_Constructor;

import java.util.Arrays;

public class EmployeeMain {
	public static void main (String[] args) {
//		Employeevo emp=
//				new Employeevo("20202111", "김철수", true, "대리", 2300);
//		emp.printInfo();
		
		Employeevo[] arr = new Employeevo[5]; 
		System.out.println(Arrays.toString(arr));
		int i = 0;
		arr[i++] = new Employeevo("2005555","짱구",true,"대리",3200);
		arr[i++] = new Employeevo("2005556","철수",true,"사원",1200);
		arr[i++] = new Employeevo("2005557","맹구",true,"사장",5200);
		arr[i++] = new Employeevo("2005558","유리",false,"차장",2200);
		arr[i++] = new Employeevo("2005559","훈이",true,"과장",13200);
		
		//배열에 있는 전체 사원정보 출력
		
	 for(int j=0; j<i; j++) {
		 arr[j].printInfo();
	 }	
   }
}
