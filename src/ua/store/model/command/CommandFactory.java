/**
 *
 */
package ua.store.model.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ua.store.model.command.admin.AddProductAdminCommand;
import ua.store.model.command.admin.DeleteProductAdminCommand;
import ua.store.model.command.admin.DeleteUserAdminCommand;
import ua.store.model.command.admin.SearchProductAdminCommand;
import ua.store.model.command.admin.SearchUserAdminCommand;
import ua.store.model.command.admin.UpdateEditUserAdminCommand;
import ua.store.model.command.admin.UpdateProductAdminCommand;
import ua.store.model.command.common.CheckoutCommonCommand;
import ua.store.model.command.common.ClearCartCommonCommand;
import ua.store.model.command.common.InitializeDbCommonCommand;
import ua.store.model.command.common.LanguageCommonCommand;
import ua.store.model.command.common.LoginCommonCommand;
import ua.store.model.command.common.LogoutCommonCommand;
import ua.store.model.command.common.MakeOrderCommonCommand;
import ua.store.model.command.common.RegisterCommonCommand;
import ua.store.model.command.common.SaveOrderCommonCommand;
import ua.store.model.command.common.SearchProductCommonCommand;
import ua.store.model.command.common.SendToCartCommonCommand;
import ua.store.model.command.common.UpdateUserCommonCommand;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class CommandFactory implements MyLogger {

	private static Map<String, Command> commands = new HashMap<>();
	static {
		// commands from common
		commands.put("initialize_DB", new InitializeDbCommonCommand());
		commands.put("login", new LoginCommonCommand());
		commands.put("logout", new LogoutCommonCommand());
		commands.put("register", new RegisterCommonCommand());
		commands.put("update_user", new UpdateUserCommonCommand());
		commands.put("send_to_cart", new SendToCartCommonCommand());
		commands.put("clear_cart", new ClearCartCommonCommand());
		commands.put("save_order", new SaveOrderCommonCommand());
		commands.put("make_an_order", new MakeOrderCommonCommand());
		commands.put("checkout", new CheckoutCommonCommand());
		commands.put("language", new LanguageCommonCommand());

		// commands from admin
		commands.put("search_user_by_userName", new SearchUserAdminCommand());
		commands.put("update_edit_user", new UpdateEditUserAdminCommand());
		commands.put("add_product", new AddProductAdminCommand());
		commands.put("update_product", new UpdateProductAdminCommand());
		commands.put("search_product_by_productName", new SearchProductAdminCommand());
		commands.put("common_search_product_by_productName", new SearchProductCommonCommand());
		commands.put("delete_user", new DeleteUserAdminCommand());
		commands.put("delete_product", new DeleteProductAdminCommand());
		commands.put("bad", new BadCommand());
	}

	public static Command createCommand(HttpServletRequest request) {

		String commandName = request.getParameter("command");
		String languageCommandName = request.getParameter("language");

		logger.debug("The command is \"" + commandName + "\"");

		if (commandName == null) {
			commandName = languageCommandName;
		}

		return switchCommand(commandName);

	}

	/**
	 * @param commandName
	 * @return
	 */
	private static Command switchCommand(String commandName) {

		switch(commandName) {
		// commands from common
		case "initialize_DB" :
			return commands.get("initialize_DB");
		case "login" :
			return commands.get("login");
		case "logout" :
			return commands.get("logout");
		case "register" :
			return commands.get("register");
		case "update_user" :
			return commands.get("update_user");
		case "send_to_cart" :
			return commands.get("send_to_cart");
		case "clear_cart" :
			return commands.get("clear_cart");
		case "save_order" :
			return commands.get("save_order");
		case "make_an_order" :
			return commands.get("make_an_order");
		case "checkout" :
			return commands.get("checkout");
		case "en" :
		case "ru" :
			return commands.get("language");

		// commands from admin
		case "search_user_by_userName" :
			return commands.get("search_user_by_userName");
		case "update_edit_user" :
			return commands.get("update_edit_user");
		case "add_product" :
			return commands.get("add_product");
		case "update_product" :
			return commands.get("update_product");
		case "search_product_by_productName" :
			return commands.get("search_product_by_productName");
		case "common_search_product_by_productName" :
			return commands.get("common_search_product_by_productName");
		case "delete_user" :
			return commands.get("delete_user");
		case "delete_product" :
			return commands.get("delete_product");
		default :
			return commands.get("bad");
		}

	}

}
