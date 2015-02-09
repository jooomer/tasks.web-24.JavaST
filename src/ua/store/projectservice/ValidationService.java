/**
 * 
 */
package ua.store.projectservice;

import java.text.NumberFormat;
import java.text.ParsePosition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Sergey
 *
 */
public class ValidationService {
	
	private static final Logger logger = LogManager.getLogger("ua.shop");

	/**
	 * @param password
	 * @param confirm_password
	 * @return
	 */
	public boolean isPasswordValid(String password) {
		if (password != null && password.trim() != "") {
			if (logger.isDebugEnabled()) {
				logger.debug("Password is valid");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Password is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param userName
	 * @return
	 */
	public boolean isUserNameValid(String userName) {
		if (userName != null && userName.trim() != "") {
			if (logger.isDebugEnabled()) {
				logger.debug("UserName is valid: \"" + userName + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("UserName is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param firstName
	 * @return
	 */
	public boolean isNameValid(String name) {
		if (name != null && name.trim() != "") {
			if (logger.isDebugEnabled()) {
				logger.debug("Name is valid: \"" + name + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Name is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param password
	 * @param confirm_password
	 * @return
	 */
	public boolean isPasswordConfirmed(String password, String confirm_password) {
		if (password.equals(confirm_password)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Password confirmation is ok");
			}
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Password confirmation is wrong");
			}
			return false;
		}
	}

	/**
	 * @param email
	 * @return
	 */
	public boolean isEmailValid(String email) {
		if (email.trim() != "") {
			if (logger.isDebugEnabled()) {
				logger.debug("Email is valid: \"" + email + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Email is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param phone
	 * @return
	 */
	public boolean isPhoneValid(String phone) {
		if (phone.trim() != "") {
			if (logger.isDebugEnabled()) {
				logger.debug("Phone is valid: \"" + phone + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Phone is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param address
	 * @return
	 */
	public boolean isAddressValid(String address) {
		if (address.trim() != "") {
			if (logger.isDebugEnabled()) {
				logger.debug("Addressis valid: \"" + address + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Address is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param comments
	 * @return
	 */
	public boolean isCommentsValid(String comments) {
		if (comments.trim() != "") {
			if (logger.isDebugEnabled()) {
				logger.debug("Comments valid: \"" + comments + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Comments is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param productName
	 * @return
	 */
	public boolean isProductNameValid(String productName) {
		if (productName.trim() != "") {
			if (logger.isDebugEnabled()) {
				logger.debug("ProductName is valid: \"" + productName + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("ProductName is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param price
	 * @return
	 */
	public boolean isPriceValid(String price) {
		if (price.trim() != "" && isNumeric(price)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Price: \"" + price + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Price is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param price
	 * @return
	 */
	private boolean isNumeric(String str) {
		  NumberFormat formatter = NumberFormat.getInstance();
		  ParsePosition pos = new ParsePosition(0);
		  formatter.parse(str, pos);
		  return str.length() == pos.getIndex();
	}

	/**
	 * @param quantityInStock
	 * @return
	 */
	public boolean isQuantityInStockValid(String quantityInStock) {
		if (quantityInStock.trim() != "" && isNumeric(quantityInStock)) {
			if (logger.isDebugEnabled()) {
				logger.debug("QuantityInStock valid: \"" + quantityInStock + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("QuantityInStock is not valid");
			}			
			return false;
		}
	}

	/**
	 * @param description
	 * @return
	 */
	public boolean isDescriptionValid(String description) {
		if (description.trim() != "") {
			if (logger.isDebugEnabled()) {
				logger.debug("Description valid: \"" + description + "\"");
			}			
			return true;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Description is not valid");
			}			
			return false;
		}
	}



}
