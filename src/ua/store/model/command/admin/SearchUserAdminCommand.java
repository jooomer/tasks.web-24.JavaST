/**
 * 
 */
package ua.store.model.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.store.controller.ControllerHelper;
import ua.store.model.command.Command;
import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.UserDAO;
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;
import ua.store.projectservice.ValidationService;

/**
 * @author Sergey
 *
 */
public class SearchUserAdminCommand implements Command, MyLogger {

	/* (non-Javadoc)
	 * @see ua.shop.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("SearchCommand - execute() - begin");
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
		
		// get and check data from search form
		String userName = request.getParameter("userName");
		ValidationService validator = new ValidationService();
		if (!validator.isNameValid(userName)) {
			sendWrongFormFilling(request, response, userName);
			return;
		}		

		// check existence of user in DB
		UserDAO userDAO = DAOFactory.getUserDAO();
		if (!userDAO.doesUserExist(userName)) {
			if (logger.isDebugEnabled()) {
				logger.debug("User \"" + userName + "\" is absent in DB.");
			}
			
			// prepare jsp for forwarding
			String jspPage = "administrator/users.jsp";
			String title = "Administrator - List of users";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}
		
		// get user from DB
		User userSql = userDAO.getByUserName(userName);
				
		// if there was error go to error page
		if (userSql == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("User \"" + userName + "\" is not gotten. There was a problem in a DB connection.");
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
			logger.debug("User \"" + userName + "\" is gotten successfully.");
		}
		
		// set user which is found to the request
		session.setAttribute("search_user", userSql);
		
		// prepare jsp for forwarding
		String jspPage = "administrator/found_user.jsp";
		String title = "Administrator - Found user";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);
		
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
			logger.debug("Form filling is not valid: \"" + string + "\"");
		}

		// prepare jsp for forwarding
		String jspPage = "administrator/users.jsp";
		String title = "Administrator - List of users";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}


}
