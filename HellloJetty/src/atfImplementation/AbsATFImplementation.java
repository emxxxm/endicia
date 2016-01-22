package atfImplementation;

import java.util.ArrayList;
import java.util.HashMap;

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
	HFPULocation HFPUloc; //TODO what does this do
	int originCloseTime; //TODO what does this do
	SDCKnowledgeDTO droolsMsg;
	HashMap<String, String> output = new HashMap<String, String>();
	
	public AbsATFImplementation(HashMap<String, String> q) {
		queryTuples = q;
		EAD = queryTuples.get(QueryStrings.DATE);
		queryTuples.put(QueryStrings.EAD, EAD);
	}

	private void lookUpClose(String originZip) throws NumberFormatException, CalculationNotPossibleException {
		originCloseTime = AddressClose.getCloseTimeOnDOWWrapper(DateTimeUtilities.getDayOfWeek(queryTuples.get(QueryStrings.DATE)), originZip);
	}
	
	private void resolveHFPU() throws CalculationNotPossibleException {
		if (QueryParser.isHFPU(queryTuples.get(QueryStrings.DEST_TYPE))) {
				HFPUloc = new HFPULocation(queryTuples); 
				HFPUAddress = HFPUloc.getHFPULocation();
		}
	}
	
	public void commonIsDestinationHFPUBranch() throws CalculationNotPossibleException {
		resolveHFPU();
		lookUpClose(queryTuples.get(QueryStrings.ORIGIN_ZIP));
	}
	
    protected void executeAcceptanceRules() {
		droolsMsg = SDCKnowledgeDTO.initializeDroolsMsg(queryTuples); //TODO test
		DataMaster.getInstance().getRulesObject().insertAndFire(droolsMsg, RulesObject.DROOLS_ACCEPTANCE);
    }
    
    protected void executeTransitRules() {
		droolsMsg = SDCKnowledgeDTO.initializeDroolsMsg(queryTuples); //TODO test
		DataMaster.getInstance().getRulesObject().insertAndFire(droolsMsg, RulesObject.DROOLS_TRANSIT);
    }
    
	protected void executeServiceStandardRules() {
		droolsMsg = SDCKnowledgeDTO.initializeDroolsMsg(queryTuples); //TODO test
		DataMaster.getInstance().getRulesObject().insertAndFire(droolsMsg, RulesObject.DROOLS_POSTPROCESSING);
	}
	
	public HashMap<String, String> getOutput() {
		return output;
	}
	
}
 