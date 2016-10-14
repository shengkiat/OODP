package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

public abstract class PropertyLoaderImpl {
	
	/**
	 * This method reads the properties file into a hash table.
	 * @throws IOException if fail to read properties from properties file.
	 */
	public abstract void initialize() throws IOException;
	
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

	/**
	 * This method reads the data from the hash table and creates a StoreItem.
	 * @param index the index of the store item.
	 * @return StoreItem the store item of the given index.
	 */
	public abstract StoreItem getItem (int index);

	/**
	 * This method updates the hash table with the data from the StoreItem.
	 * @param index the index of the item.
	 * @param item the item to be saved.
	 */
	public abstract void setItem (int index, StoreItem item);
}
