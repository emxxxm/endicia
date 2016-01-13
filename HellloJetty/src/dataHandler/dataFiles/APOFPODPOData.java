package dataHandler.dataFiles;

import dataHandler.DataFileParser;

public class APOFPODPOData extends AbsDataFile implements IDataFile {
	
	public APOFPODPOData() {
		records = DataFileParser.parseFile("ATF_APOFPODPO.txt");
	}
	
	
}
