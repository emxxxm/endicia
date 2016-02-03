package atfImplementation.PMECommitment;

import java.text.ParseException;
import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.dataFiles.PMEDispSchedule;

public class ExtraFacilityCommitment extends AbsFacilityCommitment {	
	String destFacID, originFacID;
	int departTime, arriveTime, DOWIND;

	public ExtraFacilityCommitment(HashMap<String, String> queryTuples) throws NumberFormatException, ParseException, CalculationNotPossibleException {
		super(queryTuples);


		//while(1) {
		//if(Transit Date = any USPS Holiday in ATF_REF_VAL_T) {
		//DOW = 8
		//} else
		//DOW = Commitment Date DOW

		//if(Origin DOW COT exist) {
		//break;
		//} else {
		//if(NOT Days Elapsed > 0) {
		//Days Elapsed++
		//Transit Date++
		//Drop Off Time = -1
		//} else 
		//Throw Cannot Calculate Exception
		//}
		//}

		//THE ONLY WAY WE BREAK OUT OF THAT LOOP IS IF DOW COT EXISTS

		//if(Drop Off Time <= Origin DOW COT) {
		//if(Origin CLRNC_TIME < D830) {
		//Transit Date++
		//Days Elapsed++
		//}
		//if(NOT CLRNC_TIME <= DEPART_TIME) {
		//Transit Date++
		//Days Elapsed++
		//}

		//while(1) {
		//DOW = Transit Date DOW
		//if(Transit Date = any USPS Holiday in ATF_REF_VAL_T)
		//DOW = 8
		//if(Transit Date = Major Holiday Eve in ATF_REF_VAL_T)
		//DOW = 7
		//if(NOT DOW_IND for Transit Date DOW in Dispatch Record = 1) {
		//Transit Date++
		//Days Elapsed++
		//if(Days Elapsed > 2)
		//Throw Commitment Cannot Be Calculated Exception
		//else
		//continue
		//} else
		//break;
		//}

		//ONLY WAY WE LEAVE THE ABOVE LOOP IS IF DOW_IND FOR TRANSIT DATE DOW IN DISPATCH RECORD =1

		//if(NOT DEPART_TIME <+ ARR_TIME) {
		//Transit Date++;
		//Days Elapsed++;
		//}
		//if(NOT ARR_TIME <= CET) {
		//Transit Date++;
		//Days Elapsed++;
		//}
		//if(NOT CET <= DELIVERY_TIME) {
		//Transit Time++
		//Days Elapsed++
		//}
		//if(Days Elapsed > 2)
		//Throw Commitment Cannot Be Calculated Exception
		//else {
		//Service Standard = Days Elapsed;
		//Delivery Time = Delivery Time from Destination Record;
		//Preferred Indicator = PREFERRED_IND from Dispatch Record;
		//Calculate Commitment Rank by multiplying the RANK_IND from the Origin Record by the RANK_IND from the Destination Record
		//Preliminary Delivery Date = Commitment Date + Service Standard;
		//The Service Standard, Preliminary Delivery Date, Delivery Time, Commitment Date, Preferred Indicator, and Commitment Rank make up the Commitment
		//Add the above commitment to the commitment List
		//Return to Calling method (Calculate PME Commitments)
		//}
		//}
		//else { //THIS IS THE PATH WHERE THE DROP OFF TIME NOT <= ORIGIN DOW COT
		//if(NOT Commitment Date = EAD)
		//Throw Cannot Calculate Commitment Exception
		//Transit Date++
		//Commitment Date++
		//Drop Off Time = -1
		//if(NOT Commitment Date DOW = Sunday) {
		//if(Commitment Date = any USPS Holiday in ATF_REF_VAL_T)
		//DOW = 8
		//}
		//else {
		//if(Commitment Date DOW Close Time = 0) {
		//Transit Date++
		//Commitment Date++
		//}
		//GO TO WHILE LOOP ON LINE 8

	}

	@Override
	public void initValues() {
		super.initValues();

		HashMap<Integer, Integer> dispOutput = DataMaster.getInstance().getPMEDisp().
				getDestFacilityInfo(originFacID, destFacID);

		departTime = dispOutput.get(PMEDispSchedule.DEPART_TIME);
		arriveTime = dispOutput.get(PMEDispSchedule.ARR_TIME);
		preferredIndicator = dispOutput.get(PMEDispSchedule.PREFFERED_INDICATOR);
		DOWIND = dispOutput.get(PMEDispSchedule.DOW_IND_ID);
	}

	@Override
	public void incrementValues() throws ParseException, ReturnToMainLogicException {
		if (CLRNC < 830) {
			incrementTransitDateandDaysElapsed();
		} 

		if (CLRNC <= departTime) {
			incrementTransitDateandDaysElapsed();
		}
	}

	@Override
	public void setCommitmentValues(HashMap<String, String> queryTuples) throws ParseException, ReturnToMainLogicException {
		while (true) {
			DOW = DateTimeUtilities.getDayOfWeek(transitDate);

			if (DataMaster.getInstance().getRefValue().isUspsHoliday(transitDate)) {
				DOW = 8;
			}
			if (DataMaster.getInstance().getRefValue().isHolidayEve(transitDate)) {
				DOW = 7;
			}
			if(DataMaster.getInstance().getPMEDisp().getDOWINDforTransitDateDow(transitDate) != 1) {
				incrementTransitDateandDaysElapsed();

				if (daysElapsed > 2) {
					throw new ReturnToMainLogicException();
				} else { continue; }
			} else {
				break;
			}
		}


		if(! (departTime <= arriveTime) ) {
			incrementTransitDateandDaysElapsed();
		}
		if( !(arriveTime <= CET) ) {
			incrementTransitDateandDaysElapsed();
		}
		if(! (CET <= deliveryTime) ) {
			incrementTransitDateandDaysElapsed();
		}
		if(daysElapsed > 2) {
			throw new ReturnToMainLogicException();
		}

		serviceStd = daysElapsed;
		commitmentRank = destRank * originRank;
		commitmentDate = DateTimeUtilities.incrementDate(commitmentDate, serviceStd);

		outputCommitment = new Commitment(commitmentRank, preferredIndicator, serviceStd, deliveryTime,commitmentDate);
	}

}	