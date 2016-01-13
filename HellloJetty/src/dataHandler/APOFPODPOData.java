package dataHandler;

import org.apache.commons.csv.CSVParser;

public class APOFPODPOData {
	CSVParser records;
	
	public APOFPODPOData() {
		records = DataFileParser.parseFile("ATF_APOFPODPO.txt");
	}
	
	
	
}
