package ecommerce;

import java.util.ArrayList;

public class Cart {
	private ArrayList<CartItem> products;

	public Cart() {
		products = new ArrayList<>();
	}

	public ArrayList<CartItem> getProducts() {
		return products;
	}

	public void AddToCart(Product product, int quantity) {
		products.add(new CartItem(product, quantity));
	}
	
	public boolean isEmpty() {
		return products.isEmpty();
	}
}
