package atfImplementation.PMECommitment;

import java.util.LinkedHashMap;

public class PMEDeliveryDate {
	//take in initially set in Main Flow, return update Delivery Date
	public PMEDeliveryDate(LinkedHashMap<String,String> queryTuples){
		while(true){
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
					
					//if(Close Time = 0){
						//Set Delivery Date = Delivery Date + 1
						//continue;
					//}

					//if Retrograde ZIP is set 
						//Origin ZIP = Retrograde ZIP
					
					//else if(Prograde ZIP set)
						//Destination ZIP = Prograde ZIP
					 
					//return;
				//} 
			
				//else{
					//if(DOW==Saturday or Sunday){
						//if(User select "No Delivery" on this DOW){
							//Delivery Date = Delivery Date + 1
							//continue;
						//}
					//}
				
					//else if(Delivery Date is a holiday){
						//if(User select "No Delivery" on Holidays){
							//Set Delivery date = Delivery Date + 1
							//continue;
							//}
						//else DOW = 8;
					//}
			
					//if(Destination is PO Box){
						//Lookup DOW Close Time from ATF_ADDRESS_CLOSE
						//if(Close Time = 0){
							//Set Delivery Date = Delivery Date + 1
							//continue;
						//}

						//if Retrograde ZIP is set 
							//Origin ZIP = Retrograde ZIP
						
						//else if(Prograde ZIP set)
							//Destination ZIP = Prograde ZIP
						
						//return;

					//}
				//}
			//}
		}
		
		
	}
	
	
	
	

	
	
			
}
