package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.CashPropertyLoader;
import sg.edu.nus.iss.vmcs.system.DrinkPropertyLoader;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;

public class StoreControllerTest extends TestCase{
	private String propertyFilename=System.getProperty("propertyFilename");
	
	@Before
	public void setup() throws Exception{
		
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testStoreControllerConstructor() throws Exception{
		Environment.initialize(propertyFilename);
		CashPropertyLoader cashLoader =
			new CashPropertyLoader(Environment.getCashPropFile());
		DrinkPropertyLoader drinksLoader =
			new DrinkPropertyLoader(Environment.getDrinkPropFile());
		
		cashLoader.setLoaderType(LoaderType.FILE_LOADER);
		drinksLoader.setLoaderType(LoaderType.FILE_LOADER);
		
		cashLoader.initialize();
		drinksLoader.initialize();
		//Act
		StoreController cashStoreController=new CashStoreController(cashLoader);
		cashStoreController.initialize();
		//Assert
		assertNotNull(cashStoreController);
		assertNotNull(cashStoreController.getStore());
		

		StoreController drinkStoreController = new DrinkStoreController(drinksLoader);
		
		drinkStoreController.initialize();
		//Assert
		assertNotNull(drinkStoreController);
		assertNotNull(drinkStoreController.getStore());
		
	}
	
	@Test
	public void testInitialize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		
		//Act initialize
		cashStoreController.initialize();		
		CashStore cashStore=cashStoreController.getStore();
		
		//Assert
		assertNotNull(cashStore);


		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		//Act initialize
		cashStoreController.initialize();	
		DrinksStore drinksStore=drinkStoreController.getStore();

		assertNotNull(drinksStore);
	}
	

	@Test
	public void testInitializeStores() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		
		//Act initialize indirect Act initializeStores
		cashStoreController.initialize();
		
