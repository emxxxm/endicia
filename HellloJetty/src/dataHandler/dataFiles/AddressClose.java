package dataHandler.dataFiles;

import dataHandler.DataFileParser;

public class AddressClose extends AbsDataFile implements IDataFile {

	public AddressClose() {
		records = DataFileParser.parseFile("ATF_ADDRESS_CLOSE.txt");
	}
	
}
