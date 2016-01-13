package dataHandler.dataFiles;

import dataHandler.DataFileParser;

public class RefValue extends AbsDataFile implements IDataFile {
	
	public RefValue() {
		records = DataFileParser.parseFile("ATF_REF_VALUE.txt");
	}
}
