/**
 * 
 */
package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.util.VMCSException;

/**
 * This interface contain methods for setting the next Handler
 * And giving the correct change for the respective domination
 * @author sivasankarisubramaniam
 *
 */
public interface ChangeGiverHandler {

	/**
	 * @param nextChain The next handler for calculating the number of coins and the corresponding dominations for returning change
	 */
	void setNextChain(ChangeGiverHandler nextChain);
	
	/**
	 * This method will calculate the number of 1 dollar, 50, 20, 10, and 5 cents to be returned to customer
	 * @param change The change that needs to be returned to customer
	 * @param txCtrl The TransactionColler to set the number of coins their dominations
	 * @throws VMCSException If txCtrl fails to update cash store display on the amount to return
	 */
	void giveChange(int change, TransactionController txCtrl) throws VMCSException;

}
