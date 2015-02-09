/**
 *
 */
package ua.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class ControllerHelper implements MyLogger {

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void sendPreviouseJspPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/template.jsp")
				.forward(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("Go to the previouse jsp page: \""
					+ (String) request.getAttribute("jspPage") + "\"");
		}

	}

	/**
	 * @param request
	 * @param response
	 * @param jspPage
	 * @param title
	 * @param message
	 * @throws IOException
	 * @throws ServletException
	 */
	public static void sendJspPage(HttpServletRequest request,
			HttpServletResponse response, String jspPage, String title,
			String message) throws ServletException, IOException {

		request.getSession().setAttribute("jspPage", jspPage);
		request.getSession().setAttribute("title", title);
		request.getSession().setAttribute("message", message);
		request.getRequestDispatcher("/template.jsp")
				.forward(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("jsp page is sended: "
					+ "jspPage = \"" + jspPage + "\" "
					+ "title = \"" + title + "\" "
					+ "message = \"" + message + "\"");
		}

	}

}
