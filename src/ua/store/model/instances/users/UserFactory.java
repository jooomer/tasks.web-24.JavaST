/**
 * 
 */
package ua.store.model.instances.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Sergey
 *
 */
public class UserFactory {

	private static final Logger logger = LogManager.getLogger("ua.shop");
	
	public static User createUser(String userType) {

		if (logger.isDebugEnabled()) {
			logger.debug("UserType is " + userType);
		}
		
		switch (userType) {
		case "CLIENT":
			return new Client();
		case "ADMIN":
			return new Administrator();
		default:
			logger.debug("Cannot create a new user. Wrong userType");
		}
		return null;
	}

	/**
	 * @param userType
	 * @return
	 */
	public static User createUser(UserType userType) {
		return createUser(userType.toString());
	}
}
