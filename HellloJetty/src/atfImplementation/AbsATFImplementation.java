package atfImplementation;

import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import dataHandler.dataFiles.AddressClose;

public abstract class AbsATFImplementation implements IATFImplementation {
	
	String EAD, HFPUAddress;
	HashMap<String, String> queryTuples;
	HFPULocation HFPUloc =  new HFPULocation();
	int originCloseTime;
	
	public AbsATFImplementation(HashMap<String, String> q) {
		queryTuples = q;
		EAD = queryTuples.get(QueryStrings.DATE);
		queryTuples.put(QueryStrings.EAD, EAD);
	}

	private void lookUpClose(String originZip) {
		originCloseTime = AddressClose.getCloseTimeOnDOWWrapper(DateTimeUtilities.getDayOfWeek(queryTuples.get(QueryStrings.DATE)), originZip);
	}
	
	private void resolveHFPU() {
		if (QueryParser.isHFPU(queryTuples.get(QueryStrings.DEST_TYPE))) {
				HFPUAddress = HFPUloc.getHFPULocation();
		}
	}
	
	public void commonIsDestinationHFPUBranch() {
		lookUpClose(queryTuples.get(QueryStrings.ORIGIN_ZIP));
		resolveHFPU();
	}
	
}
