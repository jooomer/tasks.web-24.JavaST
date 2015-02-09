/**
 * 
 */
package ua.store.model.instances.users;

import java.util.ArrayList;
import java.util.List;

import ua.store.model.instances.orders.Order;


/**
 * @author Sergey
 *
 */
public class Client extends User {
	
	private List<Order> orders = new ArrayList<>();
	
	public Client() {
		userType = UserType.CLIENT;
	}
	
	public void makeOrder() {
		
	}
	
	public void payOrder(Order order) {
		
	}
	
}
