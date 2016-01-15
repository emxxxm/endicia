package dataHandler.dataFiles;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.csv.CSVRecord;

public class APOFPODPO extends AbsDataFile {
	private static int ZIP_ID = 0;
	private static int MAIL_CLASS_ID = 1;
	public static int RETRO_FAC_ZIP = 3;
	public static int RETRO_OFFSET = 4;
	public static int PRO_FAC_ZIP = 5;
	public static int PRO_OFFSET = 6;
	public static int RETRO_ARRIVAL = 7;

	@Override
	public String getFileName() {
		return FilenameConstants.APOFPODPO;
	}
	
	public ArrayList<CSVRecord> getRecords(String mailClass, String zipCode) {
		ArrayList<CSVRecord> records = new ArrayList<CSVRecord>();
		for (CSVRecord r: recordsList) {
			if (r.get(ZIP_ID).equals(zipCode) &&
					r.get(MAIL_CLASS_ID).toString().equals(mailClass)) {
				records.add(r);
			}
		}
		
		return records;
	}
}
