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

import ua.store.model.command.Command;
import ua.store.model.instances.users.User;


/**
 * @author Sergey
 *
 */
public class LogoutCommonCommand implements Command {
	
	private static final Logger logger = LogManager.getLogger("ua.shop");

	/* (non-Javadoc)
	 * @see ua.shop.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getParameter("command") != null && request.getParameter("command").equals("logout")) {
			HttpSession session = request.getSession();
			
			if (logger.isTraceEnabled()) {
				if ((User) session.getAttribute("user") != null) {
					logger.trace("User " + ((User) session.getAttribute("user")).getUserName() + " logged out");
				}
			}
			
			session.invalidate();
			response.sendRedirect("");
		}


	}

}
