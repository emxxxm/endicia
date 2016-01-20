package atfImplementation;

import java.util.HashMap;

import MainPackage.QueryStrings;
import dataHandler.DataMaster;
import dataHandler.dataFiles.AddressClose;

public abstract class AbsATFImplementation implements IATFImplementation {
	
	String EAD;
	HashMap<String, String> queryTuples;
	
	public AbsATFImplementation(HashMap<String, String> q) {
		queryTuples = q;
		EAD = queryTuples.get(QueryStrings.DATE);
		queryTuples.put(QueryStrings.EAD, EAD);
	}

	public String lookUpClose(String originZip) {
		AddressClose closeLookup = DataMaster.getInstance().getAddressClose();
		return closeLookup.getCloseTime(originZip, );
		
	}
	
}
