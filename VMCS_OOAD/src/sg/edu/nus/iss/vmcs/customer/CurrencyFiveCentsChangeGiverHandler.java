/**
 * 
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This class handles giving a change of 5 cents
 * @author sivasankarisubramaniam
 *
 */
public class CurrencyFiveCentsChangeGiverHandler implements ChangeGiverHandler {
	
	private ChangeGiverHandler nextChainHandler;

	/**
	 * 
	 */
	public CurrencyFiveCentsChangeGiverHandler() {
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
		int remainder = 0;
		if(change >= 5) {
			int num = change / 5;
			remainder = change % 5;
			System.out.println("Change given ---> " + num + " 5 cents");
			txCtrl.getMainController().getMachineryController().giveChange(0,num);
			txCtrl.getCustomerPanel().setChange(remainder);
			if(remainder !=0 && this.nextChainHandler != null) {
				this.nextChainHandler.giveChange(remainder, txCtrl);
			} else {
				System.out.println("remainder ---> " + remainder);
				txCtrl.getCustomerPanel().setChange(remainder);
			}
		} else {
			txCtrl.getCustomerPanel().setChange(remainder);
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