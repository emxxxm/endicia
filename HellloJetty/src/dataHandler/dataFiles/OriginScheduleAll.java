package dataHandler.dataFiles;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

public class OriginScheduleAll extends AbsDataFile {
	private int rangeID = 0;
	
	public static final int CLRNC_ID = 14;
	public static final int RANK_ID = 15;
	
	@Override
	public String getFileName() {
		return FilenameConstants.ORIGIN_SCHEDULE_ALL;
	}

	public ArrayList<CSVRecord> getOriginList(String originZIP) {
		ArrayList<CSVRecord> originList = new ArrayList<CSVRecord>();
		for(CSVRecord r: recordsList){
			if(r.get(rangeID).startsWith(originZIP)){
				originList.add(r);
			}
		}
		return originList;
	}
	
	//TODO make this method get the CLRNC
	private int getOriginCLRNC(ArrayList<CSVRecord> originList) {
		String output = "";
		
		return Integer.parseInt(output);
	}
	
	public HashMap<Integer, Integer> getCLRNCandRankIND(String originZip) {
		HashMap<Integer, Integer> output = new HashMap<Integer, Integer>();
		
		ArrayList<CSVRecord> originList = getOriginList(originZip);

		if (!originList.isEmpty()) {
			return output;
		}
		
		//TODO this should not just get the first record! USPS!!!!!!!!!!!!!!!!!!!!!!!
		CSVRecord record = originList.get(0);
		
		output.put(CLRNC_ID, Integer.parseInt(record.get(CLRNC_ID)));
		output.put(RANK_ID, Integer.parseInt(record.get(RANK_ID)));
		
		return output;
	}

}
