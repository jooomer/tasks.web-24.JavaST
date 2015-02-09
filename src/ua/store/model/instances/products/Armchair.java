/**
 * 
 */
package ua.store.model.instances.products;

/**
 * @author Sergey
 *
 */
public class Armchair extends Product {
	
	private boolean transformer;
	
	public Armchair () {
		this.productType = ProductType.ARMCHAIR;
	}

	/**
	 * @return the transformer
	 */
	public boolean isTransformer() {
		return transformer;
	}

	/**
	 * @param transformer the transformer to set
	 */
	public void setTransformer(boolean transformer) {
		this.transformer = transformer;
	}


	
}
