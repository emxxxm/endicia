package dataHandler.dataFiles;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

public class PMEDestSchedule extends AbsDataFile {
	int rangeID = 0, rangeID1 = 3;

	public static final Integer CET_ID = 2;
	public static final Integer DELIVERY_TIME_ID = 4;
	public static final int RANK_ID = 5;
	
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
	public HashMap<Integer, Integer> getCETandDeliveryTime(String destZip, String destType) {
		HashMap<Integer, Integer> output = new HashMap<Integer, Integer>();
		ArrayList<CSVRecord> destList = getDestList(destType, destZip);
		
		if (destList.isEmpty()) {
			return output;
		}
		
		CSVRecord record = destList.get(0);
		
		output.put(CET_ID, Integer.parseInt(record.get(CET_ID)));
		output.put(DELIVERY_TIME_ID, Integer.parseInt(record.get(DELIVERY_TIME_ID)));
		output.put(RANK_ID, Integer.parseInt(record.get(RANK_ID)));
		
		return output;
	}


}
