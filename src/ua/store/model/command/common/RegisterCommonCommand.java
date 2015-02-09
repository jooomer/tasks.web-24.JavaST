/**
 * 
 */
package ua.store.model.command.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.store.controller.ControllerHelper;
import ua.store.model.command.Command;
import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.UserDAO;
import ua.store.model.instances.users.Client;
import ua.store.model.instances.users.User;
import ua.store.projectservice.ValidationService;

/**
 * @author Sergey
 *
 */
public class RegisterCommonCommand implements Command {

	private static final Logger logger = LogManager.getLogger("ua.shop");
	
	/* (non-Javadoc)
	 * @see ua.shop.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("RegisterCommand - execute() - begin");
		}
		
		User user = makeUser(request, response);
		if (user == null) {
			return;
		}

		HttpSession session = request.getSession(true);
		
		UserDAO userDao = DAOFactory.getUserDAO();

		// check the User in DB 
		if (userDao.doesUserExist(user)) {
			if (logger.isDebugEnabled()) {
				logger.debug("The user already exists in DB. "
						+ "UserName is \"" + user.getUserName() + "\"");
			}
			
			// prepare jsp for forwarding
			String jspPage = "common/login.jsp";
			String title = "Login or register";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}
		
		// create new User in DB		
		boolean isCreatedSuccessfully = userDao.create(user);

		// check the success of creating a new user in DB
		if (isCreatedSuccessfully) {
			if (logger.isDebugEnabled()) {
				logger.debug("New user was correctly created in DB. "
						+ "UserName is \"" + user.getUserName() + "\"");
			}
			
			session.setAttribute("user", user);
			session.setAttribute("successful", true);
			
			// prepare jsp for forwarding
			String jspPage = "common/register_greeting.jsp";
			String title = "Congratulations!";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Error! New user was not correctly created in DB. "
						+ "UserName is \"" + user.getUserName() + "\"");
			}

			// prepare jsp for forwarding
			String jspPage = "common/login.jsp";
			String title = "Login or register";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

		}
		
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private User makeUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

//		String firstName = request.getParameter("firstName");
//		String lastName = request.getParameter("lastName");
//		String userName = request.getParameter("userName");
//		String password = request.getParameter("password");
//		String confirm_password = request.getParameter("confirm_password");
		String firstName = new String(request.getParameter("firstName").getBytes("ISO-8859-1"),"UTF-8");
		String lastName = new String(request.getParameter("lastName").getBytes("ISO-8859-1"),"UTF-8");
		String userName = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");
		String confirm_password = new String(request.getParameter("confirm_password").getBytes("ISO-8859-1"),"UTF-8");

		// validate form filling
		ValidationService validator = new ValidationService();
		if (!validator.isNameValid(firstName) 
				|| !validator.isNameValid(lastName)
				|| !validator.isUserNameValid(userName)
				|| !validator.isPasswordValid(password) 
				|| !validator.isPasswordConfirmed(password, confirm_password)) {
			if (logger.isDebugEnabled()) {
				logger.debug("New user filled out the registration form incorrectly");
			}
			
			// prepare jsp for forwarding
			String jspPage = "common/login.jsp";
			String title = "Login or register";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return null;
		}

		User user = new Client();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(confirm_password);
		
		if (logger.isDebugEnabled()) {
			logger.debug("New user correctly filled out the registration form. "
					+ "UserName is \"" + userName + "\"");
		}
		
		return user;
	}

}
