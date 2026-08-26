package e01_abstract;

public class AbstractMain {

	public static void main(String[] args) {
		//추상 클래스는 직접적인 생성이 되지 않음
//		Animal a = new Animal();
		//Animal 클래스는 자식 클래스가 생성될떄만 이때만 간접적으로 생성됨
		Dog d = new Dog();
		d.run();
		//추상 클래스도 데이터 타입, 자식 클래스를 형변환 시켜서 저장이 가능
		Animal a = d;
		a.run();
	}
}