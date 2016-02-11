package atfImplementation.PMECommitment;

import java.text.ParseException;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;

public class IntraFacilityCommitment extends AbsFacilityCommitment {

	public IntraFacilityCommitment(HashMap<String, String> queryTuples, CSVRecord record) throws ParseException, NumberFormatException, CalculationNotPossibleException {
		super(queryTuples, record);
	}
	
	@Override
	public void setCommitmentValues(HashMap<String, String> queryTuples) throws ParseException {
		serviceStd = daysElapsed;
		queryTuples.put(QueryStrings.DELIVERY_TIME, Integer.toString(deliveryTime));
		preferredIndicator = 0;

		if(DataMaster.getInstance().getRefValue().isUSPSHoliday(EAD)){
			DOW = 8;
		} else {
			DOW = DateTimeUtilities.getDayOfWeek(EAD);
		}
		
		commitmentRank = destRank * originRank;

		outputCommitment = new Commitment(commitmentRank, preferredIndicator, serviceStd, deliveryTime, commitmentDate, DOW_COT); 
		
	}
	
	@Override
	public void incrementValues() throws ParseException, ReturnToMainLogicException {
		if(CLRNC < 830){
			incrementTransitDateandDaysElapsed();
		}
		if(!(CLRNC <= CET)) {
			incrementTransitDateandDaysElapsed();
		}

		if(!(CET <= deliveryTime)){
			incrementTransitDateandDaysElapsed();
		}
		
		if(daysElapsed > 2){
			throw new ReturnToMainLogicException(); //Commitment cannot be calculated
		}
	}


}
