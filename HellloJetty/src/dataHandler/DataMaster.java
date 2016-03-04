package dataHandler;

import java.text.ParseException;

public class DataMaster extends AbsDataMaster  {
	private static IDataMaster instance = null;

	private DataMaster() throws ParseException {	
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
		private static IDataMaster INSTANCE;
		static {
			try {
				INSTANCE = new DataMaster();
			} catch (Exception e) {
	            throw new ExceptionInInitializerError(e);
			}
		}
	}
	
}
