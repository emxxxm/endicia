package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

public class COT_ALL extends AbsDataFile{
	int tupleID = 0, rangeID = 4;
	@Override
	public String getFileName() {
	
		return FilenameConstants.COT_ALL;
	}
	//TODO [optimization] add index
	public ArrayList<String> getCot(int dOW, String ZIP){
		ArrayList<String> cotList = new ArrayList<String>();
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).contains(ZIP)){
				cotList.add(r.get(dOW + rangeID));
			}
		}
		return cotList;
		
	}

}
