package dataHandler.dataFiles;

import dataHandler.DataFileParser;

public class PMEDispSchedule extends AbsDataFile implements IDataFile {

	public PMEDispSchedule() {
		records = DataFileParser.parseFile("ATF_PME_DISP_SCHEDULE.txt");
	}
	
}
