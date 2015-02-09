/**
 *
 */
package ua.store.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import ua.store.model.instances.orders.Order;
import ua.store.model.instances.orders.OrderProduct;
import ua.store.model.instances.orders.OrderStatus;
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class OrderDAO implements MyLogger {

	/**
	 * @param order
	 * @return
	 */
	public int createOrder(Order order) {

		int orderId;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(OrderDAOSqlCommands.createOrder,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, order.getUserId());
			preparedStatement.setDouble(2, order.getAmount());
			preparedStatement.setString(3, order.getOrderDate());
			preparedStatement.setString(4, order.getOrderStatus().toString());
			preparedStatement.setString(5, order.getComments());
			preparedStatement.execute();

			// get order id
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				orderId = resultSet.getInt(1);
			} else {
				orderId = 0;
			}
		} catch (SQLException e) {
			logger.error(
					"An error occurred while executing the PreparedStatement",
					e);
			orderId = 0;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				logger.error(
						"An error occurred while closing a PreparedStatement",
						e);
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

		if (orderId != 0) {
			order.setOrderId(orderId);
			OrderDetailsDAO orderDetailsDAO = DAOFactory.getOrderDetailsDAO();
			boolean isOrderDetailsSuccessful = orderDetailsDAO
					.createOrderDetails(order);
			if (!isOrderDetailsSuccessful) {
				orderId = 0;
				logger.error("Error was happend. OrderDetails did not write to DB");
			}
		} else {
			orderId = 0;
		}

		return orderId;
	}

	/**
	 * @param user
	 * @return
	 */
	public List<Order> getUserOrders(User user) {

		List<Order> orderList = new LinkedList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(OrderDAOSqlCommands.getUserOrders);
			preparedStatement.setInt(1, user.getUserId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("order_id"));
				order.setAmount(resultSet.getDouble("amount"));
				order.setOrderDate(resultSet.getString("date"));
				order.setOrderStatus(OrderStatus.getOrderStatus(resultSet
						.getString("status")));
				order.setComments(resultSet.getString("comments"));

				// get from DB and set to the order a map of products
				order.setOrderProducts(getOrderProdutsMap(connection, order));

				orderList.add(order);
			}
		} catch (SQLException e) {
			logger.error(
					"An error occurred while executing the PreparedStatement",
					e);
			orderList = null;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				logger.error(
						"An error occurred while closing a PreparedStatement",
						e);
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

		return orderList;
	}

	/**
	 * @param connection
	 * @param order
	 * @return
	 */
	private List<OrderProduct> getOrderProdutsMap(Connection connection,
			Order order) {

		List<OrderProduct> orderProducts = new LinkedList<>();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(OrderDAOSqlCommands.getOrderProdutsMap);
			preparedStatement.setInt(1, order.getOrderId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setId(resultSet.getInt("id"));
				orderProduct.setOrderId(resultSet.getInt("order_id"));
				orderProduct.setProductId(resultSet.getInt("product_id"));
				orderProduct.setQuantityEach(resultSet.getInt("quantity_each"));
				orderProduct.setPriceEach(resultSet.getDouble("price_each"));
				orderProducts.add(orderProduct);
			}
		} catch (SQLException e) {
			logger.error(
					"An error occurred while executing the PreparedStatement",
					e);
			orderProducts = null;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a ResultSet", e);
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				logger.error(
						"An error occurred while closing a PreparedStatement",
						e);
			}
		}

		return orderProducts;
	}

	/**
	 * @param order
	 */
	public boolean updateOrderStatus(Order order) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(OrderDAOSqlCommands.updateOrderStatus);
			preparedStatement.setString(1, order.getOrderStatus().toString());
			preparedStatement.setInt(2, order.getOrderId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error(
					"An error occurred while executing the PreparedStatement",
					e);
			return false;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				logger.error(
						"An error occurred while closing a PreparedStatement",
						e);
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}

		return true;

	}

	/**
	 *
	 */
	public void dropTables() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();

			// drop tables "orders" and "order_details"
			preparedStatement = connection.prepareStatement(OrderDAOSqlCommands.dropTables);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			if (logger.isDebugEnabled()) {
				logger.debug("All order tables are successfully dropped");
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
		} finally {
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

	/**
	 *
	 */
	public void createTables() {

		Connection connection = null;
		Statement statement = null;

		try {
			connection = DAOFactory.createConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();

			// create table "orders"
			statement.addBatch(OrderDAOSqlCommands.createTableOrders);

			// create table "order_details"
			statement.addBatch(OrderDAOSqlCommands.createTableOrderDetails);

			statement.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);

			if (logger.isDebugEnabled()) {
				logger.debug("All orders tables are successfully created");
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
		} finally {
			try {
				if (statement != null) statement.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Statement", e);
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing a Connection", e);
			}
		}
	}

}
