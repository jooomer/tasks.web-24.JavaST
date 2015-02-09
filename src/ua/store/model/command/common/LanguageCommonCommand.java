/**
 *
 */
package ua.store.model.command.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.store.controller.ControllerHelper;
import ua.store.model.command.Command;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class LanguageCommonCommand implements Command, MyLogger {

	/*
	 * (non-Javadoc)
	 *
	 * @see ua.store.controller.command.Command#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("Method begun");

		ControllerHelper.sendPreviouseJspPage(request, response);

	}
}
