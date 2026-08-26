package e02_set;

import java.util.Random;
import java.util.TreeSet;

public class LottoMain {
	
	public static void main(String[] args) {
		//로또번호 1셋트 생성해서 출력
		//단. Set 사용
		
		Random r= new Random(1000);
		TreeSet<Integer> set = new TreeSet<Integer>();
		while(set.size() <6) set.add(r.nextInt(45)+1);
		System.out.println(set);
	}
}
