package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

public class COT_ALL extends AbsDataFile {
	int tupleID = 0, rangeID = 4;

	@Override
	public String getFileName() {

		return FilenameConstants.COT_ALL;
	}

	// TODO [optimization] add index
	public String getCot(int dow, String ZIP) { //TODO return all
		int cot = Integer.MAX_VALUE;
		for (CSVRecord r : recordsList) {
			// TODO return earliest or latest
			if (r.get(tupleID).startsWith(ZIP)) {
				if (Integer.parseInt(r.get(dow + rangeID)) < cot)
					cot = Integer.parseInt(r.get(dow + rangeID));
			}
		}
		if (cot != Integer.MAX_VALUE)
			return String.valueOf(cot);
		else
			return "";
	}
}
