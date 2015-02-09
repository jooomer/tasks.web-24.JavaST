/**
 *
 */
package ua.store.model.command.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.store.controller.ControllerHelper;
import ua.store.model.command.Command;
import ua.store.model.instances.Cart;
import ua.store.model.instances.orders.Order;
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class MakeOrderCommonCommand implements Command, MyLogger {

	/* (non-Javadoc)
	 * @see ua.store.controller.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Order order = new Order(user, cart);
		request.getSession().setAttribute("order", order);

		// prepare jsp for forwarding
		String jspPage = "common/order.jsp";
		String title = "Order";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);


	}
}
