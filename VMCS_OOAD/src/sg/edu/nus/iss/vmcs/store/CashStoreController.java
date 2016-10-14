package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

public class CashStoreController  extends StoreController{

	CashStore store;
	public CashStoreController(PropertyLoader drinksLoader) {
		super( drinksLoader);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method initialize the {@link CashStore}.
	 * @throws IOException if fail to initialize cash store; reading properties.
	 */
	protected void initializeStores() throws IOException {
		store = new CashStore();
		// get the cash file from the environment property file;
		int numOfItems = loader.getNumOfItems();
		store.setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
		    CashStoreItem item = (CashStoreItem) loader.getItem(i);
		    store.addItem(i, item);
		}
	}
	
	/**
	 * This method returns the total number of cash held in the {@link CashStore}.
	 * @return the total number of cash held.
	 */
	public int getTotalCash(){
		int i;
		int size;

		size = store.getStoreSize();
		CashStoreItem item;
		int qty;
		int val;
		int tc = 0;
		Coin c;

		for (i = 0; i < size; i++) {
			item = (CashStoreItem) store.getStoreItem(i);
			qty = item.getQuantity();
			c = (Coin) item.getContent();
			val = c.getValue();
			tc = tc + qty * val;
		}
		return tc;
	}

	/**
	 * This method instructs the {@link CashStore} to issue a number of {@link Coin} of a specific
	 * denomination, and then updates the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It return TRUE
	 * or FALSE to indicate whether the change issue was successful&#46;
	 * @param idx the index of the Coin&#46;
	 * @param numOfCoins the number of Coin to deduct&#46; 
	 */
	public void giveChange(int idx, int numOfCoins)  {
		CashStoreItem item;
		item = (CashStoreItem) getStoreItem(idx);
		for (int i = 0; i < numOfCoins; i++)
			item.decrement();
	}

	/**
	 * This method saves the attributes of the {@link CashStore} to the input file.
	 * @throws IOException if fail to save cash properties.
	 */
	protected void saveProperties() throws IOException {
		int size = store.getStoreSize();
		loader.setNumOfItems(size);
		for (int i = 0; i < size; i++) {
			loader.setItem(i, store.getStoreItem(i));
		}
		loader.saveProperty();
	}

	/**
	 * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and then
	 * update the display on the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}.
	 * @return the number of cash transfered.
	 */
	public int transferAll()  {
		int i;
		int cc = 0; // coin quauntity;
		int size = store.getStoreSize();

		CashStoreItem item;
		for (i = 0; i < size; i++) {
			item = (CashStoreItem) store.getStoreItem(i);
			cc = cc + item.getQuantity();
			item.setQuantity(0);
		}

		return cc;
	}


	/**
	 * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and
	 * update the display on the Machinery Simulator Panel.
	 * @param c the Coin to be stored.
	 */
	public void storeCoin(Coin c) {
		int idx = ((CashStore)store).findCashStoreIndex(c);
		CashStoreItem item;
		item = (CashStoreItem) store.getStoreItem(idx);
		item.increment();
	}
	@Override
	public CashStore getStore(){
		return store;
	}
	
}
