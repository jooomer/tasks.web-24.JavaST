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
import ua.store.model.instances.users.UserFactory;
import ua.store.projectservice.MyLogger;
import ua.store.projectservice.ValidationService;

/**
 * @author Sergey
 *
 */
public class UpdateEditUserAdminCommand implements Command, MyLogger {

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

		// update user in DB
		UserDAO userDAO = DAOFactory.getUserDAO();
		boolean isUserUpdateSuccessfully = userDAO.update(newUser);

		// if there was error go to error page
		if (!isUserUpdateSuccessfully) {
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

		session.setAttribute("search_user", newUser);

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
	private User makeUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// check userType from "user" form and create real user type instance
//		String userType = request.getParameter("userType");
		String userType = new String(request.getParameter("userType").getBytes("ISO-8859-1"),"UTF-8");
		User user = UserFactory.createUser(userType);

		// set userId and other data
		User userSql = (User) request.getSession().getAttribute("search_user");
		user = userSql.copyUser(user);

		ValidationService validator = new ValidationService();

		// check comments from edit_user form
//		String comments = request.getParameter("comments");
		String comments = new String(request.getParameter("comments").getBytes("ISO-8859-1"),"UTF-8");
		if (comments != "") {
			if (!validator.isCommentsValid(comments)) {
				sendWrongFormFilling(request, response, comments);
				return null;
			}
			user.setComments(comments);
		} else {
			user.setComments(comments);
		}

		// set inBlackList from edit_user form
		boolean inBlackList = Boolean.valueOf(request.getParameter("inBlackList"));
		user.setInBlackList(inBlackList);

		if (logger.isDebugEnabled()) {
			logger.debug("Administrator correctly filled out the new comments in edit_user form."
					+ "UserName is \"" + user.getUserName() + "\"");
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
			logger.debug("Form filleng is not valid: \"" + string + "\"");
		}

		// prepare jsp for forwarding
		String jspPage = "administrator/found_user.jsp";
		String title = "Administrator - Found user";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}


}
