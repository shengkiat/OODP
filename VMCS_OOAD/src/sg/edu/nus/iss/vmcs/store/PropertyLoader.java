/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.io.*;

/**
 * This interface provides the generic functionality required to initialize the stores.
 * 
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public abstract class PropertyLoader {
	
	private String environmentStr = null;
	private PropertyLoaderImpl propertyLoaderImpl = null;
	
	public PropertyLoader(String environmentStr) {
		
		this.environmentStr = environmentStr;
	}
	
	public void setLoaderType(LoaderType loaderType) throws IOException {
		
		switch (loaderType){
		
			case FILE_LOADER:
				setPropertyLoaderImpl(new FilePropertyLoaderImpl(environmentStr));
				break;
				
			case DB_LOADER:
				setPropertyLoaderImpl(new DBPropertyLoaderImpl(environmentStr));
				break;
		}
	}
	
	//The Bridge to the Implementation hierarchy
	
//	public PropertyLoaderImpl getPropertyLoaderImpl();
	
	private void setPropertyLoaderImpl(PropertyLoaderImpl propertyLoaderImpl){
		this.propertyLoaderImpl = propertyLoaderImpl;
	}
	
	/**
	 * This method reads the properties file into a hash table.
	 * @throws IOException if fail to read properties from properties file.
	 */
//	public abstract void initialize() throws IOException;
	
	/**
	 * This method writes the properties from the hash table to the file.
	 * @throws IOException if fail to save properties to properties file.
	 */
	public void saveProperty() throws IOException{
		this.propertyLoaderImpl.saveProperty();
	}

	/**
	 * This method returns the number of items (either CashStoreItem or DrinkStoreItem)
	 * stored in the hash table.
	 * @return the number of items.
	 */
	public int getNumOfItems(){
		return this.propertyLoaderImpl.getNumOfItems();
	}

	/**
	 * This method sets the number of items (either CashStoreItem or DrinkStoreItem)
	 * stored in the hash table.
	 * @param numItems the number of items.
	 */
	public void setNumOfItems(int numItems){
		this.propertyLoaderImpl.setNumOfItems(numItems);;
	}

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
	
	
	public String getValue(String key){
		return propertyLoaderImpl.getValue(key);
	}

	public void setValue(String key, String value){
		propertyLoaderImpl.setValue(key, value);
	}
}//End of interface PropertyLoader
