/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.system;



import java.io.IOException;

import sg.edu.nus.iss.vmcs.store.*;

/**
 * This control object manages the initialization of the CashStore&#46;
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class CashPropertyLoader extends PropertyLoader {
	
	private String environmentStr = null;
	
	private static final String NAME_LABEL     = "Name";
	private static final String WEIGHT_LABEL   = "Weight";
	private static final String VALUE_LABEL    = "Value";
	private static final String QUANTITY_LABEL = "Quantity";

	/**
	 * This constructor creates an instance of CashPropertyLoader object.
	 * @param filen the name of the cash property file.
	 */
	public CashPropertyLoader(String environmentStr) {
		super();
		
		this.environmentStr = environmentStr;
	}
	
	public void setLoaderType(LoaderType loaderType) {
		
		switch (loaderType){
		
			case FILE_LOADER:
				//cashPropFile = environmentStr
				setPropertyLoaderImpl(new FilePropertyLoaderImpl(environmentStr) {
					
					/**
					 * This method updates the hash table with the data from the CashStoreItem.
					 * @param index the index of the item.
					 * @param cashItem the cash store item.
					 */
					@Override
					public void setItem(int index, StoreItem cashItem) {
						int idx = index + 1;

						CashStoreItem item = (CashStoreItem) cashItem;
						Coin cn = (Coin) item.getContent();
						String itn = new String(NAME_LABEL + idx);

						setValue(itn, cn.getName());

						itn = new String(WEIGHT_LABEL + idx);
						setValue(itn, String.valueOf(cn.getWeight()));

						itn = new String(VALUE_LABEL + idx);
						setValue(itn, String.valueOf(cn.getValue()));

						itn = new String(QUANTITY_LABEL + idx);
						setValue(itn, String.valueOf(item.getQuantity()));
					}
					
					
					/**
					 * This method reads the data from the hash table and creates a CashStoreItem.
					 * @param index the index of the StoreItem.
					 * @return StoreItem the store item of the given index.
					 */
					@Override
					public StoreItem getItem(int index) {
						int idx = index + 1;
						Coin coin = new Coin();

						String name = new String(NAME_LABEL + idx);
						String value = getValue(name);
						coin.setName(value);

						name = new String(WEIGHT_LABEL + idx);
						value = getValue(name);
						coin.setWeight(Double.parseDouble(value));

						name = new String(VALUE_LABEL + idx);
						value = getValue(name);
						coin.setValue(Integer.parseInt(value));

						name = new String(QUANTITY_LABEL + idx);
						value = getValue(name);
						int qty = Integer.parseInt(value);

						CashStoreItem item = new CashStoreItem(coin, qty);
						return item;
					}
				});
				break;
				
			case DB_LOADER:
				setPropertyLoaderImpl(new DBPropertyLoaderImpl());
				break;
		}
	}

	@Override
	public void initialize() throws IOException {
		getPropertyLoaderImpl().initialize();
	}

	@Override
	public void saveProperty() throws IOException {
		getPropertyLoaderImpl().saveProperty();
		
	}

	@Override
	public int getNumOfItems() {
		return getPropertyLoaderImpl().getNumOfItems();
	}

	@Override
	public void setNumOfItems(int numItems) {
		 getPropertyLoaderImpl().setNumOfItems(numItems);
	}

	@Override
	public StoreItem getItem(int index) {
		return getPropertyLoaderImpl().getItem(index);
	}

	@Override
	public void setItem(int index, StoreItem item) {
		getPropertyLoaderImpl().setItem(index, item);
	}
}//End of class CashPropertyLoader