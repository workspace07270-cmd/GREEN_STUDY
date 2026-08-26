package e03_inheritance;

public class Car {
	protected String modelName;
	protected String brand;

	public Car(String modelName, String brand) {
		this.modelName = modelName;
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Car [modelName=" + modelName + ", brand=" + brand + "]";
	}
	
}