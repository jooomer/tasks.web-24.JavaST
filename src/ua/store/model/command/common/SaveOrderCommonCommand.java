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
import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.OrderDAO;
import ua.store.model.instances.Cart;
import ua.store.model.instances.orders.Order;
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;
import ua.store.projectservice.ValidationService;

/**
 * @author Sergey
 *
 */
public class SaveOrderCommonCommand implements Command, MyLogger {

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

		// save comments from an order form
//		String comments = request.getParameter("comments");
		String comments = new String(request.getParameter("comments").getBytes("ISO-8859-1"),"UTF-8");
		ValidationService validationService = new ValidationService();
		if (!validationService.isCommentsValid(comments)) {
			ControllerHelper.sendPreviouseJspPage(request, response);
			return;
		}


		// store order into DB and get orderId
		Order order = (Order) session.getAttribute("order");
		order.setComments(comments);
		OrderDAO orderDAO = DAOFactory.getOrderDAO();
		int orderId = orderDAO.createOrder(order);
		order.setOrderId(orderId);

		// set order into the session
		session.setAttribute("order", order);

		// clear cart
		session.setAttribute("cart", new Cart());
		session.setAttribute("cartMessage", "Your cart is empty.");

		// prepare jsp for forwarding
		String jspPage = "common/order_created.jsp";
		String title = "Checkout";
		String message = "Thank you! Your order is successfully created.";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);


	}
}