		//Get Cash Store and test its elements
		CashStore cashStore=cashStoreController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			//Assert
			assertNotNull(coin);
		}
		
		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		
		//Act initialize indirect Act initializeStores
		drinkStoreController.initialize();
		
		//Get Drinks Store and test its elements
		DrinksStore drinksStore=drinkStoreController.getStore();
		storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			//Assert
			assertNotNull(drinksBrand);
		}
	}

	@Test
	public void testInitializeDrinkStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		
		//Act initialize indirect Act initializeDrinkStore
		drinkStoreController.initialize();
		
		//Get Drinks Store and test its elements
		DrinksStore drinksStore=drinkStoreController.getStore();
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			//Assert
			assertNotNull(drinksBrand);
		}
	}

	@Test
	public void testInitializeCashStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		
		//Act initialize indirect Act initializeCashStore
		cashStoreController.initialize();
		
		//Get Cash Store and test its elements
		CashStore cashStore=cashStoreController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			assertNotNull(coin);
		}
	}

	@Test
	public void testStoreCoin() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		
		cashStoreController.initialize();
		
		CashStore cashStore=cashStoreController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin1=(Coin)cashStoreItem.getContent();
			//Act storeCoin
			cashStoreController.storeCoin(coin1);
			Coin coin2=cashStore.findCoin(coin1.getWeight());
			//Assert
			assertNotNull(coin2);
			assertSame(coin1,coin2);
		}
	}

	@Test
	public void testGetStoreSize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		
		//Initializing the Store
		cashStoreController.initialize();
		
		//Get Cash Store and test its elements
		CashStore cashStore=cashStoreController.getStore();
		//Act getStoreSize and test looping the store with the store size
		int storeSize=cashStoreController.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			//Assert
			assertNotNull(coin);
		}
		
		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		drinkStoreController.initialize();
		//Get Drinks Store and test its elements
		DrinksStore drinksStore=drinkStoreController.getStore();
		//Act getStoreSize and test looping the store with the store size
		storeSize=drinkStoreController.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			//Assert
			assertNotNull(drinksBrand);
		}
	}

	@Test
	public void testGetStoreItems() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		
		cashStoreController.initialize();
		
		//Act getStoreItems
		StoreItem[] cashStoreItems=cashStoreController.getStoreItems();
		for(int i=0;i<cashStoreItems.length;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStoreItems[i];
			//Assert
			assertNotNull(cashStoreItem);
		}
		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		drinkStoreController.initialize();
		//Act getStoreItems
		StoreItem[] drinksStoreItems=drinkStoreController.getStoreItems();
		for(int i=0;i<drinksStoreItems.length;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStoreItems[i];
			//Assert
			assertNotNull(drinksStoreItem);
		}
	}

	@Test
	public void testChangeStoreQty() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		cashStoreController.initialize();
		CashStore cashStore=cashStoreController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			int qty1=12+i;
			//Act changeStoreQty
			cashStoreController.changeStoreQty(i, qty1);
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		drinkStoreController.initialize();
		DrinksStore drinksStore=drinkStoreController.getStore();
		storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			int qty1=14+i;
			//Act changeStoreQty
			drinkStoreController.changeStoreQty(i, qty1);
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			int qty2=drinksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
	}

	@Test
	public void testGetStoreItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		cashStoreController.initialize();
		CashStore cashStore=cashStoreController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			//Act getStoreItem
			CashStoreItem cashStoreItem=(CashStoreItem)cashStoreController.getStoreItem(i);
			//Assert
			assertNotNull(cashStoreItem);
		}
		

		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		drinkStoreController.initialize();
		DrinksStore drinksStore=drinkStoreController.getStore();
		storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			//Act getStoreItem
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinkStoreController.getStoreItem(i);
			//Assert
			assertNotNull(drinksStoreItem);
		}
	}

	@Test
	public void testSetPrice() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		drinkStoreController.initialize();
		
	    DrinksStore drinksStore=drinkStoreController.getStore();
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			int price1=60+i;
			//Act setPrice
			drinkStoreController.setPrice(i, price1);
			DrinksStoreItem drinksStoreItem=((DrinksStoreItem)drinkStoreController.getStoreItem(i));
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			int price2=drinksBrand.getPrice();
			//Assert
			assertEquals(price1,price2);
		}
	}

	@Test
	public void testGetTotalCash() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		cashStoreController.initialize();
		CashStore cashStore=cashStoreController.getStore();
		//Act getTotalCash
		int totalCash=cashStoreController.getTotalCash();
		int countTotalCash=0;
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			countTotalCash+=(coin.getValue()*cashStoreItem.getQuantity());
		}
		//Assert
		assertEquals(totalCash,countTotalCash);
	}

	@Test
	public void testTransferAll() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		cashStoreController.initialize();
		CashStore cashStore=cashStoreController.getStore();
		//Act transferAll
		int numOfCashTransfered=cashStoreController.transferAll();
		//Assert
		if(numOfCashTransfered<0)
			fail();
		
		int totalCash=cashStoreController.getTotalCash();
		assertEquals(totalCash,0);
		
		int countTotalCash=0;
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			countTotalCash+=(coin.getValue()*cashStoreItem.getQuantity());
		}
		//Assert
		assertEquals(totalCash,countTotalCash);
	}

	@Test
	public void testCloseDown() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		cashStoreController.initialize();
		int cashStoreSize1=cashStoreController.getStoreSize();
		

		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		drinkStoreController.initialize();
		int drinksStoreSize1=drinkStoreController.getStoreSize();
		//Act closeDown
		cashStoreController.closeDown();
		
		
		cashStoreController.initialize();
		int cashStoreSize2=cashStoreController.getStoreSize();
		int drinksStoreSize2=drinkStoreController.getStoreSize();
		//Assert
		assertEquals(cashStoreSize1,cashStoreSize2);
		assertEquals(drinksStoreSize1,drinksStoreSize2);
	}

	@Test
	public void testDispenseDrink(int idx) throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		drinkStoreController.initialize();
		DrinksStore drinksStore=drinkStoreController.getStore();
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			int qty1=drinksStoreItem.getQuantity();
			if(qty1==0)
				continue;
			//Act dispenseDrink
			drinkStoreController.dispenseDrink(i);
			int qty2=drinksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2+1);
		}
	}

	@Test
	public void testGetStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		cashStoreController.initialize();
		//Act getStore
		CashStore cashStore=cashStoreController.getStore();
		
		DrinkStoreController drinkStoreController=mainCtrl.getDrinkStoreController();
		drinkStoreController.initialize();
		DrinksStore drinksStore=drinkStoreController.getStore();
		//Assert
		assertNotNull(cashStore);
		assertNotNull(drinksStore);
	}

	@Test
	public void testGiveChange() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		cashStoreController.initialize();
		CashStore cashStore=cashStoreController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			int qty1=cashStoreItem.getQuantity();
			if(qty1==0)
				continue;
			//Act give change
			cashStoreController.giveChange(i,1);
			cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2+1);
		}
	}
}//End of class StoreControllerTest