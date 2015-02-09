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
import ua.store.model.instances.users.UserList;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class DeleteUserAdminCommand implements Command, MyLogger {
	
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
		
		// get and check new data from "edit user" form
		User newUser = makeUser(request, response);
		if (newUser == null) {
			
			// prepare jsp for forwarding
			String jspPage = "administrator/found_user.jsp";
			String title = "Found user";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}
		
		// delete user in DB
		UserDAO userDAO = DAOFactory.getUserDAO();
		boolean isUserUpdateSuccessfully = userDAO.deleteByUser(newUser);
		
		// if there was error go to error page
		if (!isUserUpdateSuccessfully) {
			if (logger.isDebugEnabled()) {
				logger.debug("User \"" + newUser.getUserName() + "\" is not deleted. There was a problem in a DB connection.");
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
			logger.debug("User \"" + newUser.getUserName() + "\" is deleted successfully.");
		}
		
		// get list of all users from DB
		UserList userList = new UserList();
		userList.createUserList();
		
		// set list to a session
		session.setAttribute("userList", userList);
		
		// prepare jsp for forwarding
		String jspPage = "administrator/users.jsp";
		String title = "Administrator - users";
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
	private User makeUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get user to delete
		return (User) request.getSession().getAttribute("search_user");	
	}


}
