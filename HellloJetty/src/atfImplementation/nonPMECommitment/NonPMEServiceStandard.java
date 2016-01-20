package atfImplementation.nonPMECommitment;

import java.util.HashMap;

import MainPackage.QueryStrings;

public class NonPMEServiceStandard {
	int transitTime;
	//Take in Origin ZIP Code, Destination ZIP Code
	public NonPMEServiceStandard(HashMap<String, String> queryTuples){
		String orignZIP = queryTuples.get(QueryStrings.ORIGIN_ZIP);
		String destZIP = queryTuples.get(QueryStrings.DEST_ZIP);
		//TODO [Optimization] Index 3-Digit
		
		
		
	}
	//[DataAccess]Lookup 5-Digit Origin and 5-Digit Destination in [ATF_NON_PME_SVC_STD]
	
	//if a Service Standard NOT found for the desired Mail Class{
		//[DataAccess]Lookup 3-Digit Origin and 5-Digit Destination in [ATF_NON_PME_SVC_STD]
		
		//if Service Standard NOT found for the desired Mail Class{
			//[DataAccess]Lookup 5-Digit Origin and 3-Digit Destination in [ATF_NON_PME_SVC_STD]
	
			//If Service Standard NOT found for the desired Mail Class{
				//[DataAccess] LoOkup 3-Digit Origin and 3-Digit Destination in [ATF_NON_PME_SVC_STD]
				
				//If Service Standard NOT found for the desired Mail Class{
					//Exception; STOP-CALCULATION NOT POSSIBLE}}}}
	//Set Transit Time = Value found for Mail Class
	
	//Return NON-PME Service Standard, Non-PME Transit Time; 
	
	public int getTransitTime(){
		return transitTime;
	}
			
				
}
