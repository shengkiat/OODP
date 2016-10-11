package sg.edu.nus.iss.vmcs.store;

public class LessMilkDecorator extends DrinksDecorator {

	public LessMilkDecorator(DrinksProduct drinksProduct) {
		super(drinksProduct);
	}

	@Override
	public String getType() {
		return super.getType() + " " + "Less Milk";
	}
	
	@Override
	public int getPrice() {
		return super.getPrice() - 5;
	}

}
