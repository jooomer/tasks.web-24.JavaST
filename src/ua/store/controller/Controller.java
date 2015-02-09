package ua.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;

import ua.store.model.command.Command;
import ua.store.model.command.CommandFactory;
import ua.store.model.pathexecutor.PathExecutor;
import ua.store.model.pathexecutor.PathExecutorFactory;
import ua.store.projectservice.MyLogger;

/**
 * Servlet implementation class Controller
 */
//@WebServlet(name = "Controller", urlPatterns = { "/" })
public class Controller extends HttpServlet implements MyLogger {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug(this.getClass().getSimpleName() + " - doGet() - begin");
		}

		executeRequest(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Controller - doPost() - begin");
		}

		executeRequest(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void executeRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// if request has some parameters, Commands should handle this request
		// else - PathExecutors
		if (MapUtils.isNotEmpty(request.getParameterMap())) {
			Command command = CommandFactory.createCommand(request);
			command.execute(request, response);
		} else {
			PathExecutor pathExecutor = PathExecutorFactory.getPathExecutor(request);
			pathExecutor.executePath(request, response);
		}

	}

}
