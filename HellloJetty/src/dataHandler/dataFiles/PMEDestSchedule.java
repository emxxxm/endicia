package dataHandler.dataFiles;

import dataHandler.DataFileParser;

public class PMEDestSchedule extends AbsDataFile {
	
	public PMEDestSchedule() {
		records = DataFileParser.parseFile("ATF_PME_DEST_SCHEDULE.txt");
	}
}
