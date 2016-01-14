package dataHandler.dataFiles;

import dataHandler.DataFileParser;

public class AddressClose extends AbsDataFile {

	public AddressClose() {
		records = DataFileParser.parseFile(FilenameConstants.ADDRESS_CLOSE);
	}
	
}
