package sg.edu.nus.iss.vmcs.maintenance;

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
import sg.edu.nus.iss.vmcs.util.VMCSException;
/**
 * @author Smita
 *
 */
public class MaintenanceControllerTest extends TestCase {
	private MaintenanceController sut;
	private MainController mainController;
	
	private static final String TEST_PROPERTY_FILE_NAME = "Vmcs.properties";
	private boolean updateCashQtyDisplay = false;
	private boolean updateDrinksQtyDisplay = false;

	class MaintenancePanelSpy extends MaintenancePanel
	{
		private static final long serialVersionUID = 1L;

		public MaintenancePanelSpy(Frame fr, MaintenanceController mc) {
			super(fr, mc);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void updateQtyDisplay(int type, int idx, int qty) throws VMCSException
		{
			super.updateQtyDisplay(type, idx, qty);
			if(type == Store.CASH)
				updateCashQtyDisplay = true;
			else if(type == Store.DRINK)
				updateDrinksQtyDisplay = true;
		}

	}

	@Before
	public void setUp() throws Exception {
		Environment.initialize(TEST_PROPERTY_FILE_NAME);
		mainController = new MainController(TEST_PROPERTY_FILE_NAME);
		mainController.initialize();
		sut = mainController.getMaintenanceController();
		sut.SetMaintenancePanel(new MaintenancePanelSpy(mainController.getSimulatorControlPanel(), sut));
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
		StoreItem storeItem=(StoreItem)store.getStoreItem(1);
		storeItem.increment();

		assertTrue(updateCashQtyDisplay);
	}
	
	@Test
	public void testUpdateWhenDrinksQuantityIncrement() throws Exception {
		System.out.println("testUpdateWhenDrinksQuantityIncrement");
		StoreController drinksStoreController=mainController.getDrinkStoreController();
		Store store = drinksStoreController.getStore();
		StoreItem storeItem=(StoreItem)store.getStoreItem(2);
		storeItem.decrement();
		
		assertTrue(updateDrinksQtyDisplay);
	}

}
