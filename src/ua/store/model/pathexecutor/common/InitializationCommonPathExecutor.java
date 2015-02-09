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
public class InitializationCommonPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// prepare jsp for forwarding
		String jspPage = "common/initialization.jsp";
		String title = "Initialisation";
		String message = "If you initialize DB, you will lose all your data in DB";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}
}
