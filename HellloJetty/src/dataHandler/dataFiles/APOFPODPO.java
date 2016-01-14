package dataHandler.dataFiles;

import dataHandler.DataFileParser;

public class APOFPODPO extends AbsDataFile {
	
	public APOFPODPO() {
		records = DataFileParser.parseFile(FilenameConstants.APOFPODPO);
	}
	
}
