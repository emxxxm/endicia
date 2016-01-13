package atfImplementation.PMECommitment;

public class IntraFacilityCommitment {
	//daysElapsed=0; transitDate = EAD; commitmentDate = EAD
	//while(NOT daysElapsed > 0){
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
		

}
