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
import ua.store.model.instances.products.ProductFactory;
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;
import ua.store.projectservice.ValidationService;

/**
 * @author Sergey
 *
 */
public class AddProductAdminCommand implements Command, MyLogger {

	/* (non-Javadoc)
	 * @see ua.shop.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug(this.getClass().getSimpleName() + " - execute() - begin");
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

		// get and check new data from "add new product" form
		Product newProduct = makeProduct(request, response);
		if (newProduct == null) {

			// prepare jsp for forwarding
			String jspPage = "administrator/add_product.jsp";
			String title = "Administrator - add product";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}

		// create product in DB
		ProductDAO productDAO = DAOFactory.getProductDAO();
		boolean isProductCreateSuccessfully = productDAO.create(newProduct);

		// if there was error go to error page
		if (!isProductCreateSuccessfully) {
			if (logger.isDebugEnabled()) {
				logger.debug("Product \"" + newProduct.getProductName() + "\" is not created. There was a problem in a DB connection.");
			}

			// prepare jsp for forwarding
			String jspPage = "common/error.jsp";
			String title = "Error";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}

		// get this product from DB
		productDAO = DAOFactory.getProductDAO();
		Product productSql = productDAO.getByName(newProduct.getProductName());

		// if there was error go to error page
		if (productSql == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Product \"" + newProduct.getProductName() + "\" is not gotten. There was a problem in a DB connection.");
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
			logger.debug("Product \"" + newProduct.getProductName() + "\" is created successfully.");
		}

		session.setAttribute("product", productSql);

		// prepare jsp for forwarding
		String jspPage = "administrator/product.jsp";
		String title = "Administrator - Product";
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

		// check productType from "add product" form and create real product type
		String productType = request.getParameter("productType");
		Product product = ProductFactory.createProduct(productType);

		ValidationService validator = new ValidationService();

		// check productName from "add product" form
//		String productName = request.getParameter("productName");
		String productName = new String(request.getParameter("productName").getBytes("ISO-8859-1"),"UTF-8");
		if (!validator.isProductNameValid(productName)) {
			sendWrongFormFilling(request, response, productName);
			return null;
		}
		product.setProductName(productName);

		// check price from "add product" form
//		String price = request.getParameter("price");
		String price = new String(request.getParameter("price").getBytes("ISO-8859-1"),"UTF-8");
		if (!validator.isPriceValid(price)) {
			sendWrongFormFilling(request, response, price);
			return null;
		}
		product.setPrice(Double.valueOf(price));

		// check price from "add product" form
//		String quantityInStock = request.getParameter("quantityInStock");
		String quantityInStock = new String(request.getParameter("quantityInStock").getBytes("ISO-8859-1"),"UTF-8");
		if (!validator.isQuantityInStockValid(quantityInStock)) {
			sendWrongFormFilling(request, response, quantityInStock);
			return null;
		}
		product.setQuantityInStock(Integer.valueOf(quantityInStock));

		// check description from "add product" form
//		String description = request.getParameter("description");
		String description = new String(request.getParameter("description").getBytes("ISO-8859-1"),"UTF-8");
		if (!validator.isDescriptionValid(description)) {
			sendWrongFormFilling(request, response, description);
			return null;
		}
		product.setDescription(description);

		if (logger.isDebugEnabled()) {
			logger.debug("Administrator correctly filled out the new product in add_product form."
					+ "UserName is \"" + product.getProductName() + "\"");
		}

		return product;
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void sendWrongFormFilling(HttpServletRequest request,
			HttpServletResponse response, String string) throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Form filling is not valid: \"" + string + "\"");
		}

		// prepare jsp for forwarding
		String jspPage = "administrator/administrator/add_product.jsp";
		String title = "Administrator - add product";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}


}
