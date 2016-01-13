package dataHandler;

import dataHandler.dataFiles.APOFPODPOData;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.PMEDestSchedule;
import dataHandler.dataFiles.PMEDispSchedule;
import dataHandler.dataFiles.RefValue;

public interface IDataMaster {

	public APOFPODPOData getAPOFPODPOData();
	public AddressClose getAddressClose();
	public PMEDestSchedule getPMEDest();
	public PMEDispSchedule getPMEDisp();
	public RefValue getRefValue();
	
}
