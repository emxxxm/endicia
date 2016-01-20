package atfImplementation;

import java.util.ArrayList;
import java.util.HashMap;

import MainPackage.QueryStrings;
import atfImplementation.nonPMECommitment.PRI_COT;

public class MainNonPMEImplementation extends AbsATFImplementation {

	PRI_COT pri;
	
	public MainNonPMEImplementation(HashMap<String, String> q){
		super(q);
		pri = new PRI_COT(queryTuples);
	}

	@Override
	public void execute() {
		ArrayList<String> PRIOutput; 
		
		if (queryTuples.get(QueryStrings.MAIL_CLASS).equals(QueryStrings.MAIL_CLASS_PRI)) {
			PRIOutput = pri.getPRI_COT();
		} else {
			
		}
		
	}
	
	//TODO determine the class type 
	//if type is is PRI, getPRICOT()
	//else get_EAD_DOW_COT()

//if isDestHFPU, 
	//Get HFPULocation

//Lookup Origin Close Times from ATF_ADDRESS_CLOSE

//[DROOLS] Execute Rules Engine for Acceptance Rules

//Get NonPMEServiceStandard

//[DROOLS]Execute Rules Engine for Transit Time Rules

//DeliveryDate = EAD + Transit Time

//create subroutine calculateNonPMEDeliveryDate

}
