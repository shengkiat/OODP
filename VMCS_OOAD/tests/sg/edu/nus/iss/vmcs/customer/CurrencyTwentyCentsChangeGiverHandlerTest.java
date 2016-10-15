/**
 * 
 */
package sg.edu.nus.iss.vmcs.customer;

import static org.junit.Assert.*;

import java.awt.Frame;

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
public class CurrencyTwentyCentsChangeGiverHandlerTest {
	
	private CurrencyTwentyCentsChangeGiverHandler twentyCents;
	private MainController mainController;
	private CustomerPanel customerPanel;
	private String propertyFilename = "Vmcs.properties";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		twentyCents = new CurrencyTwentyCentsChangeGiverHandler();
		Environment.initialize(propertyFilename);
		mainController = new MainController(propertyFilename);
		mainController.initialize();
		mainController.getTransactionController().displayCustomerPanel();
		customerPanel = new CustomerPanel((Frame) mainController.getSimulatorControlPanel(), mainController.getTransactionController());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		twentyCents = null;
		mainController = null;
		customerPanel = null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.vmcs.customer.CurrencyTwentyCentsChangeGiverHandler#giveChange(int, sg.edu.nus.iss.vmcs.customer.TransactionController)}.
	 */
	@Test
	public void testGiveChange() {
		System.out.println("testGiveChangeZeroRemainder");
		int remainder = 0;
		try {
			twentyCents.giveChange(20, mainController.getTransactionController());
			
			assertEquals(remainder, Integer.parseInt(customerPanel.getChange()));
		} catch (VMCSException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.vmcs.customer.CurrencyTwentyCentsChangeGiverHandler#giveChange(int, sg.edu.nus.iss.vmcs.customer.TransactionController)}.
	 */
	@Test
	public void testGiveChangeWithRemainder() {
		System.out.println("testGiveChangeWithRemainder");
		int remainder = 0;
		try {
			twentyCents.setNextChain(new CurrencyTenCentsChangeGiverHandler());
			twentyCents.giveChange(30, mainController.getTransactionController());
			
			assertEquals(remainder, Integer.parseInt(customerPanel.getChange()));
			assertTrue(twentyCents.getNextChainHandler() instanceof CurrencyTenCentsChangeGiverHandler);
		} catch (VMCSException e) {
			e.printStackTrace();
		}
	}
}
