package atfImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.commons.csv.CSVRecord;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import dataHandler.DataMaster;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.RulesObject;
import droolsRules.SDCKnowledgeDTO;

public abstract class AbsATFImplementation implements IATFImplementation {
	
	String EAD, HFPUAddress;
	HashMap<String, String> queryTuples;
	HFPULocation HFPUloc = null; //TODO what does this do
	int originCloseTime; //TODO what does this do
	SDCKnowledgeDTO droolsMsg;
	LinkedHashMap<String, String> output = new LinkedHashMap<String, String>();
	HashMap<String, ArrayList<CSVRecord>> locationRecords;
	
	
	public AbsATFImplementation(HashMap<String, String> q) {
		queryTuples = q;
		EAD = queryTuples.get(QueryStrings.SHIP_DATE);
		queryTuples.put(QueryStrings.EAD, EAD);
	}

	private void lookUpClose(String originZip) throws NumberFormatException, CalculationNotPossibleException {
		originCloseTime = AddressClose.getCloseTimeOnDOWWrapper(DateTimeUtilities.getDayOfWeek(queryTuples.get(QueryStrings.SHIP_DATE)), originZip);
	}
	
	private void initLocationRecords() {
		locationRecords = DataMaster.getInstance().getAddressClose().getAddressRecords(queryTuples.get(QueryStrings.ORIGIN_ZIP),queryTuples.get(QueryStrings.DEST_ZIP));
	}
	
	private void resolveHFPU() throws CalculationNotPossibleException {
		initLocationRecords();
		ArrayList<CSVRecord> destRecords = locationRecords.get(QueryStrings.DEST_ZIP);
		if (QueryParser.isHFPU(queryTuples.get(QueryStrings.DEST_TYPE))) {
				HFPUloc = new HFPULocation(queryTuples, destRecords); 
				HFPUAddress = HFPUloc.getHFPULocation();
		}
	}
	
	public void commonIsDestinationHFPUBranch() throws CalculationNotPossibleException {
		resolveHFPU();
		lookUpClose(queryTuples.get(QueryStrings.ORIGIN_ZIP));
	}
	
    protected void executeAcceptanceRules() {
		droolsMsg = SDCKnowledgeDTO.initializeDroolsMsg(queryTuples, new SDCKnowledgeDTO()); //TODO test
		DataMaster.getInstance().getRulesObject().insertAndFire(droolsMsg, RulesObject.DROOLS_ACCEPTANCE);
    }
    
    protected void executeTransitRules() {
		droolsMsg = SDCKnowledgeDTO.initializeDroolsMsgForTransit(queryTuples); //TODO test
		System.out.println("Transit svc " + droolsMsg.svcStd);
		DataMaster.getInstance().getRulesObject().insertAndFire(droolsMsg, RulesObject.DROOLS_TRANSIT);
    }
    
	protected void executeServiceStandardRules() {
		droolsMsg = SDCKnowledgeDTO.initializeDroolsMsgForPost(queryTuples); //TODO test
		System.out.println("Post svc " + droolsMsg.svcStd);
		DataMaster.getInstance().getRulesObject().insertAndFire(droolsMsg, RulesObject.DROOLS_POSTPROCESSING);
	}
	
	public HashMap<String, String> getOutput() {
		formatOutput();
		return output;
	}
	
	public void formatOutput() {
		String svcStdMsg = droolsMsg.svcStdMsg;
		String guarantee = String.valueOf(droolsMsg.isGuarantee);
		
		CSVRecord destRecord = locationRecords.get(QueryStrings.DEST_ZIP).get(0), originRecord = locationRecords.get(QueryStrings.ORIGIN_ZIP).get(0);
		
		output.put(QueryStrings.DEST_CITY, AddressClose.getCity(destRecord));
		output.put(QueryStrings.DEST_STATE, AddressClose.getState(destRecord));
		output.put(QueryStrings.ORIGIN_CITY, AddressClose.getCity(originRecord));
		output.put(QueryStrings.ORIGIN_STATE, AddressClose.getState(originRecord));
		
		output.put(QueryStrings.DEST_TYPE, queryTuples.get(QueryStrings.DEST_TYPE));
		if (QueryParser.isHFPU(queryTuples.get(QueryStrings.DEST_TYPE))) {
			output.put(QueryStrings.DESTTYPE_HFPU, HFPUAddress);
		}
		
		
		output.put(QueryStrings.ORIGIN_ZIP, queryTuples.get(QueryStrings.ORIGIN_ZIP));
		output.put(QueryStrings.DEST_ZIP, queryTuples.get(QueryStrings.DEST_ZIP));
		output.put(QueryStrings.MAIL_CLASS, queryTuples.get(QueryStrings.MAIL_CLASS));
		output.put(QueryStrings.EAD, queryTuples.get(QueryStrings.EAD));
		output.put(QueryStrings.DELIVERY_DATE, queryTuples.get(QueryStrings.DELIVERY_DATE)); //SDD
		output.put(QueryStrings.SHIP_TIME, queryTuples.get(QueryStrings.SHIP_TIME)); //Accept Time and Ship Time
		output.put(RulesObject.SERVICE_STD_MSG, svcStdMsg);
		output.put(RulesObject.GUARANTEE, guarantee);
	}
	
}
 