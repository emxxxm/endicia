package atfImplementation;

import java.text.ParseException;
import java.util.HashMap;

import ApacheMain.outputwrappers.DazzleOutputMain;
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
	
	public MainPMEImplementation(HashMap<String, String> q) throws CalculationNotPossibleException {
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
	    queryTuples.put(QueryStrings.TRANSIT_TIME, Integer.toString(transitTime));
	    
	    executeAcceptanceRules();
	    executeTransitRules();
	    
	    deliveryDate = DateTimeUtilities.incrementDate(queryTuples.get(QueryStrings.EAD), transitTime + retrogradeOffset + progradeOffset);
		queryTuples.put(QueryStrings.DELIVERY_DATE, deliveryDate);
		//droolsMsg.deliveryDate = deliveryDate;
		
		deliveryCalc = new PMEDeliveryDate(queryTuples);
		deliveryDate = deliveryCalc.getDeliveryDate();
		queryTuples.put(QueryStrings.DELIVERY_DATE, deliveryDate);
		
		executeServiceStandardRules();
			
	}
	
	@Override 
	public DazzleOutputMain formatOutput() throws CalculationNotPossibleException {
		super.formatOutput();
		output.put(QueryStrings.COMMITMENT, bestCommitmentCalc.getBestCommitment());
		return new DazzleOutputMain(output);
	}	

}
