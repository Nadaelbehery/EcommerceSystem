package ecommerce;

import java.util.HashMap;
import java.util.Map.Entry;

public class ShippingService {
	public static void Ship(HashMap<IShippable, Integer> products) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
		for(Entry<IShippable, Integer> entry : products.entrySet()) {
            System.out.println(entry.getValue() + "x" + entry.getKey().getName() +"    "+ entry.getKey().getWeight() * entry.getValue() + "g");
            totalWeight += entry.getKey().getWeight() * entry.getValue();
		}
        System.out.println("Total package weight: " + totalWeight / 1000 + "kg");
	}
}
