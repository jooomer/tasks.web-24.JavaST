/**
 *
 */
package ua.store.model.dao;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class DataBaseTomCatPoolConnection implements MyLogger {

	public static Connection getConnection() {
		Connection connection = null;
		InitialContext initContext = null;
		try {
			initContext = new InitialContext();
		} catch (NamingException e) {
			logger.error("An error occurred while creating the new InitialContext", e);
		}
		DataSource ds = null;
		try {
//			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/shop");
			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/store");
		} catch (NamingException e) {
			logger.error("An error occurred while getting the DataSource", e);
		}
		try {
			connection = ds.getConnection();
			if (logger.isDebugEnabled()) {
				logger.debug("Application connected to DB by TOMCAT pool successfully");
			}
		} catch (SQLException e) {
			logger.error("An error occurred while getting the DB Connection", e);
		}
		return connection;
	}
}
