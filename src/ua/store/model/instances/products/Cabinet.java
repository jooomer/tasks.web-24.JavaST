/**
 * 
 */
package ua.store.model.instances.products;

/**
 * @author Sergey
 *
 */
public class Cabinet extends Product {
	
	private int internalVolume;
	
	public Cabinet () {
		this.productType = ProductType.CABINET;
	}

	/**
	 * @return the internalVolume
	 */
	public int getInternalVolume() {
		return internalVolume;
	}

	/**
	 * @param internalVolume the internalVolume to set
	 */
	public void setInternalVolume(int internalVolume) {
		this.internalVolume = internalVolume;
	}
	
	
}
