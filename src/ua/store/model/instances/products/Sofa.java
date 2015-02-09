/**
 * 
 */
package ua.store.model.instances.products;

/**
 * @author Sergey
 *
 */
public class Sofa extends Product {
	
	private boolean doubleSize;

	public Sofa () {
		this.productType = ProductType.SOFA;
	}

	/**
	 * @return the doubleSize
	 */
	public boolean isDoubleSize() {
		return doubleSize;
	}

	/**
	 * @param doubleSize the doubleSize to set
	 */
	public void setDoubleSize(boolean doubleSize) {
		this.doubleSize = doubleSize;
	}
	
}
