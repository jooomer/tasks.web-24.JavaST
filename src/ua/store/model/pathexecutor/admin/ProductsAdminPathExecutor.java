/**
 * 
 */
package ua.store.model.pathexecutor.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.store.model.instances.products.ProductMap;
import ua.store.model.instances.users.User;
import ua.store.model.instances.users.UserType;
import ua.store.model.pathexecutor.PathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class ProductsAdminPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		if (session.getAttribute("successful") != null 
				&& ((User) session.getAttribute("user")).getUserType() == UserType.ADMIN) {
			if (logger.isDebugEnabled()) {
				logger.debug("Administrator is logged in. Go to all_products.jsp.");
			}
			
			ProductMap productMap = new ProductMap();
			
			// set list to a request
			session.setAttribute("productMap", productMap);
			
			// prepare jsp for forwarding
			request.getSession().setAttribute("jspPage", "administrator/all_products.jsp");
			request.getSession().setAttribute("title", "Administrator - products");
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("User is not logged in. Go to error.jsp.");
			}
			request.getSession().setAttribute("jspPage", "common/error.jsp");
			request.getSession().setAttribute("title", "Error");
		}
		request.getRequestDispatcher("/template.jsp").forward(request, response);

	}
}