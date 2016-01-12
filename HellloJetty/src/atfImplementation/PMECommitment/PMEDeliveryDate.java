package atfImplementation.PMECommitment;

public class PMEDeliveryDate {
	//[Drools] Execute Rules Engine for Delivery Date Rules
	
	//if(Delivery Date > 7 Days after the EAD){
		//Set Delivery Date = EAD + 7 Days
		//return;}
	
	//else{
		//Set DOW = Delivery date DOW
		//if(Destination == HFPU){
			//if(Delivery Date is a holiday)
				//Set DOW = 8
			//[DataAccess] Lookup Dow Close Time from [ATF_ADDRESS_CLOSE]
			//!!!TODO REDESGIN  if CLOSE TIME = 0 , Go back to line 4} 
		//else{
			//if(DOW==Saturday or Sunday)
				//if(User select "No Delivery" on this DOW){
					//Delivery Date = Delivery Date + 1
					//Go back to line 4!!!!!!}
			//if(Delivery Date is a holiday){
				//if(User select "No Delivery" on Holidays)
					//Set Delivery date = Delivery Date + 1}
			//else DOW = 8}
	
		//if Destination PO Box
			//Lookup DOW Close Time from ATF_ADDRESS_CLOSE
			//If CLose Time = 0
				//Set Delivery Date = Delivery Date + 1
				//GO back to line 4}
	//If Retrograde ZIP is set 
		//Orizin ZIP = Retrograde ZIP
	
	//else if(Prograde ZIP set)
		//Destination ZIP = Prograde ZIP
	
	//return;
	
	
			
}
