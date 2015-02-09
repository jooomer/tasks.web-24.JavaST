/**
 *
 */
package ua.store.model.dao;

/**
 * @author Sergey
 *
 */
public class UserDAOSqlCommands {

	// create()
	final static String create = "INSERT INTO user (first_name, last_name, user_name, password, type, email, in_black_list) "
			+ "values (?, ?, ?, ?, ?, ?, ?);";

	final static String create2 = "SELECT user_id FROM user WHERE user_name = ?";

	final static String create3 = "INSERT INTO user_data (user_id, address, phone, comments) "
			+ "values (?, ?, ?, ?);";

	// deleteByUser()
	final static String deleteByUser = "DELETE FROM user WHERE user_id = ?";

	// update()
	final static String update = "UPDATE user SET type = ?, password = ?, first_name = ?, last_name = ?, email = ?, in_black_list = ? WHERE user_name = ?";

	final static String disableFKCheckSql = "SET foreign_key_checks = 0";

	final static String update2 = "UPDATE user_data SET address = ?, phone = ?, comments = ? WHERE user_id = ?";

	final static String enableFKCheckSql = "SET foreign_key_checks = 1";

	// getAllUsers()
	final static String getAllUsers = "SELECT "
			+ "user.user_id, "
			+ "user.user_name, "
			+ "user.password, "
			+ "user.first_name, "
			+ "user.last_name, "
			+ "user.email, "
			+ "user.type, "
			+ "user.in_black_list, "
			+ "user_data.phone, "
			+ "user_data.address, "
			+ "user_data.comments "
			+ "FROM user "
			+ "JOIN user_data "
			+ "ON user_data.user_id = user.user_id";

	// doesUserExist()
	final static String doesUserExist = "SELECT user_name FROM user WHERE user_name = ?";

	// doesUserExist()
	final static String getByUserName = "SELECT "
			+ "user.user_id, "
			+ "user.user_name, "
			+ "user.password, "
			+ "user.first_name, "
			+ "user.last_name, "
			+ "user.email, "
			+ "user.type, "
			+ "user.in_black_list, "
			+ "user_data.phone, "
			+ "user_data.address, "
			+ "user_data.comments "
			+ "FROM user "
			+ "JOIN user_data "
			+ "ON user_data.user_id = user.user_id "
			+ "AND user.user_name = ?";

	// deleteAllUsers()
	final static String deleteAllUsers = "DELETE FROM user WHERE user_id > 0";

	// dropTable()
	final static String dropTables = "DROP TABLE IF EXISTS user_data, user";

	// createTableUser()
	final static String createTableUser = "CREATE TABLE user ("
			+ "user_id int(11) NOT NULL AUTO_INCREMENT, "
			+ "user_name varchar(30) NOT NULL, "
			+ "password varchar(16) NOT NULL, "
			+ "first_name varchar(30) NOT NULL, "
			+ "last_name varchar(30) NOT NULL, "
			+ "email varchar(60) DEFAULT NULL, "
			+ "type varchar(20) NOT NULL, "
			+ "in_black_list tinyint(1) DEFAULT NULL, "
			+ "PRIMARY KEY (user_id), "
			+ "UNIQUE KEY user_name (user_name), "
			+ "UNIQUE KEY email (email) "
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8";

	// createTableUserData()
	final static String createTableUserData = "CREATE TABLE user_data ("
			+ "id int(11) NOT NULL AUTO_INCREMENT, "
			+ "user_id int(11) NOT NULL, "
			+ "address tinytext DEFAULT NULL, "
			+ "phone varchar(20) DEFAULT NULL, "
			+ "comments text DEFAULT NULL, "
			+ "PRIMARY KEY (id), "
			+ "CONSTRAINT FK_user_data "
			+ "FOREIGN KEY (user_id) "
			+ "REFERENCES user (user_id) "
			+ "ON DELETE CASCADE ON UPDATE CASCADE "
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8";

	// createSchema()
	public static String createSchema = "CREATE SCHEMA IF NOT EXISTS store "
			+ "DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;";



}
