/**
 *
 */
package ua.store.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.store.model.instances.users.User;

/**
 * @author Sergey
 *
 */
public class UserDataDAO {

	private static final Logger logger = LogManager.getLogger("ua.shop");

	public boolean createUserData(User user) {
		int userId = user.getUserId();
		String address = user.getAddress();
		String phone = user.getPhone();
		String comments = user.getComments();

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(UserDataDAOSqlCommands.createUserData);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, comments);
			preparedStatement.executeUpdate();

			if (logger.isDebugEnabled()) {
				logger.debug("UserData for user \"" + user.getUserName() + "\" is created");
			}

			return true;

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

		return false;
	}

	public boolean updateUserData(User user) {
		int userId = user.getUserId();
		String address = user.getAddress();
		String phone = user.getPhone();
		String comments = user.getComments();

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(UserDataDAOSqlCommands.updateUserData);
			preparedStatement.setString(1, address);
			preparedStatement.setString(2, phone);
			preparedStatement.setString(3, comments);
			preparedStatement.setInt(4, userId);
			preparedStatement.executeUpdate();

			if (logger.isDebugEnabled()) {
				logger.debug("UserData for user \"" + user.getUserName() + "\" is created. "
						+ "UserId=\"" + userId + "\", address=\"" + address + "\", phone=\"" + phone + "\", comments=\"" + comments + "\"" );
			}

			return true;

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


		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ua.shop.dao.Dao#getById(int)
	 */
	public boolean doesUserDataExist(User user) {
		int userId = user.getUserId();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(UserDataDAOSqlCommands.doesUserDataExist);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing a PreparedStatement", e);
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

		return false;
	}

}
