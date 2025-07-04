package ecommerce;

import java.time.LocalDate;
import java.util.HashMap;

import exceptions.EmptyCartException;
import exceptions.ExpiredException;
import exceptions.InsufficientBalanceException;
import exceptions.OutOfStockException;

public class CheckoutService {
		public static void Checkout(Customer customer) throws InsufficientBalanceException, OutOfStockException, ExpiredException, EmptyCartException {
			if(customer.getCart().isEmpty()) {
				throw new EmptyCartException();
			}
			
			double subTotal = 0;
			double orderTotal = 0;
			double shippingFees = 0;
			LocalDate currDate = LocalDate.now();
	        HashMap<IShippable, Integer> shippableProducts = new HashMap<>();
	        
			for (CartItem item : customer.getCart().getProducts()) {
				if (item.getProduct().getQuantity() < item.getQuantity()) {
					throw new OutOfStockException();
				}

				if (item.getProduct() instanceof ExpirableProduct && ((ExpirableProduct) item.getProduct()).getExpiryData().isBefore(currDate)) {
					throw new ExpiredException();
				}
				
				subTotal += item.getProduct().getPrice()*item.getQuantity();
				
				if(item.getProduct() instanceof IShippable) {
					shippableProducts.put((IShippable) item.getProduct(), item.getQuantity());
				}
			}
			
			ShippingService.Ship(shippableProducts);
			
			if(!shippableProducts.isEmpty()) {
				shippingFees = 30;
			}
			
			orderTotal = subTotal + shippingFees;
			
			if (orderTotal > customer.getBalance()) {
				throw new InsufficientBalanceException();
			}

		    for (CartItem item : customer.getCart().getProducts()) {
		        item.getProduct().deductProduct(item.getQuantity());
		    }
			
	        System.out.println("** Checkout receipt **");
	        for (CartItem item : customer.getCart().getProducts()) {
	            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " - " + (item.getProduct().getPrice() * item.getQuantity()));
	        }
	        
	        System.out.println("----------------------");
	        System.out.println("Subtotal         " + subTotal);
	        System.out.println("Shipping         " + shippingFees);
	        System.out.println("Amount           " + orderTotal);
	        System.out.println("END.\n");
		
	}
}
