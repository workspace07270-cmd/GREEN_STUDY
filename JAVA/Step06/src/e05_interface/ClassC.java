package e05_interface;

public class ClassC extends SuperClass implements InterA, InterB{

	@Override
	public void interA() {
		System.out.println("classC interB");
	}

	@Override
	public void interB() {
		System.out.println("ClassC interA");
	}
}
