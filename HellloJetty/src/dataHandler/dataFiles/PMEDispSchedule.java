package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import dataHandler.DataFileParser;

public class PMEDispSchedule extends AbsDataFile {
	int originRangeID = 0, destRangeID = 2;
	@Override
	public String getFileName() {
		return FilenameConstants.PME_DISP;
	}

	public ArrayList<CSVRecord> getDispList(String originFACID, String destFACID) {
		ArrayList<CSVRecord> dispList = new ArrayList<CSVRecord>();
		for(CSVRecord r: recordsList){
			if(r.get(originRangeID).equals(originFACID) && r.get(destRangeID).equals(destFACID)){
				dispList.add(r);
			}
		}
		return dispList;
	}

	
	
}
