package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

public class OriginScheduleAll extends AbsDataFile {
	int rangeID = 0;
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

}
