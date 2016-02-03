package dataHandler.dataFiles;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

public class PMEDestSchedule extends AbsDataFile {
	int rangeID = 0, rangeID1 = 3;

	public static final int CET_ID = 2;
	public static final int DELIVERY_TIME_ID = 4;
	public static final int RANK_ID = 5;
	public static final int FAC_ID = 1;
	
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
	
	//TODO get the values from the correct record instead of the first
	public HashMap<Integer, String> getDestFacilityInfo(String destZip, String destType) {
		HashMap<Integer, String> output = new HashMap<Integer, String>();
		ArrayList<CSVRecord> destList = getDestList(destType, destZip);
		
		if (destList.isEmpty()) {
			return output;
		}
		
		CSVRecord record = destList.get(0);
		
		output.put(CET_ID, record.get(CET_ID));
		output.put(DELIVERY_TIME_ID, record.get(DELIVERY_TIME_ID));
		output.put(RANK_ID, record.get(RANK_ID));
		output.put(FAC_ID, record.get(FAC_ID));
		
		return output;
	}


}
