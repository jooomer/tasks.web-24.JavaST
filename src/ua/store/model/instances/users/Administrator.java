/**
 * 
 */
package ua.store.model.instances.users;

/**
 * @author Sergey
 *
 */
public class Administrator extends User {
	
	public Administrator() {
		userType = UserType.ADMIN;
	}
/*
	public void addProductToCatalogue(AbstractProduct product) {
		Store.getInstance().getCatalogue().addProductToCatalogue(product);
	}
	
	public void removeProductFromCatalogue(AbstractProduct product) {
		Store.getInstance().getCatalogue().removeProductFromCatalogue(product);
	}
	
	public void addClientToBlackList(Client client) {
		Store.getInstance().getBlackList().addToBlackList(client);
	}
	
	public void removeClientFromBlacList(Client client) {
		Store.getInstance().getBlackList().removeFromBlackList(client);
	}
*/
}
