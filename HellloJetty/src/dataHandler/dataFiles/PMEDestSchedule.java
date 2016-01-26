package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import dataHandler.DataFileParser;

public class PMEDestSchedule extends AbsDataFile {
	int rangeID = 0, rangeID1 = 3;
	@Override
	public String getFileName() {
		return FilenameConstants.PME_DEST;
	}

	public ArrayList<CSVRecord> getDestList(String destType, String destZIP) {
		ArrayList<CSVRecord> destList = new ArrayList<CSVRecord>();
		for(CSVRecord r: recordsList){
			if((r.get(rangeID).startsWith(destZIP) || destZIP.startsWith(r.get(rangeID))) && r.get(rangeID1).equals(destType)){
				destList.add(r);
			}
		}
		return destList;
	}
	
	
}
