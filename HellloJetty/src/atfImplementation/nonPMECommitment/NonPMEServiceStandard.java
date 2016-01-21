package atfImplementation.nonPMECommitment;

import java.util.HashMap;

import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.dataFiles.ServiceStandardAll;


public class NonPMEServiceStandard {
	int transitTime;
	//Take in Origin ZIP Code, Destination ZIP Code
	public NonPMEServiceStandard(HashMap<String, String> queryTuples){
		String originZIP = queryTuples.get(QueryStrings.ORIGIN_ZIP);
		String destZIP = queryTuples.get(QueryStrings.DEST_ZIP);
		String mailClass = queryTuples.get(QueryStrings.MAIL_CLASS);
		try {
			transitTime = Integer.parseInt(getServiceStandard(originZIP, destZIP, mailClass));
		} catch (NumberFormatException | CalculationNotPossibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
