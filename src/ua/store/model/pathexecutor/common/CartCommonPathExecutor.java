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
import ua.store.model.instances.products.ProductMap;
import ua.store.model.pathexecutor.PathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class CartCommonPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug(this.getClass().getSimpleName() + " - executePath() - begin");
		}
		
		HttpSession session = request.getSession(true);

		// get cart
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {

			if (logger.isDebugEnabled()) {
				logger.debug("Cart is empty. Make a new cart");
			}
			
			cart = new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("cartMessage", "Your cart is empty.");
			
			// prepare ProductList to show products in a cart page
			ProductMap productMap = new ProductMap(cart);
			session.setAttribute("productMap", productMap);

			// prepare jsp for forwarding
			String jspPage = "common/cart.jsp";
			String title = "Cart";
			String message = null;
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);
		} else {

			if (logger.isDebugEnabled()) {
				logger.debug("Cart is present. Show cart");
			}
			
			// reset cartMessage
			session.setAttribute("cartMessage", null);
			
			// prepare ProductList to show products in a cart page
			ProductMap productMap = new ProductMap(cart);
			session.setAttribute("productMap", productMap);

			// prepare jsp for forwarding
			String jspPage = "common/cart.jsp";
			String title = "Cart";
			String message = null;
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);
		}
		

	}
}
