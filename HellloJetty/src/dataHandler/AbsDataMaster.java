package dataHandler;

import dataHandler.dataFiles.APOFPODPOData;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.PMEDestSchedule;
import dataHandler.dataFiles.PMEDispSchedule;
import dataHandler.dataFiles.RefValue;
import dataHandler.dataFiles.RulesObject;

public abstract class AbsDataMaster implements IDataMaster {
	APOFPODPOData APOFPODPO;
	AddressClose ac;
	PMEDestSchedule PMEDest;
	PMEDispSchedule PMEDisp;
	RefValue refVal;
	RulesObject rules;
	
	public AbsDataMaster() {
		APOFPODPO = new APOFPODPOData();
		ac = new AddressClose();
		PMEDest = new PMEDestSchedule();
		PMEDisp = new PMEDispSchedule();
		refVal = new RefValue();
		rules = new RulesObject();
	}
	
	public RulesObject getRulesObject() {
		return rules;
	}
	
	public APOFPODPOData getAPOFPODPOData() {
		return APOFPODPO;
	}
	
	public AddressClose getAddressClose() {
		return ac;
	}
	
	public PMEDestSchedule getPMEDest() {
		return PMEDest;
	}
	
	public PMEDispSchedule getPMEDisp() {
		return PMEDisp;
	}
	
	public RefValue getRefValue() {
		return refVal;
	}
	
}
