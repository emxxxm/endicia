package dataHandler;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import MainPackage.LoggingHub;

public class DataFileParser {

	private final static Logger logger = Logger.getLogger(LoggingHub.class.getName());

	public static CSVParser parseFile(String filename) {
		Reader in = null;
		CSVParser records = null;
				
		try {
			in = new FileReader(filename);
		} catch (FileNotFoundException e1) {
			logger.log(Level.SEVERE, e1.getMessage(), e1);
		}
		
		try {
			records = CSVFormat.DEFAULT.parse(in);
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return records;
	}
}
