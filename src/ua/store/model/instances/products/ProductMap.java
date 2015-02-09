/**
 *
 */
package ua.store.model.instances.products;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.ProductDAO;
import ua.store.model.instances.Cart;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class ProductMap implements MyLogger {

	private Map<Product, Integer> productMap;
	private Iterator<Entry<Product, Integer>> iterator;
	private Product product;

	public ProductMap() {
		if (logger.isDebugEnabled()) {
			logger.debug("ProductList - createProductList() - begin");
		}

		// get list of all products
		ProductDAO productDAO = DAOFactory.getProductDAO();

		this.productMap = productDAO.getAllProductsMap();
		this.iterator = this.productMap.entrySet().iterator();
	}

	public ProductMap(ProductType productType) {

		if (logger.isDebugEnabled()) {
			logger.debug("ProductList - createProductList(ProductType productType) - begin");
		}

		// get list of products by type
		ProductDAO productDAO = DAOFactory.getProductDAO();
		TreeMap<Product, Integer> productMap = productDAO.getProductsMapByType(productType);

		this.productMap = productMap;
		this.iterator = productMap.entrySet().iterator();
	}

	public void createProductList() {

		if (logger.isDebugEnabled()) {
			logger.debug("ProductList - createProductList() - begin");
		}

		// get list of all products
		ProductDAO productDAO = DAOFactory.getProductDAO();

		this.productMap = productDAO.getAllProductsMap();
		this.iterator = this.productMap.entrySet().iterator();
	}

	public void createProductMap(ProductType productType) {

		if (logger.isDebugEnabled()) {
			logger.debug("ProductMap - createProductMap(ProductType productType) - begin");
		}

		// get list of all products
		ProductDAO productDAO = DAOFactory.getProductDAO();
		TreeMap<Product, Integer> productMap = productDAO.getProductsMapByType(productType);

		if (productMap == null) {
			productMap = new TreeMap<>();
		}
		this.productMap = productMap;
		this.iterator = productMap.entrySet().iterator();
	}

	public ProductMap(Cart cart) {

		if (logger.isDebugEnabled()) {
			logger.debug("ProductMap - createProductMap(Cart cart) - begin");
		}

		// get list of products from cart
		Map<Product, Integer> productMap = cart.getProductMap();
		this.productMap = productMap;
		this.iterator = productMap.entrySet().iterator();

		this.productMap = cart.getProductMap();
		this.iterator = productMap.entrySet().iterator();

	}

	public void resetIterator() {
		this.iterator = productMap.entrySet().iterator();
	}

	public int getSize() {
		return this.productMap.size();
	}

	public String getProductId() {
		if (iterator.hasNext()) {
			Entry<Product, Integer> entry = iterator.next();
			this.product = entry.getKey();
			return String.valueOf(product.getProductId());
		} else {
			return null;
		}
	}

	public String getProductType() {
		if (product != null) {
			return this.product.getProductType().toString();
		} else {
			return null;
		}
	}

	public String getProductName() {
		if (product != null) {
			return this.product.getProductName();
		} else {
			return null;
		}
	}

	public String getPrice() {
		if (product != null) {
			return String.valueOf(this.product.getPrice());
		} else {
			return null;
		}
	}

	public String getQuantityInStock() {
		if (product != null) {
			return String.valueOf(this.product.getQuantityInStock());
		} else {
			return null;
		}
	}

	public String getDescription() {
		if (product != null) {
			return this.product.getDescription();
		} else {
			return null;
		}
	}

	/**
	 * @return the userList
	 */
	public Map<Product, Integer> getProductMap() {
		return productMap;
	}

	public String toString() {
		String str = "";
		for (Entry<Product, Integer> entry : this.productMap.entrySet()) {
			Product product = entry.getKey();
			str += product.getProductName() + " ";
		}

		return str;
	}

}
