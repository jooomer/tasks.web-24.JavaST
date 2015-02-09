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
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;
import ua.store.projectservice.ValidationService;

/**
 * @author Sergey
 *
 */
public class SearchProductAdminCommand implements Command, MyLogger {

	/* (non-Javadoc)
	 * @see ua.shop.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("SearchCommand - execute() - begin");
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
		
		// get and check data from search form
		String productName = request.getParameter("productName");
		ValidationService validator = new ValidationService();
		if (!validator.isProductNameValid(productName)) {
			sendWrongFormFilling(request, response, productName);
			return;
		}		

		// check existence of product in DB
		ProductDAO productDAO = DAOFactory.getProductDAO();
		if (!productDAO.doesProductExist(productName)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Product \"" + productName + "\" is absent in DB.");
			}
			
			// prepare jsp for forwarding
			String jspPage = "administrator/all_products.jsp";
			String title = "Administrator - List of product";
			String message = "";
			ControllerHelper
					.sendJspPage(request, response, jspPage, title, message);

			return;
		}
		
		// get product from DB
		Product productSql = productDAO.getByProductName(productName);
				
		// if there was error go to error page
		if (productSql == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Product \"" + productName + "\" is not gotten. There was a problem in a DB connection.");
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
			logger.debug("Product \"" + productName + "\" is gotten successfully.");
		}
		
		// set product which is found to the request
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
	private void sendWrongFormFilling(HttpServletRequest request,
			HttpServletResponse response, String string) throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Form filling is not valid: \"" + string + "\"");
		}

		// prepare jsp for forwarding
		String jspPage = "administrator/all_products.jsp";
		String title = "Administrator - List of product";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}


}
