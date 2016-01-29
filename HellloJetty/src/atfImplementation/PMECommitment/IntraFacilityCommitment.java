package atfImplementation.PMECommitment;

import java.text.ParseException;
import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.OriginScheduleAll;
import dataHandler.dataFiles.PMEDestSchedule;

public class IntraFacilityCommitment {

	Commitment outputCommitment = null;
	String transitDate, commitmentDate, EAD, originZip;
	int DOW, dropOffTime;

	public IntraFacilityCommitment(HashMap<String, String> queryTuples) throws ParseException, NumberFormatException, CalculationNotPossibleException {
		int daysElapsed=0, dropOffTime = Integer.parseInt(queryTuples.get(QueryStrings.SHIP_TIME)); 
		int CLRNC, CET, originRank, destRank;
		int serviceStd, preferredIndicator, deliveryTime, commitmentRank;
		String DOW_COT;
		String destZip = queryTuples.get(QueryStrings.DEST_ZIP), destType = queryTuples.get(QueryStrings.DEST_TYPE);

		originZip = queryTuples.get(QueryStrings.ORIGIN_ZIP);
		EAD = queryTuples.get(QueryStrings.EAD);
		transitDate = EAD;
		commitmentDate = EAD;
		while(true){
			while(true) {
				DOW = getDayOfWeek(transitDate, commitmentDate);

				DOW_COT = DataMaster.getInstance().getCotAll().getCot(DOW, originZip);  
				if((DOW_COT.equals("0") || DOW_COT.equals("0000") || DOW_COT.equals(""))) { //DOW_COT does no exist
					if(daysElapsed > 0){
						return; //TODO test; is exception required? Almost certain it is not. Commitment cannot be calculated
					}

					daysElapsed++;
					transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
					dropOffTime = -1;
				} else {
					break;
				}
			}
			//TODO verify that dropOffTime should be local
			if(dropOffTime <= Integer.parseInt(DOW_COT)){ //Drop off time <= origin DOW COT
				//TODO how to deal with multiple records
				HashMap<Integer, Integer> originScheduleOutput = DataMaster.getInstance().getOriginScheduleAll().getCLRNCandRankIND(originZip);
				CLRNC = originScheduleOutput.get(OriginScheduleAll.CLRNC_ID);
				originRank = originScheduleOutput.get(OriginScheduleAll.RANK_ID);

				HashMap<Integer, Integer> destScheduleOutput = DataMaster.getInstance().getPMEDest().getCETandDeliveryTime(destZip, destType);
				deliveryTime = destScheduleOutput.get(PMEDestSchedule.DELIVERY_TIME_ID); 
				CET = destScheduleOutput.get(PMEDestSchedule.CET_ID);
				destRank = destScheduleOutput.get(PMEDestSchedule.RANK_ID);

				if(CLRNC < 830){
					transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
					daysElapsed++;
				}
				if(!(CLRNC <= CET)) {
					transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
					daysElapsed++;
				}

				if(!(CET <= deliveryTime)){
					transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
					daysElapsed++;
				}

				if(daysElapsed > 2){
					return; //Commitment cannot be calculated
				}

				serviceStd = daysElapsed;
				queryTuples.put(QueryStrings.DELIVERY_TIME, Integer.toString(deliveryTime));//TODO is this correct? should it be returned elsewhere?
				preferredIndicator = 0;

				if(DataMaster.getInstance().getRefValue().isUspsHoliday(EAD)){
					DOW = 8;
				} else {
					DOW = DateTimeUtilities.getDayOfWeek(EAD);
				}

				queryTuples.put(QueryStrings.CUTOFF_TIME, DOW_COT); //TODO is this correct? should it be returned elsewhere?
				commitmentRank = destRank * originRank;

				outputCommitment = new Commitment(commitmentRank, preferredIndicator, serviceStd, deliveryTime, commitmentDate); //TODO add this commitment to the commitment list
				return; //return to main logic
			}

			else{
				if( !(commitmentDate.equals(EAD)) ){
					return; //no commitment possible from this information
				}
				resolveOuterLoop();
			}
		}
	}

	private int getDayOfWeek(String transitDate, String commitmentDate) throws ParseException {
		int DOW;
		if(DataMaster.getInstance().getRefValue().isUspsHoliday(transitDate)){	
			DOW = 8;
		} else {
			DOW = DateTimeUtilities.getDayOfWeek(commitmentDate);
		}
		return DOW;
	}

	public Commitment getCommitment() {
		return outputCommitment;
	}

	public void resolveOuterLoop() throws ParseException, NumberFormatException, CalculationNotPossibleException {
		int commitmentDOW;

		transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
		commitmentDate = DateTimeUtilities.incrementDate(commitmentDate, 1);
		dropOffTime = -1; 

		commitmentDOW = DateTimeUtilities.getDayOfWeek(commitmentDate);

		if(commitmentDOW != DateTimeUtilities.DAY_SUNDAY){
			if(DataMaster.getInstance().getRefValue().isUspsHoliday(commitmentDate)){
				DOW = 8;
			}
			else { 
				return; 
			}
		}
		if(AddressClose.getCloseTimeOnDOWWrapper(commitmentDOW, originZip) == 0) {//TODO fix this so it caches
			transitDate = DateTimeUtilities.incrementDate(transitDate, 1);
			commitmentDate = DateTimeUtilities.incrementDate(commitmentDate, 1);
		}
	}

}
