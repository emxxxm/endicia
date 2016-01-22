package atfImplementation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import atfImplementation.nonPMECommitment.NonPMEDeliveryCalculation;
import atfImplementation.nonPMECommitment.NonPMEServiceStandard;
import atfImplementation.nonPMECommitment.PRI_COT;
import dataHandler.DataMaster;
import dataHandler.dataFiles.RulesObject;
import droolsRules.SDCKnowledgeDTO;

public class MainNonPMEImplementation extends AbsATFImplementation {
	SDCKnowledgeDTO droolsMsg = new SDCKnowledgeDTO();
	RulesObject rules = DataMaster.getInstance().getRulesObject();
	PRI_COT pri;
	NonPMEServiceStandard serviceStd;
	NonPMEDeliveryCalculation deliveryCalc;
	//Number of days
	int transitTime;
	String deliveryDate;
	
	public MainNonPMEImplementation(HashMap<String, String> q) throws CalculationNotPossibleException{
		super(q);
	}

	@Override
	public void execute() throws NumberFormatException, CalculationNotPossibleException, ParseException {
		String PRIOutput; 
		String cutoffTime;
		
		pri = new PRI_COT(queryTuples);
		
		if (queryTuples.get(QueryStrings.MAIL_CLASS).equals(QueryStrings.MAIL_CLASS_PRI)) {
			PRIOutput = pri.getPRI_COT();
			cutoffTime = PRIOutput; //superfluous but improves readability to user
		} else {
			cutoffTime = DataMaster.getInstance().getRefValue().getDefaultNonPMCOT(DateTimeUtilities.getDayOfWeek(queryTuples.get(QueryStrings.DATE)), 
					queryTuples.get(QueryStrings.MAIL_CLASS));
		}
	
		queryTuples.put(QueryStrings.CUTOFF_TIME, cutoffTime);
		
		commonIsDestinationHFPUBranch();
		
		executeAcceptanceRules();
		
		serviceStd = new NonPMEServiceStandard(queryTuples);
		transitTime = serviceStd.getTransitTime();
		
		executeTransitRules();
		
		deliveryDate = DateTimeUtilities.incrementDate(queryTuples.get(QueryStrings.EAD), transitTime);
		queryTuples.put(QueryStrings.DELIVERY_DATE, deliveryDate);
		
		deliveryDate = deliveryCalc.getDeliveryTime();
		queryTuples.put(QueryStrings.DELIVERY_DATE, deliveryDate);
		
		executeServiceStandardRules();	
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
