package sg.edu.nus.iss.vmcs.store;

/**
 * New class for decorator pattern
 * 
 * @author benjaminng
 *
 */
public abstract class DrinksDecorator implements DrinksProduct {
	
	private DrinksProduct drinksProduct;
	
	public DrinksDecorator(DrinksProduct drinksProduct) {
		if (drinksProduct == null) {
			throw new IllegalArgumentException("drinksProduct cannot be null");
		}
		
		this.drinksProduct = drinksProduct;
	}

	@Override
	public int getPrice() {
		return drinksProduct.getPrice();
	}
	
	@Override
	public String getType() {
		return drinksProduct.getType();
	}

}
