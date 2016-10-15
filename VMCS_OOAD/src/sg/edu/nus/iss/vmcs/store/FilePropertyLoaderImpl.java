package sg.edu.nus.iss.vmcs.store;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class FilePropertyLoaderImpl extends PropertyLoaderImpl {

	private static final String PROP_NUM_ITEMS = "NumOfItems";

	private Properties prop;
	private String fileName;
	
	/**
	 * This constructor creates an instance of the FilePropertyLoaderImpl object.
	 * @param fileName the filename of the property file.
	 */
	public FilePropertyLoaderImpl(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * This method reads the properties file into a hash table.
	 * @throws IOException if fail to load properties from properties file.
	 */
	@Override
	public void initialize() throws IOException {
		prop = new Properties(System.getProperties());
		FileInputStream stream = new FileInputStream(fileName);
		prop.load(stream);
		stream.close();

	}
	
	/**
	 * This method writes the properties from the hash table to the file.
	 * @throws IOException if fail to store properties to properties file.
	 */
	@Override
	public void saveProperty() throws IOException {
		FileOutputStream stream = new FileOutputStream(fileName);
		prop.store(stream, "");
		stream.close();

	}

	/**
	 * This method returns the number of items (either CashStoreItem or DrinkStoreItem)
	 * stored in the hash table.
	 * @return the number of items.
	 */
	public int getNumOfItems() {
		String nm = prop.getProperty(PROP_NUM_ITEMS);
		int nmi;
		nmi = Integer.parseInt(nm);
		return nmi;
	}

	/**
	 * This method sets the number of items (either CashStoreItem or DrinkStoreItem) stored in the hash table.
	 * @param vl the number of items.
	 */
	public void setNumOfItems(int vl) {
		prop.setProperty(PROP_NUM_ITEMS, String.valueOf(vl));
	}
	
	/**
	 * This method reads the data from the hash table and creates a StoreItem.
	 * @param index the index of the StoreItem.
	 * @return the store item of the given index.
	 */
	public abstract StoreItem getItem(int index);

	/**
	 * This method updates the hash table with data from the StoreItem.
	 * @param index the index of the StoreItem.
	 * @param item the StoreItem.
	 */
	public abstract void setItem(int index, StoreItem item);
 
	/**
	 * This method retrieve the value from the hash table.
	 * @param key the key.
	 * @return the value of the given key.
	 */
	public String getValue(String key) {
		return prop.getProperty(key);
	}

	/**
	 * The method sets a value into the hash table.
	 * @param key the key.
	 * @param value the value.
	 */
	public void setValue(String key, String value) {
		prop.setProperty(key, value);
	}

}