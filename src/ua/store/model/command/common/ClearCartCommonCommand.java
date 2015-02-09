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
import ua.store.model.instances.products.ProductMap;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class ClearCartCommonCommand implements Command, MyLogger {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.shop.command.Command#execute(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug(this.getClass().getSimpleName() + " - execute() - begin");
		}

		// get product from session
		HttpSession session = request.getSession();

		// set a new cart to the session
		Cart cart = new Cart();
		session.setAttribute("cart", null);
		session.setAttribute("cartMessage", "Your cart is empty.");		
		
		if (logger.isDebugEnabled()) {
			logger.debug(" Cart is cleared");
		}

		// prepare ProductList to show products in a cart page
		ProductMap productMap = new ProductMap(cart);

		// set list to a request
		session.setAttribute("productMap", productMap);

		String jspPage = "common/cart.jsp";
		String title = "Cart";
		String message = "Cart is successfully cleared.";
		ControllerHelper.sendJspPage(request, response, jspPage, title, message);

	}


}
