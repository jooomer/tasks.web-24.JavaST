/**
 *
 */
package ua.store.model.pathexecutor.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.store.controller.ControllerHelper;
import ua.store.model.pathexecutor.PathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class LoginCommonPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("successful") != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("/is_entered.jsp");
			}

			// prepare jsp for forwarding
			String jspPage = "common/is_registered.jsp";
			String title = "Successfully registered";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("/login.jsp");
			}

			// prepare jsp for forwarding
			String jspPage = "common/login.jsp";
			String title = "Login or register";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);
		}

	}
}
