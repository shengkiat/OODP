/**
 * 
 */
package sg.edu.nus.iss.vmcs.customer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * @author sivasankarisubramaniam
 *
 */
public class CurrencyFiveCentsChangeGiverHandlerTest {
	
	private MainController mainController;
	private String propertyFilename = "Vmcs.properties";
	private CurrencyFiveCentsChangeGiverHandler fiveCents;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		fiveCents = new CurrencyFiveCentsChangeGiverHandler();
		Environment.initialize(propertyFilename);
		mainController = new MainController(propertyFilename);
		mainController.initialize();
		mainController.getTransactionController().displayCustomerPanel();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		fiveCents = null;
		mainController = null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.vmcs.customer.CurrencyFiveCentsChangeGiverHandler#giveChange(int, sg.edu.nus.iss.vmcs.customer.TransactionController)}.
	 */
	@Test
	public void testGiveChangeZeroRemainder() {
		System.out.println("testGiveChangeZeroRemainder");
		try {
			fiveCents.giveChange(5, mainController.getTransactionController());
			assertNull(fiveCents.getNextChainHandler());
			
		} catch (VMCSException e) {
			e.printStackTrace();
		}
	}
}
