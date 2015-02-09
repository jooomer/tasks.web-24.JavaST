/**
 *
 */
package ua.store.model.instances.users;

import java.util.Iterator;
import java.util.TreeSet;

import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.UserDAO;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class UserList implements MyLogger {

	private TreeSet<User> userList;
	private Iterator<User> iterator;
	private User user;

	public void createUserList() {

		if (logger.isDebugEnabled()) {
			logger.debug("UserList - createUserList() - begin");
		}

		// get list of all users
		UserDAO userDAO = DAOFactory.getUserDAO();
		TreeSet<User> userList = userDAO.getAllUsers();

		this.userList = userList;
		this.iterator = userList.iterator();
	}

	public void resetIterator() {
		this.iterator = userList.iterator();
	}

	public int getSize() {
		return this.userList.size();
	}

	public String getUserId() {
		if (iterator.hasNext()) {
			this.user = iterator.next();
			return String.valueOf(user.getUserId());
		} else {
			return null;
		}
	}

	public String getUserName() {
		if (user != null) {
			return this.user.getUserName();
		} else {
			return null;
		}
	}

	public String getFirstName() {
		if (user != null) {
			return this.user.getFirstName();
		} else {
			return null;
		}
	}

	public String getLastName() {
		if (user != null) {
			return this.user.getLastName();
		} else {
			return null;
		}
	}

	public String getUserType() {
		if (user != null) {
			return this.user.getUserType().toString();
		} else {
			return null;
		}
	}

	public String getEmail() {
		if (user != null) {
			return this.user.getEmail();
		} else {
			return null;
		}
	}

	public String getPhone() {
		if (user != null) {
			return this.user.getPhone();
		} else {
			return null;
		}
	}

	public String getAddress() {
		if (user != null) {
			return this.user.getAddress();
		} else {
			return null;
		}
	}

	public String getComments() {
		if (user != null) {
			return this.user.getComments();
		} else {
			return null;
		}
	}

	public String getInBlackList() {
		if (user != null) {
			return String.valueOf(this.user.isInBlackList());
		} else {
			return null;
		}
	}


	/**
	 * @return the userList
	 */
	public TreeSet<User> getUserList() {
		return userList;
	}

	public String toString() {
		String str = "";
		for (User user : this.userList) {
			str += user.getUserName() + " ";
		}

		return str;
	}

}
