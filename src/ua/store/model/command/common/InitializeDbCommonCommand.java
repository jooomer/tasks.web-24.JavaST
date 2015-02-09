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
import ua.store.model.instances.Store;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class InitializeDbCommonCommand implements Command, MyLogger {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * ua.shop.command.Command#execute(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug(this.getClass().getSimpleName() + " - execute() - begin");
		}


		System.out.println("Initialization");

		Store.initStore();


		request.getSession().setAttribute("init_success", "init_success");

		String jspPage = "common/initialization.jsp";
		String title = "Initialization";
		String message = "DB is successfully initialized.";
		ControllerHelper.sendJspPage(request, response, jspPage, title, message);

		request.getSession().setAttribute("init_success", null);

	}


}
