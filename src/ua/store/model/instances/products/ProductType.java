/**
 * 
 */
package ua.store.model.instances.products;

/**
 * @author Sergey
 *
 */
public enum ProductType {
	CABINET, SOFA, ARMCHAIR, TABLE;
	
	public static ProductType getProductType(String type) {
		for (ProductType productType : ProductType.values()) {
			if (productType.toString().equals(type)) {
				return productType;
			}
		}
		throw new RuntimeException("Unknown type");
	}
	
}
