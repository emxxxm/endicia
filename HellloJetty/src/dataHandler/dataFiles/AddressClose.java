package dataHandler.dataFiles;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.csv.CSVRecord;

import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;

public class AddressClose extends AbsDataFile {
	int tupleID = 0, rangeID = 5;
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
	public static int getCloseTimeOnDOWWrapper(int DOW, String destZIP) throws NumberFormatException, CalculationNotPossibleException{
		int closeTime = 0;
		IDataMaster d = DataMaster.getInstance();
		AddressClose ac = d.getAddressClose();
		
		closeTime = Integer.parseInt(ac.getCloseTimeOnDow(DOW, destZIP));
		
		return closeTime;
 
	}
	
	public ArrayList<CSVRecord> getAddressRecords(String Zip){
		ArrayList<CSVRecord> outputRecords = new ArrayList<CSVRecord>();
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).startsWith(Zip)){
				outputRecords.add(r);
			}
		}
		return outputRecords;
	}

}
