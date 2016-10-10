/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.store.CashStoreItem;
import sg.edu.nus.iss.vmcs.store.Store;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This control object manages the giving of change to the Customer.
 * @author Team SE16T5E
 * @version 1.0 2008-10-01
 */
public class ChangeGiver {
	private TransactionController txCtrl;
	private ChangeGiverHandler chainOfResponsibilityOne;

	/**
	 * The constructor creates an instance of the object.
	 * It will initialise the ChangeGiverHandler handlers
	 * @param txCtrl the TransactionController
	 */
	public ChangeGiver(TransactionController txCtrl){
		this.txCtrl=txCtrl;
		
		// initialize the chain
		this.chainOfResponsibilityOne = new CurrencyOneDollerChangeGiverHandler();
		ChangeGiverHandler chainOfResponsibilityTwo = new CurrencyFiftyCentsChangeGiverHandler();
		ChangeGiverHandler chainOfResponsibilityThree = new CurrencyTwentyCentsChangeGiverHandler();
		ChangeGiverHandler chainOfResponsibilityFour = new CurrencyTenCentsChangeGiverHandler();
		ChangeGiverHandler chainOfResponsibilityFive = new CurrencyFiveCentsChangeGiverHandler();

		// set the chain of responsibility
		chainOfResponsibilityOne.setNextChain(chainOfResponsibilityTwo);
		chainOfResponsibilityTwo.setNextChain(chainOfResponsibilityThree);
		chainOfResponsibilityThree.setNextChain(chainOfResponsibilityFour);
		chainOfResponsibilityFour.setNextChain(chainOfResponsibilityFive);
	}
	
	/**
	 * This method is used to reset the Refund/ Change Tray display on the
	 * Customer Panel.
	 */
	public void resetChange(){
		CustomerPanel custPanel=txCtrl.getCustomerPanel();
		if(custPanel!=null){
			custPanel.resetChange();
		}
	}
	
	/**
	 * This method manages the issuing of change to the Customer.
	 * @param changeRequired
	 * @return return TRUE if give change use case success, otherwise, return FALSE.
	 */
	public boolean giveChange(int changeRequired){
		if(changeRequired==0)
			return true;
		try{
			int changeBal=changeRequired;
			// start chain of responsibility for returning change for customer
			chainOfResponsibilityOne.giveChange(changeBal, txCtrl);
		}
		catch(VMCSException ex){
			txCtrl.terminateFault();
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used to display the appropriate message on the No Change
	 * Available Display depending on the current change availability.
	 */
	public void displayChangeStatus(){
		CustomerPanel custPanel=txCtrl.getCustomerPanel();
		if(custPanel==null)
			return;
		boolean isAnyDenoEmpty=false;
		MainController mainCtrl=txCtrl.getMainController();
		StoreController storeCtrl=mainCtrl.getStoreController();
		StoreItem[] cashStoreItems=storeCtrl.getStore(Store.CASH).getItems();
		for(int i=0;i<cashStoreItems.length;i++){
			StoreItem storeItem=cashStoreItems[i];
			CashStoreItem cashStoreItem=(CashStoreItem)storeItem;
			int quantity=cashStoreItem.getQuantity();
			if(quantity==0)
				isAnyDenoEmpty=true;
		}
		custPanel.displayChangeStatus(isAnyDenoEmpty);
	}
}//End of class ChangeGiver