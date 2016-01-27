package atfImplementation.nonPMECommitment;

import java.util.HashMap;

import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.dataFiles.ServiceStandardAll;


public class NonPMEServiceStandard {
	//Number of days
	int transitTime;
	//Take in Origin ZIP Code, Destination ZIP Code
	public NonPMEServiceStandard(HashMap<String, String> queryTuples) throws NumberFormatException, CalculationNotPossibleException{
		String originZIP = queryTuples.get(QueryStrings.ORIGIN_ZIP);
		String destZIP = queryTuples.get(QueryStrings.DEST_ZIP);
		String mailClass = queryTuples.get(QueryStrings.MAIL_CLASS);
		transitTime = Integer.parseInt(getServiceStandard(originZIP, destZIP, mailClass));
		//TODO [Optimization] Index 3-Digit	
	}
	
	public int getTransitTime(){
		return transitTime;
	}
	
	public String getServiceStandard(String originZIP, String destZIP, String mailClass) throws CalculationNotPossibleException{
		ServiceStandardAll ssd = DataMaster.getInstance().getServiceStandardAll();
		return ssd.getServiceStandard(originZIP, destZIP, mailClass);
		
	}
			
				
}
