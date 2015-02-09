/**
 *
 */
package ua.store.model.dao;

/**
 * @author Sergey
 *
 */
public class UserDataDAOSqlCommands {

	// createUserData()
	final static String createUserData = "INSERT INTO user_data (user_id, address, phone, comments) value (?, ?, ?, ?)";

	// updateUserData()
	final static String updateUserData = "UPDATE user_data SET address = ?, phone = ?, comments = ? WHERE user_id = ?";

	// doesUserDataExist()
	final static String doesUserDataExist = "SELECT user_id FROM user_data WHERE user_id = ?";



}
