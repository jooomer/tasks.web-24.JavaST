/**
 *
 */
package ua.store.model.command.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.store.controller.ControllerHelper;
import ua.store.model.command.Command;
import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.UserDAO;
import ua.store.model.dao.UserDataDAO;
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;
import ua.store.projectservice.ValidationService;

/**
 * @author Sergey
 *
 */
public class UpdateUserCommonCommand implements Command, MyLogger {

	/* (non-Javadoc)
	 * @see ua.shop.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("UpdateUserCommand - execute() - begin");
		}

		// get and check user in a session?
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if (user == null) {

			// prepare jsp for forwarding
			String jspPage = "common/login.jsp";
			String title = "Login or Register";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}

		// get and check new data from account form
		User newUser = makeUser(request, response);
		if (newUser == null) {

			// prepare jsp for forwarding
			String jspPage = "common/login.jsp";
			String title = "Login or Register";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}

		// update user in DB
		UserDAO userDAO = DAOFactory.getUserDAO();
		boolean isUserUpdateSuccessfully = userDAO.update(newUser);

		// check existing userData for update
		UserDataDAO userDataDAO = DAOFactory.getUserDataDAO();
		boolean isUserDataUpdateSuccessfully;
		if (userDataDAO.doesUserDataExist(newUser)) {
			// update userData in DB
			userDataDAO = DAOFactory.getUserDataDAO();
			isUserDataUpdateSuccessfully = userDataDAO.updateUserData(newUser);
		} else {
			// create userData in DB
			userDataDAO = DAOFactory.getUserDataDAO();
			isUserDataUpdateSuccessfully = userDataDAO.createUserData(newUser);
		}

		// if there was error go to error page
		if (!isUserUpdateSuccessfully || !isUserDataUpdateSuccessfully) {
			if (logger.isDebugEnabled()) {
				logger.debug("User \"" + newUser.getUserName() + "\" is not updated. There was a problem in a DB connection.");
			}

			// prepare jsp for forwarding
			String jspPage = "common/error.jsp";
			String title = "Error";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("User \"" + newUser.getUserName() + "\" is updated successfully.");
		}

		// prepare jsp for forwarding
		String jspPage = "common/message.jsp";
		String title = "Successfully updated";
		String message = "Congratulations! Your account is successfully updated!";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);


	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private User makeUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		String userName = user.getUserName();

		ValidationService validator = new ValidationService();

		// check firstName from account form
		String firstName = request.getParameter("firstName");
//		String firstName = new String(request.getParameter("firstName").getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(firstName);
		System.out.println(request.getCharacterEncoding());

		if (firstName != "") {
			if (!validator.isNameValid(firstName)) {
				sendWrongFormFilling(request, response, firstName);
				return null;
			}
		} else {
			firstName = user.getFirstName();
		}

		// check lastName from account form
//		String lastName = request.getParameter("lastName");
		String lastName = new String(request.getParameter("lastName").getBytes("ISO-8859-1"),"UTF-8");
		if (lastName != "") {
			if (!validator.isNameValid(lastName)) {
				sendWrongFormFilling(request, response, lastName);
				return null;
			}
		} else {
			lastName = user.getLastName();
		}

		// check email from account form
//		String email = request.getParameter("email");
		String email = new String(request.getParameter("email").getBytes("ISO-8859-1"),"UTF-8");
		if (email != "") {
			if (!validator.isEmailValid(email)) {
				sendWrongFormFilling(request, response, email);
				return null;
			}
		} else {
			email = user.getEmail();
		}

		// check phone from account form
//		String phone = request.getParameter("phone");
		String phone = new String(request.getParameter("phone").getBytes("ISO-8859-1"),"UTF-8");
		if (phone != "") {
			if (!validator.isPhoneValid(phone)) {
				sendWrongFormFilling(request, response, phone);
				return null;
			}
		} else {
			phone = user.getPhone();
		}

		// check address from account form
//		String address = request.getParameter("address");
		String address = new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8");
		if (address != "") {
			if (!validator.isAddressValid(address)) {
				sendWrongFormFilling(request, response, address);
				return null;
			}
		} else 	{
			address = user.getAddress();
		}

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddress(address);

		if (logger.isDebugEnabled()) {
			logger.debug("User correctly filled out the new data in account form."
					+ "UserName is \"" + userName + "\"");
		}

		return user;
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void sendWrongFormFilling(HttpServletRequest request,
			HttpServletResponse response, String string) throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("String from form is not valid: \"" + string + "\"");
		}

		// prepare jsp for forwarding
		String jspPage = "common/my_account.jsp";
		String title = "My account";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}


}
