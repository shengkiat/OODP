package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

public class DrinkStoreController extends StoreController{
	DrinksStore store;
	public DrinkStoreController(PropertyLoader cashLoader) {
		super(cashLoader);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method initialize the {@link DrinksStore}.
	 * @throws IOException if fail to initialize drinks store; reading properties.
	 */
	protected void initializeStores() throws IOException {
		store = new DrinksStore();
		// get the drink file from the environment property file;
		int numOfItems = loader.getNumOfItems();
		store.setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
            DrinksStoreItem item = (DrinksStoreItem) loader.getItem(i);
			StoreObject brand = item.getContent();
			StoreObject existingBrand = store.findObject(brand.getName());
			if (existingBrand != null) {
			    item.setContent(existingBrand);
			}
			store.addItem(i, item);
		}
	} 
	/**
	 * This method instructs the {@link DrinksStore} to dispense one drink, and then updates the 
	 * {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It returns TRUE or FALSE to indicate whether dispensing
	 * was successful&#46;
	 * @param idx the index of the drinks to be dispensed&#46;
	 */
	public void dispenseDrink(int idx)  {
		DrinksStoreItem item;
		item = (DrinksStoreItem) getStoreItem(idx);
		item.decrement();
	}

	/**
	 * This method saves the attributes of the {@link DrinksStore} to the input file.
	 * It saves the drink property when simulation is ended.
	 * @throws IOException if fail to save drinks properties.
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
	 * This method will sets the price for the {@link StoreItem} with the given index and price.
	 * @param idx the index of the StoreItem.
	 * @param pr the price of the StoreItem.
	 */
	public void setPrice(int idx, int pr)  {
		DrinksStoreItem item;

		item = (DrinksStoreItem) store.getStoreItem(idx);
		DrinksBrand bd;

		bd = (DrinksBrand) item.getContent();

		bd.setPrice(pr);
	}
	@Override
	public DrinksStore getStore(){
		return store;
	}
}
