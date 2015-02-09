/**
 * 
 */
package ua.store.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sergey
 *
 */
public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
