/**
 *
 */
package ua.store.model.pathexecutor.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.store.controller.ControllerHelper;
import ua.store.model.instances.users.User;
import ua.store.model.instances.users.UserType;
import ua.store.model.pathexecutor.PathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class AddProductAdminPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("AdminAddProductPathExecutor - executePath() - begin");
		}

		HttpSession session = request.getSession(true);
		if (session.getAttribute("successful") != null
				&& ((User) session.getAttribute("user")).getUserType() == UserType.ADMIN) {
			if (logger.isDebugEnabled()) {
				logger.debug("Administrator is logged in. Go to add_product.jsp.");
			}

			// prepare jsp for forwarding
			String jspPage = "administrator/add_product.jsp";
			String title = "Administrator - add product";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("User is not logged in. Go to error.jsp.");
			}

			// prepare jsp for forwarding
			String jspPage = "common/error.jsp";
			String title = "Error";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

		}

	}
}
