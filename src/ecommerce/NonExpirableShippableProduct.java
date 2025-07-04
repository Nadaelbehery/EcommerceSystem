package ecommerce;

public class NonExpirableShippableProduct extends Product implements IShippable {
	private double weight;

	public NonExpirableShippableProduct(String name, double price, int quantity, double weight) {
		super(name, price, quantity);
		this.weight = weight;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}
}
