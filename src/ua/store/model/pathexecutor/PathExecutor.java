/**
 * 
 */
package ua.store.model.pathexecutor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sergey
 *
 */
public interface PathExecutor {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

}
