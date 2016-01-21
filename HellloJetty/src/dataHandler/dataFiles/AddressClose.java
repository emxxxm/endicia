package dataHandler.dataFiles;

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
				if(DOW == 1)
					return r.get(rangeID + 7);
				else return r.get(rangeID + DOW - 1);
			}
		}
		throw new CalculationNotPossibleException("Did not find corresponding close time");
	}

	//[DateAccess] get the closeTime on given DOW
	public static int getCloseTimeOnDOWWrapper(int DOW, String destZIP){
		int closeTime = 0;
		IDataMaster d = DataMaster.getInstance();
		AddressClose ac = d.getAddressClose();
		try {
			closeTime = Integer.parseInt(ac.getCloseTimeOnDow(DOW, destZIP));
		} catch (NumberFormatException | CalculationNotPossibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return closeTime;
 
	}

}
