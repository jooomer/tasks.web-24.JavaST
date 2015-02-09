/**
 * 
 */
package ua.store.model.pathexecutor.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.store.model.pathexecutor.PathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class RootAdminPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("successful") != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("User is logged in. Go to administrator.jsp.");
			}
			request.getSession().setAttribute("jspPage", "administrator/administrator.jsp");
			request.getSession().setAttribute("title", "Administrator - main");
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("User is not logged in. Go to error.jsp.");
			}
			request.getSession().setAttribute("jspPage", "common/error.jsp");
			request.getSession().setAttribute("title", "Error");
		}
		
		request.getRequestDispatcher("/template.jsp").forward(request, response);

	}
}
