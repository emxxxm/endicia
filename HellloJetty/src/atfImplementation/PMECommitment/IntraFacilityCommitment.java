package atfImplementation.PMECommitment;

import java.util.HashMap;

import MainPackage.QueryStrings;

public class IntraFacilityCommitment {

	public IntraFacilityCommitment(HashMap<String, String> queryTuples) {
	//while(true){
		int daysElapsed=0; 
		String transitDate = queryTuples.get(QueryStrings.EAD), commitmentDate = queryTuples.get(QueryStrings.EAD);
		//while(true){
			//if(transitDate == any USPS Holiday in ATF_REF_VAL_T){
				//DOW = 8
			//}
			//else DOW = Commitment Date DOW
			
			//if(NOT Origin DOW COT exist){
				//if(daysElapsed > 0){
					//Exception Commitment cannot be calculated
				//}
				
				//daysElapsed++;
				//transitDate++;
				//dropOffTime=-1;
			//}
			//else break;
		//}
		
		//if(dropOffTime <= Origin DOW COT){
			//if(Origin CLRNC_TIME < 0830){
				//transitDate++;
				//daysElapsed
			//}
			//if(NOT Origin CLRNC_TIME <=CET){
				//transitDate++;
				//daysElapsed++;
			//}
			//if(NOT CET <=DELIVERY_TIME){
				//transitDate++;
				//daysElapsed++;
			//}
		
			//if(daysElapsed > 2){
				//Exception Commitment cannot be calculated;
			//}
		
			//Service Standard = Days Elapsed;
			//Delivery Time = DELIVERY_TIME from Destination Record;
			//Preferred Indicator = 0;
			
			//if(EAD == any USPS Holiday in ATF_REF_VAL_T){
				//DOW = 8
			//}
			//else DOW = DOW of EAD
		
			//COT = DOW COT from Origin Record
			//Calculate COmmitment Rank by multiplying the RANK_IND from the 
				//Origin Record by the RANK_IND from the Destination Record
		
			//The Service Standard, Delivery Time, COT, Preferred Indicator, and COmmitment Rank
				//make up the COmmitment; Add this COmmitment to the Commitment List
			//return;
		//}
		
		//else{
			//if(NOT Commitment Date == EAD){
				//throw calculation now possible exception
			//}
			//Transit Date ++;
			//CommitmentDate++;
			//Drop Off Time = -1;
		
			//if(NOT Commitment Date DOW a Sunday){
				//if(Commitment Date = any USPS Holiday in ATF_REF_VAL_T){
					//DOW = 8
				//}
				//else continue;
			//}
			//if( the commitment Date DOW Close Time == 0){
				//Transit Date++;
				//Commitment Date ++;
			//}
		//}
	//}
	}
		

}
