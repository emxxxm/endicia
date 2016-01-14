package dataHandler.dataFiles;

import dataHandler.DataFileParser;

public class PMEDispSchedule extends AbsDataFile {

	public PMEDispSchedule() {
		records = DataFileParser.parseFile(FilenameConstants.PME_DISP);
	}
	
}
