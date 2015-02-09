/**
 * 
 */
package ua.store.model.instances.orders;

import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class OrderProduct implements MyLogger {
	private int id;
	private int orderId;
	private int productId;
	private int quantityEach;
	private double priceEach;
	
	private String productName;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * @return the quantityEach
	 */
	public int getQuantityEach() {
		return quantityEach;
	}
	
	/**
	 * @param quantityEach the quantityEach to set
	 */
	public void setQuantityEach(int quantityEach) {
		this.quantityEach = quantityEach;
	}
	
	/**
	 * @return the priceEach
	 */
	public double getPriceEach() {
		return priceEach;
	}
	
	/**
	 * @param d the priceEach to set
	 */
	public void setPriceEach(double priceEach) {
		this.priceEach = priceEach;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	

}
