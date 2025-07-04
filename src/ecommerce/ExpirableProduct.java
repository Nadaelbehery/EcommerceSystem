package ecommerce;

import java.time.LocalDate;

public class ExpirableProduct extends Product {
	private LocalDate expiryData;

	public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
		super(name, price, quantity);
		this.setExpiryData(expiryDate);
	}

	public LocalDate getExpiryData() {
		return expiryData;
	}

	public void setExpiryData(LocalDate expiryData) {
		this.expiryData = expiryData;
	}
}
