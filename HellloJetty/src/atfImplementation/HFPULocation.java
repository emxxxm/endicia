package atfImplementation;

public class HFPULocation {
	//Input Destination ZIP Code, Mail Class
	// if the mail is Standard Mail or Periodicals
		//EXCEPTION; STOP-CALCULATION NOT POSSIBLE
	
	//else 
		//if Destination ZIP Code 9-Digits{
			//[DataAccess] if the 9-Digit Destination ZIP Code is NOT in ATF_ADDRESS_CLOSE
			//EXCEPTION; STOP-CALCULATION NOT POSSIBLE}
	
	
		//else{ 
			//[DataAccess]Select a 9-Digit Destination ZIP Code in ATF_ADDRESS_CLOSE}
	
		//if mail class is PME{
			//[DataAccess]Lookup PME HFPU Indicator from ATF_ADDRESS_CLOSE
			//Set HFPU Indicator = PME HFPU Indicator}
		
		//else{
			//[DataAccess]Lookup Non-PME HFPU Indiciator from ATF_ADDRESS_CLOSE
			//set HFPU Indicator = NonPME HFPU Indicator}
		
		//if HFPU Indicator exists{
			//[DataAccess]Lookup HFPU Address from ATF_ADDRESS_CLOSE
			//return HFPU Address;}
		//else
			//Exception; STOP-CALCULATION NOT POSSIBLE
	
		
	
}
