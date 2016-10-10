/**
 * 
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This class handles giving a change of 1 dollar
 * @author sivasankarisubramaniam
 *
 */
public class CurrencyOneDollerChangeGiverHandler implements ChangeGiverHandler {
	
	private ChangeGiverHandler nextChainHandler;

	/**
	 * 
	 */
	public CurrencyOneDollerChangeGiverHandler() {
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
		if(change >= 100) {
			int num = change / 100;
			int remainder = change % 100;
			System.out.println("Change given ---> " + num + " 100 cents");
			txCtrl.getMainController().getMachineryController().giveChange(5,num);
			txCtrl.getCustomerPanel().setChange(remainder);
			if(remainder !=0) {
				this.nextChainHandler.giveChange(remainder, txCtrl);
			}
		} else {
			this.nextChainHandler.giveChange(change, txCtrl);
		}
	}
}