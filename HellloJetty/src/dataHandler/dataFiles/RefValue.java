package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

public class RefValue extends AbsDataFile {
	int tupleID = 0, rangeID = 2;
	private static String MILITARY_RANGE_ID = "APOFPODPO_ZIP_RANGE";
	private static String HOLIDAY_RANGE_ID = "HOLIDAY";
	private static String DPO_ZIP_ID = "DPO_ZIPS";
	private static String NON_PME_DEFAULT_COT_ID = "NON_PME_DEFAULT_COT";
	
	
	public ArrayList<String> getMilitaryZipRanges() {
		
		ArrayList<String> ranges = new ArrayList<String>();
		
		for (CSVRecord r: recordsList) {
			if (r.get(tupleID).equals(MILITARY_RANGE_ID)) {
				ranges.add(r.get(rangeID)); 
			}
		}
		return ranges;
	}
	public ArrayList<String> getHolidays(){
		ArrayList<String> holidays = new ArrayList<String>();
		
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).equals(HOLIDAY_RANGE_ID)){
				holidays.add(r.get(rangeID));
			}
		}
		return holidays;
	}
	
	public ArrayList<String> getDPOZips() {
		ArrayList<String> DPOZips = new ArrayList<String>();
		
		for (CSVRecord r: recordsList) {
			if (r.get(tupleID).equals(DPO_ZIP_ID)) {
				DPOZips.add(r.get(rangeID));
			}
		}
		
		return DPOZips;
	}
	
	public ArrayList<String> getDefaultPRICOT(int DOW){
		ArrayList<String> defaultCot = new ArrayList<String>();
		//TODO figure out what the 3_FRI_COT means in ATF_VAL
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).equals(NON_PME_DEFAULT_COT_ID)){
				defaultCot.add(r.get(rangeID));
			}
		}
		return defaultCot;
	}

	@Override
	public String getFileName() {
		return FilenameConstants.REF_VAL;
	}

	
}
