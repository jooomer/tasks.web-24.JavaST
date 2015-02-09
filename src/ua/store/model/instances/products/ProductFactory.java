/**
 * 
 */
package ua.store.model.instances.products;

import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
public class ProductFactory implements MyLogger {

	// create product by string
	public static Product createProduct(String productType) {
		return createProduct(ProductType.getProductType(productType));
	}
		
	// create product by enum
	public static Product createProduct(ProductType productType) {

		if (logger.isDebugEnabled()) {
			logger.debug("ProductType is \"" + productType + "\"");
		}
		
		switch (productType) {
		case CABINET:
			return new Cabinet();
		case SOFA:
			return new Sofa();
		case ARMCHAIR:
			return new Armchair();
		case TABLE:
			return new Table();
		default:
			logger.debug("Cannot create a new product. Wrong productType");
		}
		return null;
	}
}
