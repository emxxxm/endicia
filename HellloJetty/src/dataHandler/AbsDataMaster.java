package dataHandler;

import java.text.ParseException;

import dataHandler.dataFiles.APOFPODPO;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.COT_ALL;
import dataHandler.dataFiles.OriginScheduleAll;
import dataHandler.dataFiles.PMEDestSchedule;
import dataHandler.dataFiles.PMEDispSchedule;
import dataHandler.dataFiles.RefValue;
import dataHandler.dataFiles.RulesObject;
import dataHandler.dataFiles.ServiceStandardAll;

public abstract class AbsDataMaster implements IDataMaster {
	APOFPODPO APOFPODPO;
	AddressClose ac;
	PMEDestSchedule PMEDest;
	PMEDispSchedule PMEDisp;
	RefValue refVal;
	RulesObject rules;
	COT_ALL cot;
	ServiceStandardAll ssd;
	OriginScheduleAll orgScheduleAll = new OriginScheduleAll();
	
	public AbsDataMaster() throws ParseException {
		APOFPODPO = new APOFPODPO();
		ac = new AddressClose();
		PMEDest = new PMEDestSchedule();
		PMEDisp = new PMEDispSchedule();
		refVal = new RefValue();
		rules = new RulesObject();
		cot = new COT_ALL();
		ssd = new ServiceStandardAll();
		
	}
	public ServiceStandardAll getServiceStandardAll(){
		return ssd;
	}
	public RulesObject getRulesObject() {
		return rules;
	}
	
	public APOFPODPO getAPOFPODPO() {
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
	
	public COT_ALL getCotAll(){
		return cot;
	}
	
	public OriginScheduleAll getOriginScheduleAll(){
		return orgScheduleAll;
	}
	
}
