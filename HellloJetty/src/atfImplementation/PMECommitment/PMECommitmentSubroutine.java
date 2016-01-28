package atfImplementation.PMECommitment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import dataHandler.DataMaster;

public class PMECommitmentSubroutine {
	ArrayList<Commitment> commitmentList = new ArrayList<Commitment>();

	private static final int PMEServiceStd = 2;
	private static final int PMEPreferredIndicator = 0;
	private static final int PMEDeliverytime = 1500;
	private static final int PMECommitmentRank = 1;

	public PMECommitmentSubroutine(HashMap<String, String> queryTuples) throws ParseException {
		int originFACID = 5, destFACID = 1;
		String originZIP = queryTuples.get(QueryStrings.ORIGIN_ZIP);
		String destZIP = queryTuples.get(QueryStrings.DEST_ZIP);
		String destType = queryTuples.get(QueryStrings.getDestTypes());
		ArrayList<CSVRecord> originList = DataMaster.getInstance().getOriginScheduleAll().getOriginList(originZIP);
		ArrayList<CSVRecord> destList = DataMaster.getInstance().getPMEDest().getDestList(destType, destZIP);
		ArrayList<CSVRecord> dispList;
		for (int i = 0; i < originList.size(); i++) {
			CSVRecord originRecord = originList.get(i);
			for (int j = 0; j < destList.size(); j++) {
				CSVRecord destRecord = destList.get(j);
				if (originRecord.get(originFACID).equals(destRecord.get(destFACID))) {
					new IntraFacilityCommitment(queryTuples);// TODO finish intrafacility
					continue;
				} else {
					dispList = DataMaster.getInstance().getPMEDisp().getDispList(originRecord.get(originFACID),
							destRecord.get(destFACID));

					for (int k = 0; i < dispList.size(); k++) {
						CSVRecord dispRecord = dispList.get(k);
						new ExtraFacilityCommitment(); // TODO finish
														// extrafacility;
					}
				}
			}
		}
		if (commitmentList.isEmpty()) {
			Commitment commitment;
			if (Integer.parseInt(queryTuples.get(QueryStrings.SHIP_TIME)) < Integer
					.parseInt(queryTuples.get(QueryStrings.CUTOFF_TIME))) {
				commitment = new Commitment(PMECommitmentRank, PMEPreferredIndicator, PMEServiceStd, PMEDeliverytime,
						queryTuples.get(QueryStrings.EAD));
			} else {
				commitment = new Commitment(PMECommitmentRank, PMEPreferredIndicator, PMEServiceStd, PMEDeliverytime,
						DateTimeUtilities.incrementDate(queryTuples.get(QueryStrings.EAD), 1));
			}
			commitmentList.add(commitment);
		}
		// [DataAccess] Lookup Origin ZIP in [ATF_PME_ORIGIN_SCHEDULE]
		// save all Records

		// [DataAccess] Lookup 5-Digit and 3-Digit Dest ZIP for chosen Dest Type
		// in
		// [ATF_PME_DEST_SCHEDULE; Save all Records

		// While(Origin Records Remaining) {
		// Select 1 Origin Record to create a Commitment with
		// While(Destination Records Remaining) {
		// Select 1 Destination Record to create a Commitment with
		// if(Origin FAC_ID = Dest Fac_ID) {
		// run subroutine Intra-Facility Commitment
		// Discard selected Destination Record
		// continue
		// }else {
		// [DataAccess] Lookup Dispatch Records with the ORIGIN's FAC_ID and the
		// Destination's FAC_IF from ATF_PME_DISP_SCHEDULE;
		// Save all records
		// }
		// while(dispatches remaining) {
		// Select 1 Dispatch Record to create a commitment with
		// Run subroutine Calculate Extra-Facility Commitment
		// Discard selected Dispatch Record
		// }
		// Discard Selected Destination Record
		// continue
		// }
		// Discard selected Origin Record
		// Replenish all discarded Destination Records
		// }

		// if(NOT any commitments exists){
		// if(Drop Off Time <= DOW COT from AFT_COT){
		// Service Standard = 2;
		// Delivery Time = 1500;
		// Commitment Date = EAD;
		// Commitment Rank = 1;
		// Preferred Indicator = 0;}
		// else{
		// Service Standard = 2;
		// Delivery Time = 1500;
		// Commitment Date = EAD + 1;
		// cOMMITMENT rANK = 1;
		// Preferred Indicator = 0}}}
		// else //Any commitments exist
		// return to Main ATF Main login (I believe in our case is PME
		// Commitment Main)
	}

	public ArrayList<Commitment> getCommitments() {
		return commitmentList;
	}

}
