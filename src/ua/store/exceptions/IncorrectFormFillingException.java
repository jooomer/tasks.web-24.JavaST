/**
 * 
 */
package ua.store.exceptions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Sergey
 *
 */
public class IncorrectFormFillingException extends Exception {
	
	ExceptionData exceptionData;

	private static final Logger logger = LogManager.getLogger("ua.shop");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param exceptionData
	 */
	public IncorrectFormFillingException(ExceptionData exceptionData) {
		this.exceptionData = exceptionData;
		
		if (logger.isTraceEnabled()) {
			logger.trace("IncorrectFormFillingException is thrown");
		}
	}
	
	public void handleException() throws ServletException, IOException {
		HttpServletRequest request = exceptionData.getRequest();
		HttpServletResponse response = exceptionData.getResponse();
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login");
		requestDispatcher.forward(request, response);
		
	}
	
}
