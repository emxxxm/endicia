package dataHandler;

import dataHandler.dataFiles.APOFPODPO;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.PMEDestSchedule;
import dataHandler.dataFiles.PMEDispSchedule;
import dataHandler.dataFiles.RefValue;
import dataHandler.dataFiles.RulesObject;

public interface IDataMaster {

	public APOFPODPO getAPOFPODPOData();
	public AddressClose getAddressClose();
	public PMEDestSchedule getPMEDest();
	public PMEDispSchedule getPMEDisp();
	public RefValue getRefValue();
	public RulesObject getRulesObject();
	
}
