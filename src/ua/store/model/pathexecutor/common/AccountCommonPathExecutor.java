/**
 * 
 */
package ua.store.model.pathexecutor.common;

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
public class AccountCommonPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("successful") != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("User is logged in. Go to my_account.jsp.");
			}
			request.getSession().setAttribute("jspPage", "common/my_account.jsp");
			request.getSession().setAttribute("title", "My account");
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
