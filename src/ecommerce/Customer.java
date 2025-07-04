package ecommerce;

public class Customer {
	private Cart cart;
	private double balance;

	public Customer(double balance) {
		cart = new Cart();
		this.balance = balance;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void addToCart(Product product, int quantity) {
		if (product.getQuantity() >= quantity) {
			cart.AddToCart(product, quantity);
		}
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void emptyCart() {
		cart.getProducts().clear();
	}
}
