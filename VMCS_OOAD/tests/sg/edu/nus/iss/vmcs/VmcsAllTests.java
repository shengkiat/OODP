package sg.edu.nus.iss.vmcs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sg.edu.nus.iss.vmcs.customer.CurrencyFiftyCentsChangeGiverHandlerTest;
import sg.edu.nus.iss.vmcs.customer.CurrencyFiveCentsChangeGiverHandlerTest;
import sg.edu.nus.iss.vmcs.customer.CurrencyOneDollerChangeGiverHandlerTest;
import sg.edu.nus.iss.vmcs.customer.CurrencyTenCentsChangeGiverHandlerTest;
import sg.edu.nus.iss.vmcs.customer.CurrencyTwentyCentsChangeGiverHandlerTest;
import sg.edu.nus.iss.vmcs.machinery.MachineryControllerTest;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceControllerTest;
import sg.edu.nus.iss.vmcs.store.CashStoreItemTest;
import sg.edu.nus.iss.vmcs.store.CashStoreTest;
import sg.edu.nus.iss.vmcs.store.CoinTest;
import sg.edu.nus.iss.vmcs.store.DrinksBrandTest;
import sg.edu.nus.iss.vmcs.store.DrinksStoreItemTest;
import sg.edu.nus.iss.vmcs.store.DrinksStoreTest;
import sg.edu.nus.iss.vmcs.store.StoreControllerTest;
import sg.edu.nus.iss.vmcs.store.StoreItemTest;
import sg.edu.nus.iss.vmcs.store.StoreObjectTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	CurrencyFiftyCentsChangeGiverHandlerTest.class,
	CurrencyFiveCentsChangeGiverHandlerTest.class,
	CurrencyOneDollerChangeGiverHandlerTest.class,
	CurrencyTenCentsChangeGiverHandlerTest.class,
	CurrencyTwentyCentsChangeGiverHandlerTest.class,
	MachineryControllerTest.class,
	MaintenanceControllerTest.class,
	sg.edu.nus.iss.vmcs.store.AllTests.class
})
public class VmcsAllTests {

}
