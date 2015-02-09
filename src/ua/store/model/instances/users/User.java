/**
 *
 */
package ua.store.model.instances.users;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ua.store.model.instances.orders.Order;

/**
 * @author Sergey
 *
 */
public class User implements Comparable<User> {
	protected int userId;
	protected UserType userType;
	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String phone;
	protected String address;
	protected String comments;
	protected boolean inBlackList;

	protected List<Order> orders;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if(!(obj instanceof User)) {
			return false;
		}
		User otherUser = (User)obj;
		 
		return new EqualsBuilder().append(this.userName, otherUser.getUserName()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userName).append(userId).toHashCode();
		
				
//		final int prime = 31;
//		int result = 1;
//
//		result = prime * result + this.userName.hashCode();
//		result = prime * result + this.userId;
//
//		return result;
	}

	@Override
	public int compareTo(User user) {
		return this.userId - user.getUserId();
	}

	public User copyUser(User user) {
		user.userId = this.userId;
		user.userName = this.userName;
		user.password = this.password;
		user.firstName = this.firstName;
		user.lastName = this.lastName;
		user.email = this.email;
		user.phone = this.phone;
		user.address = this.address;
		user.comments = this.comments;
		user.inBlackList = this.inBlackList;
		return user;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the inBlackList
	 */
	public boolean isInBlackList() {
		return inBlackList;
	}

	/**
	 * @param inBlackList the inBlackList to set
	 */
	public void setInBlackList(boolean inBlackList) {
		this.inBlackList = inBlackList;
	}

	@Override
	public String toString() {
		String str = "userType: " + userType + "; userName: " + userName;
		return str;
	}

	public void print() {
		System.out.println(this.toString());
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param orders
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * @return the ordersList
	 */
	public List<Order> getOrders() {
		return orders;
	}

}