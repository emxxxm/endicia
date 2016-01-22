package atfImplementation;

import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import dataHandler.dataFiles.AddressClose;

public abstract class AbsATFImplementation implements IATFImplementation {
	
	String EAD, HFPUAddress;
	HashMap<String, String> queryTuples;
	HFPULocation HFPUloc ;
	int originCloseTime;
	
	public AbsATFImplementation(HashMap<String, String> q) throws CalculationNotPossibleException {
		HFPUloc = new HFPULocation(queryTuples);
		queryTuples = q;
		EAD = queryTuples.get(QueryStrings.DATE);
		queryTuples.put(QueryStrings.EAD, EAD);
	}

	private void lookUpClose(String originZip) throws NumberFormatException, CalculationNotPossibleException {
		originCloseTime = AddressClose.getCloseTimeOnDOWWrapper(DateTimeUtilities.getDayOfWeek(queryTuples.get(QueryStrings.DATE)), originZip);
	}
	
	private void resolveHFPU() {
		if (QueryParser.isHFPU(queryTuples.get(QueryStrings.DEST_TYPE))) {
				HFPUAddress = HFPUloc.getHFPULocation();
		}
	}
	
	public void commonIsDestinationHFPUBranch() throws NumberFormatException, CalculationNotPossibleException {
		lookUpClose(queryTuples.get(QueryStrings.ORIGIN_ZIP));
		resolveHFPU();
	}
	
    protected void executeAcceptanceRules() {
    	//TODO
    }
    
    protected void executeTransitRules() {
    	//TODO
    }
    
	protected void executeServiceStandardRules() {
		//TODO
	}
	
}
 