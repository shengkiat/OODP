package sg.edu.nus.iss.vmcs.store;

public class LessSugarDecorator extends DrinksDecorator {

	public LessSugarDecorator(DrinksProduct drinksProduct) {
		super(drinksProduct);
	}

	@Override
	public String getType() {
		return super.getType() + " " + "Less Sugar";
	}
	
	@Override
	public int getPrice() {
		return super.getPrice() - 5;
	}

}
