package sg.edu.nus.iss.vmcs.machinery;

import java.awt.Frame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;

/**
 * @author Smita
 *
 */
public class MachineryControllerTest extends TestCase {
	private MachineryController sut;
	private MainController mainController;
	
	private static final String TEST_PROPERTY_FILE_NAME = "Vmcs.properties";
	private boolean displayCoinStockCalled = false;
	private boolean displayDrinksStockCalled = false;
	
	class MachinerySimulatorPanelSpy extends MachinerySimulatorPanel
	{

		public MachinerySimulatorPanelSpy(Frame fr, MachineryController machCtrl) {
			super(fr, machCtrl);
			// TODO Auto-generated constructor stub
		}

		private static final long serialVersionUID = 1L;
		
		@Override
		public StoreViewer getCashStoreDisplay() {
			displayCoinStockCalled = true;
			return super.getCashStoreDisplay();
		}
		
		@Override
		public StoreViewer getDrinksStoreDisplay() {
			displayDrinksStockCalled = true;
			return super.getDrinksStoreDisplay();
		}

	}

	@Before
	public void setUp() throws Exception {
		Environment.initialize(TEST_PROPERTY_FILE_NAME);
		mainController = new MainController(TEST_PROPERTY_FILE_NAME);
		mainController.initialize();
		sut = mainController.getMachineryController();
		sut.SetMachinerySimulatorPanel(new MachinerySimulatorPanelSpy(mainController.getSimulatorControlPanel(), sut));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		sut = null;
		mainController = null;
	}
	
	@Test
	public void testUpdateWhenCoinQuantityIncrement() throws Exception {
		System.out.println("testUpdateWhenCoinQuantityIncrement");
		
		StoreController cashStoreController=mainController.getCashStoreController();
		Store store=cashStoreController.getStore();
		StoreItem storeItem=(StoreItem)store.getStoreItem(2);
		storeItem.increment();

		assertTrue(displayCoinStockCalled);
	}
	
	@Test
	public void testUpdateWhenDrinksQuantityIncrement() throws Exception {
		System.out.println("testUpdateWhenDrinksQuantityIncrement");
		StoreController drinksStoreController=mainController.getDrinkStoreController();
		Store store = drinksStoreController.getStore();
		StoreItem storeItem=(StoreItem)store.getStoreItem(2);
		storeItem.increment();
		
		assertTrue(displayDrinksStockCalled);
	}
}
