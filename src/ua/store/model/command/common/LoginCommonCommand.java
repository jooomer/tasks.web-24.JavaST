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
import ua.store.model.instances.users.User;
import ua.store.model.instances.users.UserFactory;
import ua.store.model.instances.users.UserType;
import ua.store.projectservice.ValidationService;

/**
 * @author Sergey
 *
 */
public class LoginCommonCommand implements Command {

	private static final Logger logger = LogManager.getLogger(LoginCommonCommand.class);

	/* (non-Javadoc)
	 * @see ua.shop.command.Command#exequte(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (logger.isTraceEnabled()) {
			logger.trace("LoginCommand - execute() - begin");
		}

		// get user data from request
//		String userName = request.getParameter("userName");
//		String password = request.getParameter("password");
		String userName = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");

		if (logger.isTraceEnabled()) {
			logger.trace("UserName: \"" + userName + "\", password: \"" + password + "\"");
		}

		// validate form filling
		ValidationService validator = new ValidationService();
		if (!validator.isUserNameValid(userName) || !validator.isPasswordValid(password)) {
			if (logger.isDebugEnabled()) {
				logger.debug("User filled out the login form incorrectly");
			}

			// prepare jsp for forwarding
			String jspPage = "common/login.jsp";
			String title = "Login or register";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}

		// get User from DB
		UserDAO userDao = DAOFactory.getUserDAO();
		User userSql = userDao.getByUserName(userName);

		// check if user exists in DB, then set it to the session and go to main page.
		// if doesn't, just go to error.jsp
		if (userSql != null && userSql.getPassword().equals(password)) {

			// create real user instance: CLIENT, ADMIN, ...
			userSql = createUserInstanceByUserType(userSql);

			// set user to a session
			HttpSession session = request.getSession(true);
			session.setAttribute("user", userSql);
			session.setAttribute("successful", true);
			if (logger.isDebugEnabled()) {
				logger.debug("User \"" + ((User)session.getAttribute("user")).getUserName() + "\" logged in");
			}

			// go to main page for ADMINISTRATOR or for CLIENT
			if (userSql.getUserType() == UserType.ADMIN) {

				// prepare jsp for forwarding
				String jspPage = "administrator/administrator.jsp";
				String title = "Administrator - main";
				String message = "";
				ControllerHelper
						.sendJspPage(request, response, jspPage, title, message);

			} else {

				// prepare jsp for forwarding
				String jspPage = "common/main.jsp";
				String title = "Home";
				String message = "";
				ControllerHelper
						.sendJspPage(request, response, jspPage, title, message);

			}
		} else {

			// prepare jsp for forwarding
			String jspPage = "common/error.jsp";
			String title = "Error";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

		}


	}

	/**
	 * @param userSql
	 * @return
	 */
	private User createUserInstanceByUserType(User userSql) {
		User user = null;
		switch (userSql.getUserType()) {
		case CLIENT:
			user = UserFactory.createUser(UserType.CLIENT);
			break;
		case ADMIN:
			user = UserFactory.createUser(UserType.ADMIN);
			break;
		default:
			break;
		}
		return userSql.copyUser(user);
	}

}
