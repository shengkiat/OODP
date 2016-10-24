package sg.edu.nus.iss.vmcs.store;

public class DecoratorPatternDemo {

	public static void main(String[] args) {
		DrinksBrand coffee = new DrinksBrand("Coffee", 60);
		System.out.println(coffee.getName() + ": " + coffee.getPrice());
		
		DrinksProduct coffeeWithLessSugar = new LessSugarDecorator(coffee);
		System.out.println(coffeeWithLessSugar.getName() + ": " + coffeeWithLessSugar.getPrice());
		
		DrinksProduct coffeeWithLessSugarLessMilk = new LessMilkDecorator(coffeeWithLessSugar);
		System.out.println(coffeeWithLessSugarLessMilk.getName() + ": " + coffeeWithLessSugarLessMilk.getPrice());
		
	}

}
