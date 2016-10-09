package sg.edu.nus.iss.vmcs.customer;

public interface PaymentReceiver {

	public void startReceiver();
	public void receiveCash(double weight);
	public void continueReceive();
	public boolean storeCash();
	public void stopReceive();
	public void refundCash();
	public void resetReceived();
	public void setActive(boolean active);
	public void setTotalInserted(int totalInserted);
	public int getTotalInserted();
	
}
