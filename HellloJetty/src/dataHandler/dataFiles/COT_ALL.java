package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import atfImplementation.Location;

public class COT_ALL extends AbsDataFile {
	private int tupleID = 0, rangeID = 4, ZIP_ID = 0, FAC_NAME_ID = 1, FAC_ADDRESS_ID = 2, FAC_CITY_ID =3, FAC_STATE_ID = 4;
	private ArrayList<Location> locationList = new ArrayList<Location>();
	           
	
	@Override
	public String getFileName() {
		return FilenameConstants.COT_ALL;
	}

	// TODO [optimization] add index
	// TODO return all records
	public String getCot(int dow, String ZIP) {
		Location newLoc;
		int cot = Integer.MAX_VALUE;
		for (CSVRecord r : recordsList) {
			// TODO return earliest or latest
			if (r.get(tupleID).startsWith(ZIP)) {
				newLoc = getLocationFromFile(r, dow);
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
	
	public Location getLocationFromFile(CSVRecord r, int dow) {
		String zip = r.get(ZIP_ID);
		String facName = r.get(FAC_NAME_ID);
		String facAddress = r.get(FAC_ADDRESS_ID);
		String facCity = r.get(FAC_CITY_ID);
		String facState = r.get(FAC_STATE_ID);
		String cutOffTime = r.get(dow + rangeID);
		return new Location(zip, facName, facAddress, facCity, facState, cutOffTime);
	}

}
