/**
 *
 */
package ua.store.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.store.model.instances.orders.Order;
import ua.store.model.instances.orders.OrderProduct;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class OrderDetailsDAO implements MyLogger {

	/**
	 * @param order
	 * @return
	 */
	public boolean createOrderDetails(Order order) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(OrderDetailsDAOSqlCommands.createOrderDetails);

			for (OrderProduct orderProduct : order.getOrderProducts()) {
				preparedStatement.setInt(1, order.getOrderId());
				preparedStatement.setInt(2, orderProduct.getProductId());
				preparedStatement.setInt(3, orderProduct.getQuantityEach());
				preparedStatement.setDouble(4, orderProduct.getPriceEach());
				preparedStatement.execute();
			}

			return true;

		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
			return false;
		} finally {
			try {
				if (resultSet != null) resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a PreparedStatement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}
	}

}
