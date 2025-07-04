package ecommerce;

import java.time.LocalDate;

public class ExpirableShippableProduct extends ExpirableProduct implements IShippable{
	private double weight;
	public ExpirableShippableProduct(String name, double price, int quantity, LocalDate expiryDate, double weight) {
		super(name, price, quantity, expiryDate);
		this.weight = weight;
	}

	@Override
	public double getWeight() {
		return weight;
	}

}
