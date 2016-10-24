package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

public interface PropertyLoaderImpl {
	
	/**
	 * This method writes the properties from the hash table to the file.
	 * @throws IOException if fail to save properties to properties file.
	 */
	public abstract void saveProperty() throws IOException; 

	/**
	 * This method returns the number of items (either CashStoreItem or DrinkStoreItem)
	 * stored in the hash table.
	 * @return the number of items.
	 */
	public abstract int getNumOfItems();

	/**
	 * This method sets the number of items (either CashStoreItem or DrinkStoreItem)
	 * stored in the hash table.
	 * @param numItems the number of items.
	 */
	public abstract void setNumOfItems(int numItems);

	public abstract String getValue(String key);

	public abstract void setValue(String key, String value);

}
