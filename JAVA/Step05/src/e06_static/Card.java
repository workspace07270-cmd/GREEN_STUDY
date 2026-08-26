package e06_static;
/*
 * 싱글톤 패턴
 * 		디자인 패턴 중 하나
 * 		특정 클래스의 인스턴스가 프로그램 내에서 하나만 생성되도록 보장
 * 		해당 인스턴스는 모든 클래스가 접근 할수있는 방법을 제공(메서드로 제공)
 * 
 * 1. 시스템 전체에서 공유해야되는 자원아 설정 정보를 하나의 객체로 관리.
 * 2. 객체 생성이 최소화 되기때문에 메모리 사용을 효율적으로 할 수 있음.
 * 3. 어디서든 동일한 인스턴스에 접근할 수 있음.
 */
public class Card {
	private int cardNo;
	private String name;
	
	public Card(int cardNo, String name) {
		super();
		this.cardNo =cardNo;
		this.name=name;
	}

	@Override
	public String toString() {
		return cardNo + " / " + name; 
	}
	
	
	
	
	
}
