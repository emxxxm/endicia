package atfImplementation.PMECommitment;

import java.text.ParseException;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.OriginScheduleAll;
import dataHandler.dataFiles.PMEDestSchedule;

public abstract class AbsFacilityCommitment implements IFacilityCommitment {

	Commitment outputCommitment = null;
	String transitDate, commitmentDate, EAD, originZip, destZip, destType;
	int DOW, dropOffTime, daysElapsed;
	int CLRNC, CET, originRank, destRank;
	int serviceStd, preferredIndicator, deliveryTime, commitmentRank;
	String DOW_COT;
	CSVRecord record;
	public AbsFacilityCommitment(HashMap<String, String> queryTuples, CSVRecord record) throws ParseException, NumberFormatException, CalculationNotPossibleException {
		daysElapsed=0;
		dropOffTime = Integer.parseInt(queryTuples.get(QueryStrings.SHIP_TIME)); 
		destZip = queryTuples.get(QueryStrings.DEST_ZIP);
		destType = queryTuples.get(QueryStrings.DEST_TYPE);
		originZip = queryTuples.get(QueryStrings.ORIGIN_ZIP);
		EAD = queryTuples.get(QueryStrings.EAD);
		transitDate = EAD;
		commitmentDate = EAD;
		this.record = record;
		
		while(true){
			try {
				resolveInnerLoop();
			} catch(ReturnToMainLogicException e) {
				return;
			}
			
			if(dropOffTime <= Integer.parseInt(DOW_COT)){ //Drop off time <= origin DOW COT
				//TODO [USPS] how to deal with multiple records
				initValues();

				try {
					incrementValues();
				} catch (ReturnToMainLogicException e) {
					return;
				}

				try {
					setCommitmentValues(queryTuples);
				} catch (ReturnToMainLogicException e) {
					return;
				}
				
				return; //return to main logic, commitment calculated
			}

			else{
				if( !(commitmentDate.equals(EAD)) ){
					return; //no commitment possible from this information
				}
				resolveOuterLoop();
			}
		}
	}

	protected int getDayOfWeek(String transitDate, String commitmentDate) throws ParseException {
		int DOW;
		if(DataMaster.getInstance().getRefValue().isUSPSHoliday(transitDate)){	
			DOW = 8;
		} else {
			DOW = DateTimeUtilities.getDayOfWeek(commitmentDate);
		}
		return DOW;
	}

	protected void resolveInnerLoop() throws ParseException, ReturnToMainLogicException {
		while(true) {
			DOW = getDayOfWeek(transitDate, commitmentDate);

			DOW_COT = DataMaster.getInstance().getCotAll().getCot(DOW, originZip, QueryStrings.MAIL_CLASS_PME);  
			if((DOW_COT.equals("0") || DOW_COT.equals("0000") || DOW_COT.equals(""))) { //DOW_COT does no exist
				if(daysElapsed > 0){
					throw new ReturnToMainLogicException();
				}

				daysElapsed++;
				transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
				dropOffTime = -1;
			} else {
				break;
			}
		}

	}

	public Commitment getCommitment() {
		return outputCommitment;
	}

	protected void resolveOuterLoop() throws ParseException, NumberFormatException, CalculationNotPossibleException {
		int commitmentDOW;

		transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
		commitmentDate = DateTimeUtilities.incrementDate(commitmentDate, 1);
		dropOffTime = -1; 

		commitmentDOW = DateTimeUtilities.getDayOfWeek(commitmentDate);

		if(commitmentDOW != DateTimeUtilities.DAY_SUNDAY){
			if(DataMaster.getInstance().getRefValue().isUSPSHoliday(commitmentDate)){
				DOW = 8;
			}
			else { 
				return; 
			}
		}
		if(AddressClose.getCloseTimeOnDOWWrapper(commitmentDOW, originZip) == 0) {//TODO [optimization] fix this so it caches
			transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
			commitmentDate = DateTimeUtilities.incrementDate(commitmentDate, 1);
		}
	}
	

	public void incrementTransitDateandDaysElapsed() throws ParseException {
		transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
		daysElapsed++;
	}
	
	public void initValues() {
		HashMap<Integer, String> originScheduleOutput = DataMaster.getInstance().getOriginScheduleAll().getOriginFacilityInfo(originZip);
		CLRNC = Integer.parseInt(originScheduleOutput.get(OriginScheduleAll.CLRNC_ID));
		originRank = Integer.parseInt(originScheduleOutput.get(OriginScheduleAll.RANK_ID));
		
		HashMap<Integer, String> destScheduleOutput = DataMaster.getInstance().getPMEDest().getDestFacilityInfo(destZip, destType);
		deliveryTime = Integer.parseInt(destScheduleOutput.get(PMEDestSchedule.DELIVERY_TIME_ID)); 
		CET = Integer.parseInt(destScheduleOutput.get(PMEDestSchedule.CET_ID));
		destRank = Integer.parseInt(destScheduleOutput.get(PMEDestSchedule.RANK_ID));
	}
}
