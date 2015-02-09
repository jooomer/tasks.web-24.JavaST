/**
 * 
 */
package ua.store.model.dao;

import java.sql.Connection;

/**
 * @author Sergey
 *
 */
public class DAOFactory {
	
	public static Connection createConnection() {
//		return DataBaseDirectConnection.getConnection();
		return  DataBaseTomCatPoolConnection.getConnection();
	}

	public static UserDAO getUserDAO() {
		return new UserDAO();
	}
	
	public static UserDataDAO getUserDataDAO() {
		return new UserDataDAO();
	}
	
	public static ProductDAO getProductDAO() {
		return new ProductDAO();
	}
	
	public static OrderDAO getOrderDAO() {
		return new OrderDAO();
	}

	public static OrderDetailsDAO getOrderDetailsDAO() {
		return new OrderDetailsDAO();
	}
}
