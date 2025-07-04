package ecommerce;

import java.time.LocalDate;

import exceptions.EmptyCartException;
import exceptions.ExpiredException;
import exceptions.InsufficientBalanceException;
import exceptions.OutOfStockException;

public class Main {
	public static void main(String[] args) {
		//Products
		Product cheese = new ExpirableShippableProduct("Cheese", 100, 5, LocalDate.of(2022,12,1), 20.0);
		Product biscuits = new ExpirableShippableProduct("Biscuits", 100, 5, LocalDate.of(2028,7,1), 20.0);
		Product tv = new NonExpirableShippableProduct("Tv", 100, 100, 9000);
		Product scartchCard = new Product("ScratchCard", 100, 5);
		
		Customer customer = new Customer(1000);
		
		//no exceptions thrown
		System.out.println("===== Test 1: Normal Checkout =====");
		customer.addToCart(scartchCard, 1);
		customer.addToCart(tv, 1);
		customer.addToCart(biscuits, 3);
		
		try {
			CheckoutService.Checkout(customer);
		} catch (InsufficientBalanceException | OutOfStockException | ExpiredException | EmptyCartException e) {
			e.printStackTrace();
		}
		
		customer.emptyCart();
		
		//expiry exception thrown
		System.out.println("===== Test 2: Expired Product =====");
		customer.addToCart(cheese, 2);
		
		try {
			CheckoutService.Checkout(customer);
		} catch (InsufficientBalanceException | OutOfStockException | ExpiredException | EmptyCartException e) {
			e.printStackTrace();
		}
		
		customer.emptyCart();
		
		//insufficient balance
		System.out.println("===== Test 3: Insufficient Balance =====");
		customer.addToCart(tv, 80);
		
		try {
			CheckoutService.Checkout(customer);
		} catch (InsufficientBalanceException | OutOfStockException | ExpiredException | EmptyCartException e) {
			e.printStackTrace();
		}
		
		customer.emptyCart();
		
		System.out.println("===== Test 4: Empty Cart =====");
		
		try {
			CheckoutService.Checkout(customer);
		} catch (InsufficientBalanceException | OutOfStockException | ExpiredException | EmptyCartException e) {
			e.printStackTrace();
		}
		
		// out of stock exception
		System.out.println("===== Test 5: Out of Stock =====");
		Customer customer2 = new Customer(1000);
		customer2.addToCart(scartchCard, 3);
		customer.addToCart(scartchCard, 2);
		
		try {
			CheckoutService.Checkout(customer2);
			CheckoutService.Checkout(customer);
		} catch (InsufficientBalanceException | OutOfStockException | ExpiredException | EmptyCartException e) {
			e.printStackTrace();
		}
		

	}
}
