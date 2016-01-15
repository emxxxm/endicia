package dataHandler.dataFiles;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVRecord;

import MainPackage.LoggingHub;
import dataHandler.DataFileParser;

public abstract class AbsDataFile implements IDataFile {

	List<CSVRecord> recordsList;
	protected final static Logger logger = Logger.getLogger(LoggingHub.class.getName());
	
	public AbsDataFile() {
		try {
			recordsList = DataFileParser.parseFile(this.getFileName()).getRecords();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		

	}
	
	
}
