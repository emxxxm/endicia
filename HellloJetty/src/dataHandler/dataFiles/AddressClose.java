package dataHandler.dataFiles;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;

public class AddressClose extends AbsDataFile {
	int tupleID = 0, rangeID = 5;
	public static int CITY_ID = 3;
	public static int STATE_ID = 4;
	@Override
	public String getFileName() {
		return FilenameConstants.ADDRESS_CLOSE;
	}

	private String getCloseTimeOnDow(int DOW, String ZIP) throws CalculationNotPossibleException {
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).equals(ZIP)){

				return r.get(rangeID + DOW);

			}
		}
		throw new CalculationNotPossibleException("Did not find corresponding close time");
	}

	//[DateAccess] get the closeTime on given DOW
	public static int getCloseTimeOnDOWWrapper(int DOW, String destZIP) throws NumberFormatException, CalculationNotPossibleException {
		int closeTime = 0;
		IDataMaster d = DataMaster.getInstance();
		AddressClose ac = d.getAddressClose();
		
		closeTime = Integer.parseInt(ac.getCloseTimeOnDow(DOW, destZIP));
		
		return closeTime;
 
	}
/**	
	public ArrayList<CSVRecord> getAddressRecords(String Zip){
		ArrayList<CSVRecord> outputRecords = new ArrayList<CSVRecord>();
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).startsWith(Zip)){
				outputRecords.add(r);
			}
		}
		return outputRecords;
	}
	**/
	public HashMap<String,ArrayList<CSVRecord>> getAddressRecords(String originZip, String destZip) {
		HashMap<String, ArrayList<CSVRecord>> outputRecords = new HashMap<String, ArrayList<CSVRecord>>();
		ArrayList<CSVRecord> destOutputRecords = new ArrayList<CSVRecord>();
		ArrayList<CSVRecord> originOutputRecords = new ArrayList<CSVRecord>();
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).startsWith(originZip)){
				originOutputRecords.add(r);
			} else if (r.get(tupleID).startsWith(destZip)) {
				destOutputRecords.add(r);
			}
		}
		outputRecords.put(QueryStrings.ORIGIN_ZIP, originOutputRecords);
		outputRecords.put(QueryStrings.DEST_ZIP, destOutputRecords);
		return outputRecords;
	}
	
	public static String getCity(CSVRecord r) {
		return r.get(CITY_ID);
	}
	
	public static String getState(CSVRecord r) {
		return r.get(STATE_ID);
	}

}
