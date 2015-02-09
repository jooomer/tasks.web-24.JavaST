/**
 * 
 */
package ua.store.model.instances.products;

/**
 * @author Sergey
 *
 */
public class Table extends Product {
	
	private String surfaceType;
	
	public Table () {
		this.productType = ProductType.TABLE;
	}

	/**
	 * @return the surfaceType
	 */
	public String getSurfaceType() {
		return surfaceType;
	}

	/**
	 * @param surfaceType the surfaceType to set
	 */
	public void setSurfaceType(String surfaceType) {
		this.surfaceType = surfaceType;
	}

	
}
