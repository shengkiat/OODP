package sg.edu.nus.iss.vmcs.store;

public enum LoaderType {
	FILE_LOADER(1), DB_LOADER(2);
	
	private int value; 
	
	private LoaderType(int value) { this.value = value; }
}
