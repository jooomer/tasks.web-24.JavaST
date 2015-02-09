/**
 * 
 */
package ua.store.model.pathexecutor.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.store.controller.ControllerHelper;
import ua.store.model.instances.Cart;
import ua.store.model.instances.orders.Order;
import ua.store.model.instances.products.ProductMap;
import ua.store.model.instances.users.User;
import ua.store.model.pathexecutor.PathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class OrderCommonPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug(this.getClass().getSimpleName()
					+ " - executePath() - begin");
		}

		// get and check user in a session?
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if (user == null) {
			
			// prepare jsp for forwarding
			String jspPage = "common/login.jsp";
			String title = "Login or Register";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);
			return;
		}
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		// create an order
		Order order = new Order(user, cart);		
		
		// set order into the session
		session.setAttribute("order", order);
		
		// prepare ProductList to show products in a order page
		ProductMap productMap = new ProductMap(cart);
		session.setAttribute("productMap", productMap);

		// prepare jsp for forwarding
		String jspPage = "common/order.jsp";
		String title = "Order";
		String message = "Your goods are successfully ordered.";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}
}
