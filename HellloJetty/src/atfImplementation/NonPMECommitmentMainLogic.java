package atfImplementation;
import java.util.LinkedHashMap;
public class NonPMECommitmentMainLogic {
	public NonPMECommitmentMainLogic(LinkedHashMap<String, String> queryTuples){
		
		//TODO determin the class type 
			//if type is is PRI, create and call getPRICOT(ZIP, EAD);
			//else get EAD_DOW_COT for Mail Class from ATF_REF_VALUE
		
		//if isDestHFPU, 
			//create subroutine getHFPULocation
		
		//Lookup Origin Close Times from ATF_ADDRESS_CLOSE
		
		//[DROOLS] Execute Rules Engine for Acceptance Rules
		
		//create subroutine getNonPMEServiceStandard
		
		//[DROOLS]Execute Rules Engine for Transit Time Rules
		
		//DeliveryDate = EAD + Transit Time
		
		//create subroutine calculateNonPMEDeliveryDate
	}
}
