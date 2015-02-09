/**
 *
 */
package ua.store.model.dao;

/**
 * @author Sergey
 *
 */
public class OrderDAOSqlCommands {

	// createOrder()
	final static String createOrder = "INSERT INTO orders (" + "user_id, " + "amount, "
			+ "date, " + "status, " + "comments "
			+ ") VALUES (?, ?, ?, ?, ?)";

	// getUserOrders()
	final static String getUserOrders = "SELECT " + "order_id, " + "amount, " + "date, "
			+ "status, " + "comments " + "FROM orders "
			+ "WHERE user_id = ?";

	// getOrderProdutsMap()
	final static String getOrderProdutsMap = "SELECT " + "id, " + "order_id, " + "product_id, "
			+ "quantity_each, " + "price_each " + "FROM order_details "
			+ "WHERE order_id = ?";

	// updateOrderStatus()
	final static String updateOrderStatus = "UPDATE orders SET status = ? WHERE order_id = ?";

	//  dropTables()
	final static String dropTables = "DROP TABLE IF EXISTS order_details, orders";

	//  createTables()
	final static String createTableOrders = "CREATE TABLE IF NOT EXISTS orders ("
			+ "order_id INT(11) NOT NULL AUTO_INCREMENT, "
			+ "user_id INT(11) NOT NULL, "
			+ "amount DOUBLE NOT NULL, "
			+ "date DATETIME NOT NULL, "
			+ "status VARCHAR(20) NOT NULL, "
			+ "comments TEXT NULL DEFAULT NULL, "
			+ "PRIMARY KEY (order_id), "
			+ "INDEX FK_orders (user_id ASC), "
			+ "CONSTRAINT FK_orders "
			+ "FOREIGN KEY (user_id) "
			+ "REFERENCES user (user_id) "
			+ "ON DELETE CASCADE "
			+ "ON UPDATE CASCADE) "
			+ "ENGINE = InnoDB "
			+ "DEFAULT CHARACTER "
			+ "SET = utf8 "
			+ "COLLATE = utf8_general_ci";

	final static String createTableOrderDetails = "CREATE TABLE IF NOT EXISTS order_details ("
			+ "id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, "
			+ "order_id INT(11) NOT NULL, "
			+ "product_id INT(10) UNSIGNED NOT NULL, "
			+ "quantity_each INT(10) UNSIGNED NOT NULL, "
			+ "price_each DOUBLE NOT NULL, "
			+ "PRIMARY KEY (id), "
			+ "INDEX FK_order_details (order_id ASC), "
			+ "INDEX FK2_order_details (product_id ASC), "
			+ "CONSTRAINT FK2_order_details "
			+ "FOREIGN KEY (product_id) "
			+ "REFERENCES products (product_id) "
			+ "ON DELETE CASCADE "
			+ "ON UPDATE CASCADE, "
			+ "CONSTRAINT FK_order_details "
			+ "FOREIGN KEY (order_id) "
			+ "REFERENCES orders (order_id) "
			+ "ON DELETE CASCADE "
			+ "ON UPDATE CASCADE) "
			+ "ENGINE = InnoDB "
			+ "DEFAULT CHARACTER SET = utf8 "
			+ "COLLATE = utf8_general_ci";

}
