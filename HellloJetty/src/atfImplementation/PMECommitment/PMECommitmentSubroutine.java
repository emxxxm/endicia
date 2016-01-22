package atfImplementation.PMECommitment;

import java.util.ArrayList;
import java.util.HashMap;

import atfImplementation.Commitment;

public class PMECommitmentSubroutine {
	
	public PMECommitmentSubroutine(HashMap<String, String> queryTuples) {
		//[DataAccess] Lookup Origin ZIP in [ATF_PME_ORIGIN_SCHEDULE]
		//save all Records
		
		//[DataAccess] Lookup 5-Digit and 3-Digit Dest ZIP for chosen Dest Type in 
		//[ATF_PME_DEST_SCHEDULE; Save all Records
			
		//While(Origin Records Remaining) {
			//Select 1 Origin Record to create a Commitment with
			//While(Destination Records Remaining) {
				//Select 1 Destination Record to create a Commitment with
				//if(Origin FAC_ID = Dest Fac_ID) {
					//run subroutine Intra-Facility Commitment
					//Discard selected Destination Record
					//continue
				//}else {
					//[DataAccess] Lookup Dispatch Records with the ORIGIN's FAC_ID and the Destination's FAC_IF from ATF_PME_DISP_SCHEDULE;
					//Save all records
				//}
				//while(dispatches remaining) {
					//Select 1 Dispatch Record to create a commitment with
					//Run subroutine Calculate Extra-Facility Commitment
					//Discard selected Dispatch Record
				//}
				//Discard Selected Destination Record
				//continue
			//}
			//Discard selected Origin Record
			//Replenish all discarded Destination Records
		//}
		
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
		//else //Any commitments exist
			//return to Main ATF Main login (I believe in our case is PME Commitment Main)
	}
	
	public ArrayList<Commitment> getCommitments() {
		//TODO
		return new ArrayList<Commitment>();
	}

}
