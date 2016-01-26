package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import atfImplementation.Location;

public class COT_ALL extends AbsDataFile {
	int tupleID = 0, rangeID = 4;
	ArrayList<Location> locationList = new ArrayList<Location>();
	
	@Override
	public String getFileName() {

		return FilenameConstants.COT_ALL;
	}

	// TODO [optimization] add index
	// TODO return all records
	public String getCot(int dow, String ZIP) {
		int cot = Integer.MAX_VALUE;
		for (CSVRecord r : recordsList) {
			// TODO return earliest or latest
			if (r.get(tupleID).startsWith(ZIP)) {
				Location newLoc = new Location();
				newLoc.zipCode = ZIP;
				//locationList.add(r);
				locationList.add(newLoc);
				if (Integer.parseInt(r.get(dow + rangeID)) < cot)
					cot = Integer.parseInt(r.get(dow + rangeID));
			}
		}
		if (cot != Integer.MAX_VALUE)
			return String.valueOf(cot);
		else
			return "";
	}
	
	public ArrayList<Location> getLocationList() {
		return locationList;
	}
}
