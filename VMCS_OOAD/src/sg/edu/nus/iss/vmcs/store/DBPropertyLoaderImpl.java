package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

public class DBPropertyLoaderImpl implements PropertyLoaderImpl {
	
	private String jdbcUrl = null;
	
	
	public DBPropertyLoaderImpl(String jdbcUrl){
		this.jdbcUrl = jdbcUrl;
	}
	
	
	/**
	 * Initialize the DB connection
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		
	}
	
	@Override
	public void saveProperty() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumOfItems() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNumOfItems(int numItems) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(String key, String value) {
		// TODO Auto-generated method stub
		
	}

}
