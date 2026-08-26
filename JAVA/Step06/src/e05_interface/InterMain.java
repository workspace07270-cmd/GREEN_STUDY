package e05_interface;

public class InterMain {
	public static void main(String[]args) {
		ClassC c = new ClassC();
		
		InterA interA = c;
		interA.interA();
		
		InterB interB = c;
		interB.interA();
		
		SuperClass su = c;
		su.interA();
		
		SuperClass d= (SuperClass)interA;
		d.interA();
		
		InterA ia = new InterA() {

			@Override
			public void interA() {
				System.out.println("임시");
			}
			
		};
		ia=d;
		ia.interA();
	}
}