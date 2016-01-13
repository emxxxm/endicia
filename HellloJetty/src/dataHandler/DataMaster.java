package dataHandler;

import dataHandler.dataFiles.APOFPODPOData;
import dataHandler.dataFiles.AbsDataMaster;

public class DataMaster extends AbsDataMaster implements IDataMaster {
	private static IDataMaster instance = null;
	APOFPODPOData d;

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
