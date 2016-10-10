/**
 * 
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This class handles giving a change of 10 cents
 * @author sivasankarisubramaniam
 *
 */
public class CurrencyTenCentsChangeGiverHandler implements ChangeGiverHandler {
	
	private ChangeGiverHandler nextChainHandler;

	/**
	 * 
	 */
	public CurrencyTenCentsChangeGiverHandler() {
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
		if(change >= 10) {
			int num = change / 10;
			int remainder = change % 10;
			System.out.println("Change given ---> " + num + " 10 cents");
			txCtrl.getMainController().getMachineryController().giveChange(2,num);
			txCtrl.getCustomerPanel().setChange(remainder);
			if(remainder !=0) {
				this.nextChainHandler.giveChange(remainder, txCtrl);
			}
		} else {
			this.nextChainHandler.giveChange(change, txCtrl);
		}
	}
}