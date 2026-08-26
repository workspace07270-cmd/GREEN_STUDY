package e06_static;

public class CardMain {
	
	public static void main(String[] args) {
		Card card1 = CardFactory.getInstance().createCard("홍길동");
		Card card2 = CardFactory.getInstance().createCard("김철수");
		Card card3 = CardFactory.getInstance().createCard("박우수");
		Card card4 = CardFactory.getInstance().createCard("이철웅");
		Card card5 = CardFactory.getInstance().createCard("박수헝");
		
		System.out.println(card1);
		System.out.println(card2);
		System.out.println(card3);
		System.out.println(card4);
		System.out.println(card5);
	}
}
