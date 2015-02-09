/**
 *
 */
package ua.store.model.pathexecutor.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.store.controller.ControllerHelper;
import ua.store.model.pathexecutor.PathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class RootCommonPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug(this.getClass().getSimpleName() + " - executePath() - begin");
		}

		// prepare jsp for forwarding
		String jspPage = "common/main.jsp";
		String title = "Home";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}
}
