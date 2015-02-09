/**
 * 
 */
package ua.store.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.store.controller.ControllerHelper;

/**
 * @author Sergey
 *
 */
public class BadCommand implements Command {
	
	private static final Logger logger = LogManager.getLogger("ua.shop");


	/* (non-Javadoc)
	 * @see ua.shop.command.Command#exequte(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("BadCommand - execute() - begin");
		}

		// prepare jsp for forwarding
		String jspPage = "common/bad_command.jsp";
		String title = "Error. Bad command";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}

}
