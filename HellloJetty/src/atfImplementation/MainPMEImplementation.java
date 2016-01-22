package atfImplementation;

import java.text.ParseException;
import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import atfImplementation.PMECommitment.APOFPODPOSubroutine;
import atfImplementation.PMECommitment.BestPMECommitment;
import atfImplementation.PMECommitment.PMECommitmentSubroutine;
import atfImplementation.PMECommitment.PMEDeliveryDate;

public class MainPMEImplementation extends AbsATFImplementation {
	APOFPODPOSubroutine afd;
	PMECommitmentSubroutine PMECommitment;
	BestPMECommitment bestCommitmentCalc;
	PMEDeliveryDate deliveryCalc;
	int retrogradeOffset;
	int progradeOffset;
	//Number of days
	int transitTime;
	String deliveryDate;
	
	public MainPMEImplementation(HashMap<String, String> q) {
		super(q);
	}

	@Override
	public void execute() throws CalculationNotPossibleException, ParseException {
		afd = new APOFPODPOSubroutine(queryTuples);
		retrogradeOffset = afd.getRetrogradeOffset();
		progradeOffset = afd.getProgradeOffset();
		
		commonIsDestinationHFPUBranch();

		PMECommitment = new PMECommitmentSubroutine(queryTuples);
	    bestCommitmentCalc = new BestPMECommitment(PMECommitment.getCommitments());
	    
	    transitTime = bestCommitmentCalc.getTransitTime();
	    
	    executeAcceptanceRules();
	    executeTransitRules();
	    
	    deliveryDate = DateTimeUtilities.incrementDate(queryTuples.get(QueryStrings.EAD), transitTime + retrogradeOffset + progradeOffset);
		queryTuples.put(QueryStrings.DELIVERY_DATE, deliveryDate);
		
		deliveryCalc = new PMEDeliveryDate(queryTuples);
		
		executeServiceStandardRules();
	}
	
	//TODO create and run get APO/FPO/DPO data subroutine
	
	//if (destination is of type HFPU) 
		//TODO create and run get HFPU Location subroutine
	//else
		//TODO lookup origin close times from ATF_Address_close
	
	//TODO create and run Calculate PME Commitments subroutine
	
	//TODO create and run Get Best PME Commitment subroutine
	
	//TODO [Drools] execute rules engine for Acceptance Rules
	
	//TODO [Drools] execute rule engine for Transit Time Rules
	
	//TODO Set delivery date based on calculated values
		//What is retrograde offset?
		//What is prograde offset?
	
	//TODO create and run Calculate PME Delivery Date subroutine
	
	//TODO return data back up to ATF main
	

}
