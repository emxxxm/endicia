package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import dataHandler.DataFileParser;

public class RefValue extends AbsDataFile implements IDataFile {
	int tupleID = 0, rangeID = 2;
	private static String MILITARY_RANGE_ID = "APOFPODPO_ZIP_RANGE";
	
	public RefValue() {
		records = DataFileParser.parseFile("ATF_REF_VALUE.txt");
	}
	
	public ArrayList<String> getMilitaryZipRanges() {
		ArrayList<String> ranges = new ArrayList<String>();
		
		for (CSVRecord r: records) {
			if (r.get(tupleID).equals(MILITARY_RANGE_ID)) {
				ranges.add(r.get(rangeID)); 
			}
		}
		return ranges;
	}
	
}
