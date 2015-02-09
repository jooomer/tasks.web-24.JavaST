/**
 *
 */
package ua.store.model.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.store.controller.ControllerHelper;
import ua.store.model.command.Command;
import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.ProductDAO;
import ua.store.model.instances.products.Product;
import ua.store.model.instances.products.ProductList;
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class DeleteProductAdminCommand implements Command, MyLogger {

	/* (non-Javadoc)
	 * @see ua.shop.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("UpdateUserCommand - execute() - begin");
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

		// get and check new data from "update_product" form
		Product newProduct = makeProduct(request, response);
		if (newProduct == null) {

			// prepare jsp for forwarding
			String jspPage = "administrator/product.jsp";
			String title = "Administrator - product";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}

		// delete product in DB
		ProductDAO productDAO = DAOFactory.getProductDAO();
		boolean isProductUpdateSuccessfully = productDAO.deleteByProduct(newProduct);

		// if there was error go to error page
		if (!isProductUpdateSuccessfully) {
			if (logger.isDebugEnabled()) {
				logger.debug("Product \"" + newProduct.getProductName() + "\" is not deleted. There was a problem in a DB connection.");
			}

			// prepare jsp for forwarding
			String jspPage = "common/error.jsp";
			String title = "Error";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Product \"" + newProduct.getProductName() + "\" is deleted successfully.");
		}

		// get list of all product from DB
		ProductList productList = new ProductList();
		productList.createProductList();

		// set list to a session
		session.setAttribute("productList", productList);

		// prepare jsp for forwarding
		String jspPage = "administrator/all_products.jsp";
		String title = "Administrator - products";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private Product makeProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get product to delete
		return (Product) request.getSession().getAttribute("product");
	}


}
