/**
 *
 */
package ua.store.model.pathexecutor;

import javax.servlet.http.HttpServletRequest;

import ua.store.model.pathexecutor.admin.AddProductAdminPathExecutor;
import ua.store.model.pathexecutor.admin.CatalogAdminPathExecutor;
import ua.store.model.pathexecutor.admin.EditUserAdminPathExecutor;
import ua.store.model.pathexecutor.admin.ProductAdminPathExecutor;
import ua.store.model.pathexecutor.admin.ProductsAdminPathExecutor;
import ua.store.model.pathexecutor.admin.RootAdminPathExecutor;
import ua.store.model.pathexecutor.admin.SearchAdminPathExecutor;
import ua.store.model.pathexecutor.admin.UsersAdminPathExecutor;
import ua.store.model.pathexecutor.common.AccountCommonPathExecutor;
import ua.store.model.pathexecutor.common.ArmchairsCommonPathExecutor;
import ua.store.model.pathexecutor.common.CabinetsCommonPathExecutor;
import ua.store.model.pathexecutor.common.CartCommonPathExecutor;
import ua.store.model.pathexecutor.common.InitializationCommonPathExecutor;
import ua.store.model.pathexecutor.common.LoginCommonPathExecutor;
import ua.store.model.pathexecutor.common.OrderCommonPathExecutor;
import ua.store.model.pathexecutor.common.OrdersCommonPathExecutor;
import ua.store.model.pathexecutor.common.RootCommonPathExecutor;
import ua.store.model.pathexecutor.common.SofasCommonPathExecutor;
import ua.store.model.pathexecutor.common.TablesCommonPathExecutor;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class PathExecutorFactory implements MyLogger {

	public static PathExecutor getPathExecutor(HttpServletRequest request) {

		String url = request.getServletPath();

		if (logger.isDebugEnabled()) {
			logger.debug("Execute path is \"" + url + "\"");
		}

		return switchPathExecutor(url);

	}

	/**
	 * @param url
	 * @return
	 */
	private static PathExecutor switchPathExecutor(String url) {
		switch (url) {

		// common executors
		case "":
			return new RootCommonPathExecutor();
		case "/initialization":
			return new InitializationCommonPathExecutor();
		case "/login":
			return new LoginCommonPathExecutor();
		case "/account":
			return new AccountCommonPathExecutor();
		case "/cart":
			return new CartCommonPathExecutor();
		case "/order":
			return new OrderCommonPathExecutor();
		case "/orders":
			return new OrdersCommonPathExecutor();
		case "/product":
			return new LoginCommonPathExecutor();
		case "/cabinets":
			return new CabinetsCommonPathExecutor();
		case "/sofas":
			return new SofasCommonPathExecutor();
		case "/armchairs":
			return new ArmchairsCommonPathExecutor();
		case "/tables":
			return new TablesCommonPathExecutor();

		// admin executors
		case "/administrator":
			return new RootAdminPathExecutor();
		case "/administrator/catalogue":
			return new CatalogAdminPathExecutor();
		case "/administrator/search":
			return new SearchAdminPathExecutor();
		case "/administrator/users":
			return new UsersAdminPathExecutor();
		case "/administrator/edit-user":
			return new EditUserAdminPathExecutor();
		case "/administrator/add-product":
			return new AddProductAdminPathExecutor();
		case "/administrator/product":
			return new ProductAdminPathExecutor();
		case "/administrator/products":
			return new ProductsAdminPathExecutor();
		default:
			break;
		}
		return null;

	}

}
