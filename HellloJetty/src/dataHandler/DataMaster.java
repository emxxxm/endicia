package dataHandler;

public class DataMaster extends AbsDataMaster  {
	private static IDataMaster instance = null;


	private DataMaster() {	
		super();
	}
	
	public static synchronized IDataMaster getInstance() {
		if (instance == null) {
			instance = new DataMaster();
		}
		return instance;
	}
	
	public static synchronized void swapData(IDataMaster newData) {
		instance = newData;
	}
	
}
