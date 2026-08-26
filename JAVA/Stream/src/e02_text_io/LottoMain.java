package e02_text_io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.TreeSet;

/**
 * 로또 번호를 생성하고 텍스트 파일로 저장하는 예제
 */
public class LottoMain {
	/**
	 * 1~45 사이의 중복되지 않는 6개 숫자를 생성하여 정렬된 상태(TreeSet)로 반환
	 */
	public static TreeSet<Integer> createLotto(){
		TreeSet<Integer> set = new TreeSet<Integer>();
		Random r = new Random();
		while(set.size() < 6)
			set.add(r.nextInt(45) + 1);
		return set;
	}
	
	/**
	 * 로또 번호 리스트를 예쁜 문자열 형식으로 변환
	 */
	public static String makeText(ArrayList<TreeSet<Integer>> list) {
		String result = "";
		result += String.format("%18s\n", "LOTTO");
		result += "--------------------------------\n";
		for(int i = 0; i < list.size(); i++) {
			String numbers = "";
			for(int n : list.get(i)) {
				numbers += String.format("%02d ", n);
			}
			result += String.format("%5dset %s\n", i + 1, numbers);
		}
		result += "--------------------------------\n";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result += "  판매시간 : " + sdf.format(new Date()) + "\n";
		result += "--------------------------------\n";
		
		return result;
	}
	
	/**
	 * 문자열을 파일(lotto.txt)로 저장
	 */
	public static void createFile(String str) {
		// try-with-resources 구문을 사용하여 스트림을 자동으로 닫습니다.
		try(FileWriter fw = new FileWriter("lotto.txt");
			PrintWriter pw = new PrintWriter(fw);){
			pw.println(str);
			System.out.println("lotto.txt 파일 출력 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// 1. 로또 번호 세트를 저장할 리스트 생성
		ArrayList<TreeSet<Integer>> list = new ArrayList<TreeSet<Integer>>();
		
		// 2. 로또 번호를 5세트 생성하여 리스트에 추가
		while(list.size() < 5)
			list.add(createLotto());
		
		// 3. 화면에 출력할 형식 확인
		String lottoText = makeText(list);
		System.out.println(lottoText);
		
		// 4. 생성된 텍스트를 파일로 저장
		createFile(lottoText);
	}

}