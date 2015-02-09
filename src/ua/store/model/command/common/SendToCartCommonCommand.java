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
import ua.store.model.instances.products.Product;
import ua.store.model.instances.products.ProductMap;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class SendToCartCommonCommand implements Command, MyLogger {

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
		Product product = (Product) session.getAttribute("product");

		// if there was error go to error page
		if (product == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Product is not gotten from a session");
			}
			String jspPage = "common/error.jsp";
			String title = "Error";
			String message = "";
			ControllerHelper.sendJspPage(request, response, jspPage, title, message);

			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Product \"" + product.getProductName()
					+ "\" is gotten from a session successfully.");
		}

		// add the product to the cart
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			
		}
		cart.addProduct(product, 1);
		session.setAttribute("cart", cart);
		session.setAttribute("cartMessage", null);		
		
		// prepare ProductMap to show products in a cart page
		ProductMap productMap = new ProductMap(cart);
		session.setAttribute("productMap", productMap);

		String jspPage = "common/cart.jsp";
		String title = "Cart";
		String message = "Product is successfully added to your cart.";
		ControllerHelper.sendJspPage(request, response, jspPage, title, message);

	}


}
