package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import dataHandler.DataFileParser;

public class RefValue extends AbsDataFile {
	int tupleID = 0, rangeID = 2;
	private static String MILITARY_RANGE_ID = "APOFPODPO_ZIP_RANGE";
	
	public RefValue() {
		records = DataFileParser.parseFile(FilenameConstants.REF_VAL);
	}
	
	public ArrayList<String> getMilitaryZipRanges() {
		
		ArrayList<String> ranges = new ArrayList<String>();
		
		for (CSVRecord r: records) {
			String teststr= r.get(tupleID);
			if (teststr.equals(MILITARY_RANGE_ID)) {
				ranges.add(r.get(rangeID)); 
			}
		}
		return ranges;
	}
	
}
