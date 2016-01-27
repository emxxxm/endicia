package dataHandler;

public class DataMaster extends AbsDataMaster  {
	private static IDataMaster instance = null;

	private DataMaster() {	
		super();
	}
	
	public static synchronized IDataMaster getInstance() {
		instance = SingletonHelper.INSTANCE;
		return instance;
	}
	
	public static synchronized void swapData(IDataMaster newData) {
		instance = newData;
	}
	
	private static class SingletonHelper {
		private static final IDataMaster INSTANCE = new DataMaster();
	}
	
}
