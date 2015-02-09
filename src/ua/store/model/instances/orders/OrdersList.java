/**
 *
 */
package ua.store.model.instances.orders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.OrderDAO;
import ua.store.model.instances.Catalog;
import ua.store.model.instances.products.Product;
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class OrdersList implements MyLogger {

	private List<Order> ordersList;
	private Iterator<Order> iterator;
	private Order order;
	private List<String> productNamesList;

	public OrdersList() {
		if (logger.isDebugEnabled()) {
			logger.debug(this.getClass().getSimpleName() + " - OrderList() - begin");
		}

		// get list of all orders
		OrderDAO orderDAO = DAOFactory.getOrderDAO();

//		this.ordersList = orderDAO.getAllOrders();
		this.iterator = this.ordersList.iterator();
	}

	public OrdersList(User user) {

		if (logger.isDebugEnabled()) {
			logger.debug(this.getClass().getSimpleName() + " - OrderList(User user) - begin");
		}

		// get list of user's orders
		this.ordersList = user.getOrders();

		this.iterator = ordersList.iterator();
	}

	public void resetIterator() {
		this.iterator = ordersList.iterator();
	}

	public int getSize() {
		return this.ordersList.size();
	}

	public String getOrderId() {
		if (iterator.hasNext()) {
			this.order = iterator.next();
			return String.valueOf(order.getOrderId());
		} else {
			return null;
		}
	}

	public Double getAmount() {
		if (order != null) {
			return this.order.getAmount();
		} else {
			return null;
		}
	}

	public String getOrderDate() {
		if (order != null) {
			return this.order.getOrderDate();
		} else {
			return null;
		}
	}

	public String getOrderStatus() {
		if (order != null) {
			return this.order.getOrderStatus().toString();
		} else {
			return null;
		}
	}

	public String getComments() {
		if (order != null) {
			return this.order.getComments();
		} else {
			return null;
		}
	}

	/**
	 * @return the userList
	 */
	public List<Order> getOrdersList() {
		return ordersList;
	}

	public String toString() {
		String str = "";
		for (Order order : this.ordersList) {
			str += order.getOrderId() + " ";
		}
		return str;
	}

	/**
	 * @return the productList
	 */
	public List<String> getProductNamesList() {
		List<String> list = new ArrayList<>();
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			Catalog catalogue = Catalog.getInstance();
			Product product = catalogue.getProductById(orderProduct.getProductId());
			list.add(product.getProductName());
		}
		return list;
	}

}
