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
import ua.store.model.instances.products.ProductMap;
import ua.store.model.instances.products.ProductType;
import ua.store.model.pathexecutor.PathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class ArmchairsCommonPathExecutor implements PathExecutor, MyLogger {

	public void executePath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		// get list of all products
		ProductMap productMap = new ProductMap();
		productMap.createProductMap(ProductType.ARMCHAIR);

		// set list to a request
		session.setAttribute("productMap", productMap);
		session.setAttribute("productType", ProductType.ARMCHAIR);
		
		// prepare jsp for forwarding
		String jspPage = "common/products.jsp";
		String title = "Armchairs";
		String message = "";
		ControllerHelper
				.sendJspPage(request, response, jspPage, title, message);

	}
}
