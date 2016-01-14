package dataHandler.dataFiles;

import dataHandler.DataFileParser;

public class PMEDestSchedule extends AbsDataFile {
	
	public PMEDestSchedule() {
		records = DataFileParser.parseFile(FilenameConstants.PME_DEST);
	}
}
