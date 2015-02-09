/**
 *
 */
package ua.store.model.dao;

/**
 * @author Sergey
 *
 */
public class OrderDetailsDAOSqlCommands {

	// createOrderDetails()
	final static String createOrderDetails ="INSERT INTO order_details ("
			+ "order_id, "
			+ "product_id, "
			+ "quantity_each, "
			+ "price_each "
			+ ") VALUES (?, ?, ?, ?)";



}
