package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import dataHandler.DataMaster;

public class RefValue extends AbsDataFile {
	int tupleID = 0, valueID = 2;
	private static String MILITARY_RANGE_ID = "APOFPODPO_ZIP_RANGE";
	private static String HOLIDAY_RANGE_ID = "HOLIDAY";
	private static String DPO_ZIP_ID = "DPO_ZIPS";
	private static String NON_PME_DEFAULT_COT_ID = "NON_PME_DEFAULT_COT";
	private static String PTFAS_ID = "PTFAS_ZIPS";
	
	
	public ArrayList<String> getMilitaryZipRanges() {
		
		ArrayList<String> ranges = new ArrayList<String>();
		
		for (CSVRecord r: recordsList) {
			if (r.get(tupleID).equals(MILITARY_RANGE_ID)) {
				ranges.add(r.get(valueID)); 
			}
		}
		return ranges;
	}
	
	public ArrayList<String> getPTFASRanges() {
		ArrayList<String> ranges = new ArrayList<String>();

		for (CSVRecord r: recordsList) {
			if (r.get(tupleID).equals(PTFAS_ID)) {
				ranges.add(r.get(valueID)); 
			}
		}
		return ranges;
	}
	
	public ArrayList<String> getHolidays(){
		ArrayList<String> holidays = new ArrayList<String>();
		
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).equals(HOLIDAY_RANGE_ID)){
				holidays.add(r.get(valueID));
			}
		}
		return holidays;
	}
	
	public ArrayList<String> getDPOZips() {
		ArrayList<String> DPOZips = new ArrayList<String>();
		
		for (CSVRecord r: recordsList) {
			if (r.get(tupleID).equals(DPO_ZIP_ID)) {
				DPOZips.add(r.get(valueID));
			}
		}
		
		return DPOZips;
	}
	
	/**
	 * Initializes the variables lowerbounds and upperbounds to contain a list of the parsed values
	 * @param militaryRanges a list of strings in the format "LOWERZIP-UPPERZIP" (i.e. "34000-34099") 
	 * These strings are obtained from the ATF_REF_VALUE file; any tuples with the prefix "APOFPODPO_ZIP_RANGE" are taken to be bounds
	 */
	public void initRanges(ArrayList<Integer> lowerBounds, ArrayList<Integer> upperBounds) {
		String[] bounds;
		for (String s: getMilitaryZipRanges()) {
			bounds = s.split("-");
			lowerBounds.add(Integer.parseInt(bounds[0]));
			upperBounds.add(Integer.parseInt(bounds[1]));
		}
	}
	
	/**
	 * Calculates whether the given zip code string is numerically within the Military ZIP boundaries specified in ATF_REF_VALUE
	 * @param zip The ZIP Code to be checked against the lower and upper bounds
	 * @return true if the zip code is inclusively between the lower and upper bounds
	 * TODO make private once testing is not needed
	 */
	public boolean isZipInRange(String zip) {
		
		ArrayList<Integer> lowerbounds = new ArrayList<Integer>();
		ArrayList<Integer> upperbounds = new ArrayList<Integer>();
		
		initRanges(lowerbounds, upperbounds);
		
		int intZip = Integer.parseInt(zip);
		boolean inRange = false;

		for (int i = 0; i < lowerbounds.size(); i++) {
			inRange |= ((lowerbounds.get(i) <= intZip) && 
					(upperbounds.get(i) >= intZip));
		}

		return inRange;
	}

	
	public ArrayList<String> getDefaultPRICOT(int DOW){
		ArrayList<String> defaultCot = new ArrayList<String>();
		//TODO figure out what the 3_FRI_COT means in ATF_VAL
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).equals(NON_PME_DEFAULT_COT_ID)){
				defaultCot.add(r.get(valueID));
			}
		}
		return defaultCot;
	}

	@Override
	public String getFileName() {
		return FilenameConstants.REF_VAL;
	}

	
}
