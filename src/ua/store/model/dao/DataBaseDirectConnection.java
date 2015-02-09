/**
 *
 */
package ua.store.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class DataBaseDirectConnection implements MyLogger {

	private static String driver = "com.mysql.jdbc.Driver";
//	private static String url = "jdbc:mysql://localhost:3306/shop";
	private static String url = "jdbc:mysql://localhost:3306/store";
	private static String user = "root";
	private static String password = "root";

	public static Connection getConnection() {
		Connection connection = null;;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
			if (logger.isDebugEnabled()) {
				logger.debug("Application connected directly to DB successfully");
			}
		} catch (ClassNotFoundException e) {
			logger.error("An error occurred while searching the JDBC Driver", e);
		} catch (SQLException e) {
			logger.error("An error occurred while getting the DB Connection", e);
		}

		return connection;
	}

	public static Statement getStatement(Connection connection) {
		Statement statement = null;

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			logger.error("An error occurred while creating the Statement", e);
		}
		return statement;
	}

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("An error occurred while closing the DB Connection", e);
			}
		}
	}

}
