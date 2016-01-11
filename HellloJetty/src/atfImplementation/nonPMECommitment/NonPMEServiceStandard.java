package atfImplementation.nonPMECommitment;

public class NonPMEServiceStandard {
	//Take in Origin ZIP Code, Destination ZIP Code
	//[DataAccess]Lookup 5-Digit Origin and 5-Digit Destination in [ATF_NON_PME_SVC_STD]
	
	//if a Service Standard NOT found for the desired Mail Class{
		//[DataAccess]Lookup 3-Digit Origin and 5-Digit Destination in [ATF_NON_PME_SVC_STD]
		
		//if Service Standard NOT found for the desired Mail Class{
			//[DataAccess]Lookup 5-Digit Origin and 3-Digit Destination in [ATF_NON_PME_SVC_STD]
	
			//If Service Standard NOT found for the desired Mail Class{
				//[DataAccess] LoOkup 3-Digit Origin and 3-Digit Destination in [ATF_NON_PME_SVC_STD]
				
				//If Service Standard NOT found for the desired Mail Class{
					//Exception; STOP-CALCULATION NOT POSSIBLE}}}}
	//Set Transit Time = Value fond for Mail Class
	
	//Return NON-PME Service Standard, Non-PME Transit Time; 
			
				
}
