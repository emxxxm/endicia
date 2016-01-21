package atfImplementation;

import java.util.ArrayList;
import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import atfImplementation.nonPMECommitment.PRI_COT;
import dataHandler.DataMaster;
import dataHandler.dataFiles.RulesObject;
import droolsRules.SDCKnowledgeDTO;

public class MainNonPMEImplementation extends AbsATFImplementation {
	SDCKnowledgeDTO droolsMsg = new SDCKnowledgeDTO();
	RulesObject rules = DataMaster.getInstance().getRulesObject();
	PRI_COT pri;
	
	public MainNonPMEImplementation(HashMap<String, String> q) throws CalculationNotPossibleException{
		super(q);
		pri = new PRI_COT(queryTuples);
	}

	@Override
	public void execute() {
		ArrayList<String> PRIOutput; 
		String cutoffTime;
		
		if (queryTuples.get(QueryStrings.MAIL_CLASS).equals(QueryStrings.MAIL_CLASS_PRI)) {
			PRIOutput = pri.getPRI_COT();
		} else {
			cutoffTime = DataMaster.getInstance().getRefValue().getDefaultNonPMCOT(DateTimeUtilities.getDayOfWeek(queryTuples.get(QueryStrings.DATE)), 
					queryTuples.get(QueryStrings.MAIL_CLASS));
		}
	
		commonIsDestinationHFPUBranch();
		
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
