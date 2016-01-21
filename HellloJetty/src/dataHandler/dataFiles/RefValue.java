package dataHandler.dataFiles;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.csv.CSVRecord;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;

import dataHandler.DataMaster;

public class RefValue extends AbsDataFile {
	int tupleID = 0, valueID = 2;
	private static String MILITARY_RANGE_ID = "APOFPODPO_ZIP_RANGE";
	private static String HOLIDAY_RANGE_ID = "HOLIDAY";
	private static String DPO_ZIP_ID = "DPO_ZIPS";
	private static String NON_PME_DEFAULT_COT_ID = "NON_PME_DEFAULT_COT";
	private static String PTFAS_ID = "PTFAS_ZIPS";
	private static String PM_DEFAULT_COT_ID = "DEFAULT_COT_PM";
	
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

	//TODO further clarification on COT of mailclass
	public String getDefaultPMCOT(int DOW){
		String dowInFile = "";
		String cot = "";
		if(DOW <= Calendar.FRIDAY && DOW >= Calendar.MONDAY){
			dowInFile = "M_F";
		}
		else if(DOW == Calendar.SATURDAY){
			dowInFile = "SAT";
		}
		else{
			dowInFile = "SUN";
		}
		
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).equals(PM_DEFAULT_COT_ID)&& 
					r.get(tupleID +1).equals(dowInFile)){
				cot = r.get(valueID);
			}
		}
		return cot;
		
	}
	public String getDefaultNonPMCOT(int DOW, String mailClass){
		String dowInFile = mailClass;
		switch(mailClass){
			case QueryStrings.MAIL_CLASS_FCM:
				dowInFile = "3";
				break;
			case QueryStrings.MAIL_CLASS_STD:
				dowInFile = "4";
				break;
			case QueryStrings.MAIL_CLASS_PER:
				dowInFile = "5";
				break;
			case QueryStrings.MAIL_CLASS_PKG:
				dowInFile = "6";
				break;
			default:
				
				break;
		}
		String cot = "";
		switch(DOW){
			case Calendar.MONDAY:
				dowInFile += "_MON_COT";
				break;
			case Calendar.TUESDAY:
				dowInFile += "_TUE_COT";
				break;
			case Calendar.WEDNESDAY:
				dowInFile += "_WED_COT";
				break;
			case Calendar.THURSDAY:
				dowInFile += "_THU_COT";
				break;
			case Calendar.FRIDAY:
				dowInFile += "_FRI_COT";
				break;
			case Calendar.SATURDAY:
				dowInFile += "_SAT_COT";
				break;
			case Calendar.SUNDAY:
				dowInFile += "_SUN_COT";
				break;
			default:
				break;
		}
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).equals(NON_PME_DEFAULT_COT_ID) && 
					r.get(tupleID + 1).equals(dowInFile)){
				cot = r.get(valueID);
			}
		}
		return cot;
	}

	@Override
	public String getFileName() {
		return FilenameConstants.REF_VAL;
	}

	
}
