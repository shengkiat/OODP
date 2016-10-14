/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

/**
 * This control object manages changes in CashStore attributes and 
 * the DrinksStore attributes.
 *
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public abstract class StoreController {
	protected PropertyLoader loader;
	/**
	 * This constructor creates an instance of StoreController object.
	 * @param cashLoader the cash loader.
	 * @param drinksLoader the drinks loader.
	 */
	public StoreController(
			PropertyLoader loader
			) {
		this.loader = loader;
	}

	/**
	 * This method instantiate the {@link CashStore}, {@link DrinksStore} and initialize it.
	 * @throws IOException if fail to initialize stores; reading properties.
	 */
	public void initialize() throws IOException {
	//	cStore = new CashStore();
	//	dStore = new DrinksStore();
		initializeStores();
	}

	/**
	 * This method initiates the initialization of the {@link DrinkStore} and {@link CashStore}
	 * from data read-in from an input file.
	 * @return 
	 * @throws IOException if fail to initialize stores; reading properties.
	 */
	protected abstract void initializeStores() throws IOException ;


	/**
	 * This method return the total size of the {@link Store} of the given type of {@link Store}.
	 * @return the size of the store of the given type of Store.
	 */
	public int getStoreSize() {
			return getStore().getStoreSize();
	}

	/**
	 * This method returns an array of {@link StoreItem} of the given type of {@link Store}.
	 * @return an array of StoreItem.
	 */
	public StoreItem[] getStoreItems() {
			return getStore().getItems();
	}

	/**
	 * This method will either:
	 * <br>
	 * - instruct the {@link CashStore} to update the quantity of a {@link Coin} denomination to new
	 * value supplied and update the total cash held in the {@link CashStore}; or
	 * <br>
	 * - instruct the {@link DrinksStore} to update the drinks stock for a {@link DrinksBrand} required
	 * to a new value supplied.
	 * @param type the type of Store.
	 * @param idx the index of the StoreItem.
	 * @param qty the quantity of the StoreItem.
	 */
	public void changeStoreQty(int type, int idx, int qty) {
			System.out.println("StoreController.changeStoreQty: type:"+ type+ " qty:"+ qty);
			
			getStore().setQuantity(idx, qty);
			/*if (type == Store.CASH)
				cStore.setQuantity(idx, qty);
			else
				dStore.setQuantity(idx, qty);*/
	}

	/**
	 * This method returns the {@link StoreItem} with the given {@link Store} type and index of {@link StoreItem}.
	 * @param type the type of Store.
	 * @param idx the index of the StoreItem.
	 * @return the StoreItem.
	 */
	public StoreItem getStoreItem(int idx) {
		return getStore().getStoreItem(idx);
	}


	public abstract Store getStore();


	/**
	 * This method will close down the store management function of the vending machine.
	 * This involves saving the attributes of the stores to the property file.
	 * @throws IOException if fail to save cash properties and drinks properties.
	 */
	public void closeDown() throws IOException {
		// save back cash property;

        saveProperties();
	}
	protected abstract void saveProperties() throws IOException;


}//End of class StoreController