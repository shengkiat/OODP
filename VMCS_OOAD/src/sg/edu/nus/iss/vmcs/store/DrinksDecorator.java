package sg.edu.nus.iss.vmcs.store;

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

}
