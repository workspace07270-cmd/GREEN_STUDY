package e03_inheritance;

public class CarMain {

	public static void main(String[] args) {
		Avante avante = new Avante("아반떼2026년식", "현대자동차", "흰색");
		System.out.println(avante.toString());
		System.out.println(avante);// 인스턴스만 넣어도 자동으로 toString 실행됨
		
	}
}
