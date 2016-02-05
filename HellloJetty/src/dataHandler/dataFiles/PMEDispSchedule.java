package dataHandler.dataFiles;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import MainPackage.DateTimeUtilities;

public class PMEDispSchedule extends AbsDataFile {
	int originRangeID = 0, destRangeID = 2;
	CSVRecord record; //TODO test if this updates correctly
	
	public static final int DEPART_TIME = 1;
	public static final int ARR_TIME = 3;
	public static final int PREFFERED_INDICATOR = 4;
	public static final int DOW_IND_ID = 100;
	
	private static final int DOW_OFFSET = 4;
	
	
	@Override
	public String getFileName() {
		return FilenameConstants.PME_DISP;
	}

	public ArrayList<CSVRecord> getDispList(String originFACID, String destFACID) { //TODO [Optimization] cache result
		ArrayList<CSVRecord> dispList = new ArrayList<CSVRecord>();
		for(CSVRecord r: recordsList){
			if(r.get(originRangeID).equals(originFACID) && r.get(destRangeID).equals(destFACID)){
				dispList.add(r);
			}
		}
		return dispList;
	}
	
	public HashMap<Integer, Integer> getDestFacilityInfo(String originFacID, String destFacID) {
		HashMap<Integer, Integer> output = new HashMap<Integer, Integer>();
		ArrayList<CSVRecord> dispList = getDispList(originFacID, destFacID);
		
		if (dispList.isEmpty()) {
			return output;
		}
		
		record = dispList.get(0); //TODO this should correctly cache and retreive the record
		
		output.put(DEPART_TIME, Integer.parseInt(record.get(DEPART_TIME)));
		output.put(ARR_TIME, Integer.parseInt(record.get(ARR_TIME)));
		output.put(PREFFERED_INDICATOR, Integer.parseInt(record.get(PREFFERED_INDICATOR)));
		
		return output;
	}
	
	public int getDOWINDforTransitDateDow(String transitDate) {//TODO this should correctly retreive value from cacheed record
		String IND = record.get(DOW_OFFSET + DateTimeUtilities.getDayOfWeek(transitDate));
		return Integer.parseInt(IND);
	}
//	output.put(DOW_IND_ID, Integer.parseInt(record.get(DOW_OFFSET + transitDateDOW)));
	

	
	
}
