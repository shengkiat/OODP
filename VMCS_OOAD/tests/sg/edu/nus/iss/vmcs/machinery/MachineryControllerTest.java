package sg.edu.nus.iss.vmcs.machinery;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;


public class MachineryControllerTest extends TestCase {
	private MachineryController sut;
	private MainController mainController;
	
	private static final String TEST_PROPERTY_FILE_NAME = "Vmcs.properties";
	private boolean updateCalled = false;
	
	class TestMachineryController extends MachineryController
	{
		public TestMachineryController(MainController mctrl) {
			super(mctrl);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void update(Observable storeItem, Object obj)
		{
			updateCalled = true;
			assertTrue(storeItem instanceof CashStoreItem);
		}
	}
	
	@Before
	public void setUp() throws Exception {
		Environment.initialize(TEST_PROPERTY_FILE_NAME);
		mainController = new MainController(TEST_PROPERTY_FILE_NAME);
		mainController.initialize();
		sut = new TestMachineryController(mainController);
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
		
		cashStoreController.initialize();
		Store store=cashStoreController.getStore();
		StoreItem storeItem=(StoreItem)store.getStoreItem(2);
		storeItem.increment();
		// verify machinerycontroller update is called
		Thread.sleep(5000);
		assertTrue(updateCalled);
	}
	
	@Test
	public void testUpdateWhenDrinksQuantityIncrement() throws Exception {
		System.out.println("testUpdateWhenDrinksQuantityIncrement");
		StoreController drinksStoreController=mainController.getDrinkStoreController();
		
		drinksStoreController.initialize();
		Store store = drinksStoreController.getStore();
		StoreItem storeItem=(StoreItem)store.getStoreItem(2);
		storeItem.increment();
		// verify machinerycontroller update is called
	}
}
