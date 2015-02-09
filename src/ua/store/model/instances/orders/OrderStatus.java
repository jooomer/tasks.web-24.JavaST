/**
 * 
 */
package ua.store.model.instances.orders;

/**
 * @author Sergey
 *
 */
public enum OrderStatus {
	WAITING_FOR_PAIMENT, PAID, CANCELED;
	
	public static OrderStatus getOrderStatus(String orderSatusString) {
		for (OrderStatus orderStatus : OrderStatus.values()) {
			if (orderStatus.toString().equals(orderSatusString)) {
				return orderStatus;
			}
		}
		throw new RuntimeException("Unknown type");
	}
}
