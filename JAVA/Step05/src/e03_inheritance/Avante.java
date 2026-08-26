package e03_inheritance;

public class Avante extends Car{
	private String color;

	/**
	 * 자식 클래스의 생성자는 부모 클래스 생성자가 필요로하는 값을 받아서
	 * 부모 생성자에 보내주는 역할도 함
	 */
	public Avante(String modelName, String brand, String color) {
		super(modelName, brand);//부모 생성자에 필요한 값을 전달
		this.color = color;
	}

	@Override
	public String toString() {
		return "Avante [color=" + color + ", modelName=" + modelName + ", brand=" + brand + "]";
	}
	
}