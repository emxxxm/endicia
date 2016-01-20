package dataHandler.dataFiles;

import org.apache.commons.csv.CSVRecord;

import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataFileParser;

public class AddressClose extends AbsDataFile {
	int tupleID = 0, rangeID = 5;
	@Override
	public String getFileName() {
		return FilenameConstants.ADDRESS_CLOSE;
	}

	public String getCloseTimeOnDow(int DOW, String destZIP) throws CalculationNotPossibleException {
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).equals(destZIP)){
				if(DOW == 1)
					return r.get(rangeID + 7);
				else return r.get(rangeID + DOW - 1);
			}
		}
		throw new CalculationNotPossibleException("Did not find corresponding close time");
	}
	
}
