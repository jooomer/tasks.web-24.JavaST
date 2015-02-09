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
import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.OrderDAO;
import ua.store.model.instances.orders.OrdersList;
import ua.store.model.instances.users.User;
import ua.store.model.pathexecutor.PathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class OrdersCommonPathExecutor implements PathExecutor, MyLogger {

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

		// get list of orders from DB and set it into user
		OrderDAO orderDAO = DAOFactory.getOrderDAO();
		user.setOrders(orderDAO.getUserOrders(user));
		OrdersList ordersList = new OrdersList(user);

		// set list to a request
		session.setAttribute("ordersList", ordersList);

		// prepare jsp for forwarding
		String jspPage = "common/orders.jsp";
		String title = "Orders";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}
}
