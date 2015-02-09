/**
 *
 */
package ua.store.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import ua.store.model.instances.users.User;
import ua.store.model.instances.users.UserFactory;
import ua.store.model.instances.users.UserType;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class UserDAO implements MyLogger {

	/*
	 * (non-Javadoc)
	 *
	 * @see ua.shop.dao.Dao#create(java.lang.Object)
	 */
	public boolean create(User user) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOFactory.createConnection();

			// send user to table "user"
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.create);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getUserName());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getUserType().toString());
			preparedStatement.setString(6, user.getEmail());
			preparedStatement.setBoolean(7, user.isInBlackList());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			// get userId
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.create2);
			preparedStatement.setString(1, user.getUserName());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int userId = resultSet.getInt(1);
			preparedStatement.close();

			// send userData to table "user_data"
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.create3);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, user.getAddress());
			preparedStatement.setString(3, user.getPhone());
			preparedStatement.setString(4, user.getComments());
			preparedStatement.executeUpdate();
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

	/*
	 * (non-Javadoc)
	 *
	 * @see ua.shop.dao.Dao#deleteByUser(java.lang.Object)
	 */
	public boolean deleteByUser(User user) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();

			// delete user from table "user"
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.deleteByUser);
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			if (logger.isDebugEnabled()) {
				logger.debug("User \"" + user.getUserName() + "\" is deleted");
			}

			return true;

		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
			return false;
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

	/*
	 * (non-Javadoc)
	 *
	 * @see ua.shop.dao.Dao#update(java.lang.Object)
	 */
	public boolean update(User user) {

		int userId = user.getUserId();
		UserType userType = user.getUserType();
		String userName = user.getUserName();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String phone = user.getPhone();
		String address = user.getAddress();
		String comments = user.getComments();
		boolean inBlackList = user.isInBlackList();

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();

			// send user to table "user"
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.update);
			preparedStatement.setString(1, userType.toString());
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, email);
			preparedStatement.setBoolean(6, inBlackList);
			preparedStatement.setString(7, userName);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.disableFKCheckSql);
			preparedStatement.execute();
			preparedStatement.close();

			// send user to table "user_data"
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.update2);
			preparedStatement.setString(1, address);
			preparedStatement.setString(2, phone);
			preparedStatement.setString(3, comments);
			preparedStatement.setInt(4, userId);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.enableFKCheckSql);
			preparedStatement.execute();

			if (logger.isDebugEnabled()) {
				logger.debug("User \"" + userName + "\" is updated");
			}

			return true;

		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
			return false;
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

	/*
	 * (non-Javadoc)
	 *
	 * @see ua.shop.dao.Dao#getAll()
	 */
	public TreeSet<User> getAllUsers() {
		TreeSet<User> userList = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connection = DAOFactory.createConnection();
		try {
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.getAllUsers);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userList = createUserList(resultSet);
				if (logger.isDebugEnabled()) {
					logger.debug("Users list is successfully gotten from DB");
				}
				return userList;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Error. Users list is empty");
				}
				return null;
			}
		} catch (SQLException e) {
			logger.error("An error occurred while executing the PreparedStatement", e);
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

		return userList;
	}

	/**
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private TreeSet<User> createUserList(ResultSet resultSet) throws SQLException {
		TreeSet<User> userList = new TreeSet<>();
		resultSet.beforeFirst();
		while (resultSet.next()) {
			User user = createUserFromResultSet(resultSet);
			userList.add(user);
		}
		return userList;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see ua.shop.dao.Dao#getById(int)
	 */
	public boolean doesUserExist(User user) {
		return doesUserExist(user.getUserName());
	}

	/**
	 * @param userName
	 * @return
	 */
	public boolean doesUserExist(String userName) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.doesUserExist);
			preparedStatement.setString(1, userName);
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

	/**
	 * @param userName
	 * @return
	 */
	public User getByUserName(String userName) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User userSql = null;

		try {
			connection = DAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.getByUserName);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				userSql = createUserFromResultSet(resultSet);
				if (logger.isDebugEnabled()) {
					logger.debug("User \"" + userName + "\" is obtained");
				}
				return userSql;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("User \"" + userName + "\" is absent in DB");
				}
				return null;
			}
		} catch (SQLException e) {
			logger.error(
					"An error occurred while executing the PreparedStatement", e);
			return null;
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

	private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
		User user = UserFactory.createUser(resultSet.getString("type"));
		user.setUserId(resultSet.getInt("user_id"));
		user.setUserName(resultSet.getString("user_name"));
		user.setPassword(resultSet.getString("password"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setLastName(resultSet.getString("last_name"));
		user.setEmail(resultSet.getString("email"));
		user.setInBlackList(resultSet.getBoolean("in_black_list"));
		user.setPhone(resultSet.getString("phone"));
		user.setAddress(resultSet.getString("address"));
		user.setComments(resultSet.getString("comments"));
		return user;
	}

	/**
	 *
	 */
	public void deleteAllUsers() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();

			// delete all users from table "user" and "user_data"
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.deleteAllUsers);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			if (logger.isDebugEnabled()) {
				logger.debug("All users are successfully deleted");
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
	public void dropTables() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DAOFactory.createConnection();

			// drop tables "user" and "user_data"
			preparedStatement = connection.prepareStatement(UserDAOSqlCommands.dropTables);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			if (logger.isDebugEnabled()) {
				logger.debug("All users are successfully deleted");
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

			// create table "user"
			statement.addBatch(UserDAOSqlCommands.createTableUser);

			// create table "user_data"
			statement.addBatch(UserDAOSqlCommands.createTableUserData);

			statement.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);

			if (logger.isDebugEnabled()) {
				logger.debug("All users tables are successfully created");
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

	/**
	 *
	 */
	public void createSchema() {

		Connection connection = null;
		Statement statement = null;

		try {
			connection = DAOFactory.createConnection();
			statement = connection.createStatement();

			// create schema
			statement.execute(UserDAOSqlCommands.createSchema);

			if (logger.isDebugEnabled()) {
				logger.debug("All users tables are successfully created");
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
