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
public class CurrencyTenCentsChangeGiverHandlerTest {
	
	private CurrencyTenCentsChangeGiverHandler tenCents;
	private MainController mainController;
	private CustomerPanel customerPanel;
	private String propertyFilename = "Vmcs.properties";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tenCents = new CurrencyTenCentsChangeGiverHandler();
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
		tenCents = null;
		mainController = null;
		customerPanel = null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.vmcs.customer.CurrencyTenCentsChangeGiverHandler#giveChange(int, sg.edu.nus.iss.vmcs.customer.TransactionController)}.
	 */
	@Test
	public void testGiveChangeZeroRemainder() {
		System.out.println("testGiveChangeZeroRemainder");
		int remainder = 0;
		try {
			tenCents.giveChange(10, mainController.getTransactionController());
			
			assertEquals(remainder, Integer.parseInt(customerPanel.getChange()));
		} catch (VMCSException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.vmcs.customer.CurrencyTenCentsChangeGiverHandler#giveChange(int, sg.edu.nus.iss.vmcs.customer.TransactionController)}.
	 */
	@Test
	public void testGiveChangeWithRemainder() {
		System.out.println("testGiveChangeWithRemainder");
		int remainder = 0;
		try {
			tenCents.setNextChain(new CurrencyFiveCentsChangeGiverHandler());
			tenCents.giveChange(15, mainController.getTransactionController());
			
			assertEquals(remainder, Integer.parseInt(customerPanel.getChange()));
			assertTrue(tenCents.getNextChainHandler() instanceof CurrencyFiveCentsChangeGiverHandler);
		} catch (VMCSException e) {
			e.printStackTrace();
		}
	}
}
