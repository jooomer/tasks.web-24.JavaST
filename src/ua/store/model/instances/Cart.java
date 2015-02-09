/**
 * 
 */
package ua.store.model.instances;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import ua.store.model.instances.products.Product;
import ua.store.model.instances.users.User;

/**
 * @author Sergey
 *
 */
public class Cart {
	
	private Map<Product, Integer> productMap = new TreeMap<>();
	private double amount;
	
	public void addProduct(Product product, Integer quantity) {
		if (productMap.containsKey(product)) { 
			Integer q = productMap.get(product);
			q += quantity;
			productMap.put(product, quantity);
		} else {
			productMap.put(product, quantity);
		}
		amount += product.getPrice() * quantity;
	}
	
	public boolean deleteProduct(Product product, Integer quantity) {
		if (productMap.containsKey(product)) { 
			Integer q = productMap.get(product);
			if (q >= quantity) {
				q -= quantity;
				productMap.put(product, q);
				amount += product.getPrice() * quantity;
				return true;
			} 
		}
		return false;
	}
	
	public void clearCart() {
		productMap.clear();
		amount = 0;
	}
	
	public int size() {
		return productMap.size();
	}

	/**
	 * @return the productList
	 */
	public Map<Product, Integer> getProductMap() {
		return productMap;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}


}
