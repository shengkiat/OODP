/**
 * 
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This class handles giving a change of 50 cents
 * @author sivasankarisubramaniam
 *
 */
public class CurrencyFiftyCentsChangeGiverHandler implements ChangeGiverHandler {
	
	private ChangeGiverHandler nextChainHandler;

	/**
	 * 
	 */
	public CurrencyFiftyCentsChangeGiverHandler() {
		super();
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.vmcs.customer.ChangeGiverHandler#setNextChain(sg.edu.nus.iss.vmcs.customer.ChangeGiverHandler)
	 */
	@Override
	public void setNextChain(ChangeGiverHandler nextChain) {
		this.nextChainHandler = nextChain;
		
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.vmcs.customer.ChangeGiverHandler#giveChange(int, sg.edu.nus.iss.vmcs.customer.TransactionController)
	 */
	@Override
	public void giveChange(int change, TransactionController txCtrl) throws VMCSException {
		if(change >= 50) {
			int num = change / 50;
			int remainder = change % 50;
			System.out.println("Change given ---> " + num + " 50 cents");
			txCtrl.getMainController().getMachineryController().giveChange(3,num);
			txCtrl.getCustomerPanel().setChange(remainder);
			if(remainder !=0) {
				this.nextChainHandler.giveChange(remainder, txCtrl);
			}
		} else {
			this.nextChainHandler.giveChange(change, txCtrl);
		}
	}

	/**
	 * @return the nextChainHandler
	 */
	public ChangeGiverHandler getNextChainHandler() {
		return nextChainHandler;
	}

	/**
	 * @param nextChainHandler the nextChainHandler to set
	 */
	public void setNextChainHandler(ChangeGiverHandler nextChainHandler) {
		this.nextChainHandler = nextChainHandler;
	}
}