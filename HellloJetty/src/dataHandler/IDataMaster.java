package dataHandler;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import dataHandler.dataFiles.APOFPODPO;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.COT_ALL;
import dataHandler.dataFiles.OriginScheduleAll;
import dataHandler.dataFiles.PMEDestSchedule;
import dataHandler.dataFiles.PMEDispSchedule;
import dataHandler.dataFiles.RefValue;
import dataHandler.dataFiles.RulesObject;
import dataHandler.dataFiles.ServiceStandardAll;

public interface IDataMaster {

	public APOFPODPO getAPOFPODPO();
	public AddressClose getAddressClose();
	public PMEDestSchedule getPMEDest();
	public PMEDispSchedule getPMEDisp();
	public RefValue getRefValue();
	public RulesObject getRulesObject();
	public COT_ALL getCotAll();
	public ServiceStandardAll getServiceStandardAll();
	public OriginScheduleAll getOriginScheduleAll();
	
}
