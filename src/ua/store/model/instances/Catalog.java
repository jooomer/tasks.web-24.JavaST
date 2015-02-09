/**
 *
 */
package ua.store.model.instances;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.ProductDAO;
import ua.store.model.instances.products.Product;


/**
 * @author Sergey
 *
 */
public class Catalog {

	private Map<Integer, Product> products = new TreeMap<>(); // <productId, Product>
	private static Catalog instance = new Catalog();

	private Catalog() {
		createCatalogue();
	}

	/**
	 *
	 */
	private void createCatalogue() {
		ProductDAO productDAO = DAOFactory.getProductDAO();
		Set<Product> productsList = productDAO.getAllProducts();
		Map<Integer, Product> products = new TreeMap<>();
		for (Product product : productsList) {
			products.put(product.getProductId(), product);
		}
		this.products = products;
	}

	public static Catalog getInstance() {
		return instance;
	}


	public void addProductToCatalogue(Product product) {

	}

	public void updateProductInCatalogue(Product product) {

	}

	public void removeProductFromCatalogue(Product product) {
		products.remove(product);
	}

	/**
	 * @return the products
	 */
	public Map<Integer, Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Map<Integer, Product> products) {
		this.products = products;
	}

	/**
	 * @param productId
	 * @return
	 */
	public Product getProductById(int productId) {
		return products.get(productId);
	}
}
