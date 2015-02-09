/**
 *
 */
package ua.store.model.instances.orders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ua.store.model.instances.Cart;
import ua.store.model.instances.products.Product;
import ua.store.model.instances.users.User;

/**
 * @author Sergey
 *
 */
public class Order {
	private int orderId;
	private int userId;
	private double amount;
	private String orderDate;
	private OrderStatus orderStatus;
	private String comments;
	private List<OrderProduct> orderProducts;

	public Order() {
		orderProducts = new LinkedList<>();
	}

	public Order(User user, Cart cart) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.orderDate = sdf.format(new Date());
		this.userId = user.getUserId();
		this.amount = cart.getAmount();
		this.orderStatus = OrderStatus.WAITING_FOR_PAIMENT;
		orderProducts = getCartProductMap(cart);
//		this.cart = cart;
	}

	/**
	 * @param cart
	 * @return
	 */
	private List<OrderProduct> getCartProductMap(Cart cart) {
		List<OrderProduct> orderProducts = new LinkedList<>();
		for (Map.Entry<Product, Integer> entry : cart.getProductMap().entrySet()) {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setProductId(entry.getKey().getProductId());
			orderProduct.setPriceEach(entry.getKey().getPrice());
			orderProduct.setQuantityEach(entry.getValue());
			orderProducts.add(orderProduct);
		}
		return orderProducts;
	}

	public void addOrderProduct(Product product, int quantity) {
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setProductId(product.getProductId());
		orderProduct.setPriceEach(product.getPrice());
		orderProduct.setQuantityEach(quantity);
		orderProducts.add(orderProduct);
		amount += product.getPrice() * quantity;
	}

	public void removeProduct(Product product, int quantity) {
		OrderProduct orderProduct = null;
		Iterator<OrderProduct> iterator = orderProducts.iterator();
		while (iterator.hasNext()) {
			orderProduct = iterator.next();
			if (orderProduct.getProductId() == product.getProductId()) {
				int quantityEach = orderProduct.getQuantityEach();
				if (quantityEach == quantity) {
					iterator.remove();
				} else if (quantityEach > quantity) {
					quantityEach -= quantity;
					orderProduct.setQuantityEach(quantityEach);
				}
			}
		}
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the orderStatus
	 */
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the orderProducts
	 */
	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	/**
	 * @param orderProducts the orderProducts to set
	 */
	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

//	/**
//	 * @return the cart
//	 */
//	public Cart getCart() {
//		return cart;
//	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}





}
