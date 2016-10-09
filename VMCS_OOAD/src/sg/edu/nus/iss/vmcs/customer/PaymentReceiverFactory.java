package sg.edu.nus.iss.vmcs.customer;

public class PaymentReceiverFactory {

	private static PaymentReceiverFactory instance = null;
	private static PaymentReceiver coinReceiver = null;
	
	 public static PaymentReceiverFactory getInstance() {
	      if(instance == null) {
	         instance = new PaymentReceiverFactory();
	      }
	      return instance;
	   }
	
	 protected PaymentReceiver getReceiver(String receiver, TransactionController txCtrl){
		 switch(receiver){
		 case "coin":
				if (coinReceiver == null){
					coinReceiver = new CoinReceiver(txCtrl);
				}
					return coinReceiver;
		 
		 }
		 
		return null;			 
	 }
}
