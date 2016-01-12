package atfImplementation.PMECommitment;

public class PMECommitmentSubroutine {
	
	//[DataAccess] Lookup Origin ZIP in [ATF_PME_ORIGIN_SCHEDULE]
	//save all Records
	
	//[DataAccess] Lookup 5-Digit and 3-Digit Dest ZIP for chosen Dest Type in 
	//[ATF_PME_DEST_SCHEDULE; Save all Records
	
	//While(Origin Records remaining && NOT Destination Records remaining){
		//Select 1 Origin Record to create a COmmitment with
		
	//if(NOT Origin Records remaining){
		//if(NOT any commitments exists){
			//if(Drop Off Time <= DOW COT from AFT_COT){
				// Service Standard = 2;
				// Delivery Time = 1500;
				// Commitment Date = EAD;
				// Commitment Rank = 1;
				// Preferred Indicator = 0;}
			//else{
				//Service Standard = 2;
				//Delivery Time = 1500;
				//Commitment Date = EAD + 1;
				//cOMMITMENT rANK = 1;
				//Preferred Indicator = 0}}}
	
		
	

}
