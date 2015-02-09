/**
 * 
 */
package ua.store.model.instances.products;

import java.util.Iterator;
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
public class ProductList implements MyLogger {

	private TreeSet<Product> productList;
	private Iterator<Product> iterator;
	private Product product;
	
	public ProductList() {
		if (logger.isDebugEnabled()) {
			logger.debug("ProductList - createProductList() - begin");
		}

		// get list of all products
		ProductDAO productDAO = DAOFactory.getProductDAO();

		this.productList = productDAO.getAllProducts();
		this.iterator = this.productList.iterator();
	}
	
	public ProductList(ProductType productType) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("ProductList - createProductList(ProductType productType) - begin");
		}

		// get list of all products
		ProductDAO productDAO = DAOFactory.getProductDAO();
		TreeSet<Product> productList = productDAO.getProductsByType(productType);

		this.productList = productList;
		this.iterator = productList.iterator();
	}
	
	public void createProductList() {
		
		if (logger.isDebugEnabled()) {
			logger.debug("ProductList - createProductList() - begin");
		}

		// get list of all products
		ProductDAO productDAO = DAOFactory.getProductDAO();

		this.productList = productDAO.getAllProducts();
		this.iterator = this.productList.iterator();
	}
	
	public void createProductList(ProductType productType) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("ProductList - createProductList(ProductType productType) - begin");
		}

		// get list of all products
		ProductDAO productDAO = DAOFactory.getProductDAO();
		TreeSet<Product> productList = productDAO.getProductsByType(productType);

		this.productList = productList;
		this.iterator = productList.iterator();
	}
	
//	public ProductList(Cart cart) {
//		
//		if (logger.isDebugEnabled()) {
//			logger.debug("ProductList - createProductList(Cart cart) - begin");
//		}
//
//		// get list of products from cart
//		TreeSet<Product> productList = cart.getProductList();
//		this.productList = productList;
//		this.iterator = productList.iterator();
//
//	}
		
	public void resetIterator() {
		this.iterator = productList.iterator();
	}

	public int getSize() {
		return this.productList.size();
	}

	public String getProductId() {
		if (iterator.hasNext()) {
			this.product = iterator.next();
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
	public TreeSet<Product> getProductList() {
		return productList;
	}
	
	public String toString() {
		String str = "";
		for (Product product : this.productList) {
			str += product.getProductName() + " ";
		}
		
		return str;
	}

}
