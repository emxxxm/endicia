package atfImplementation;

import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import dataHandler.dataFiles.AddressClose;

public abstract class AbsATFImplementation implements IATFImplementation {
	
	String EAD;
	HashMap<String, String> queryTuples;
	
	public AbsATFImplementation(HashMap<String, String> q) {
		queryTuples = q;
		EAD = queryTuples.get(QueryStrings.DATE);
		queryTuples.put(QueryStrings.EAD, EAD);
	}

	public int lookUpClose(String originZip) {
		return AddressClose.getCloseTimeOnDOWWrapper(DateTimeUtilities.getDayOfWeek(queryTuples.get(QueryStrings.DATE)), originZip);
	}
	
	public void resolveHFPU() {
		//TODO
	}
	
}
