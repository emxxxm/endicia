package atfImplementation.PMECommitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.commons.csv.CSVRecord;

import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.APOFPODPO;
import dataHandler.dataFiles.AbsDataFile;
import dataHandler.dataFiles.RefValue;

public class APOFPODPOSubroutine {

	int progradeOffset=0,retrogradeOffset=0, retrogradeOffsetFromRecord, progradeOffsetFromRecord;

	//Variables Initialized 
	ArrayList<Integer> lowerbounds = new ArrayList<Integer>(), upperbounds = new ArrayList<Integer>();
	ArrayList<String> DPOZips;
	String originZip="01609", destZip ="90610", mailClass="1", destType = "-1";
	String retrogradeZip, progradeZip, dropOffTime, retrogradeArrivalTime;

	ArrayList<CSVRecord> records;

	public APOFPODPOSubroutine(LinkedHashMap<String,String> queryTuples) throws CalculationNotPossibleException {
		initializeValuesFromDataFile(queryTuples);

		if (isZipInRange(originZip)) {//Path if origin zip is within military range
			if (isZipInRange(destZip)) {
				if (isOriginOrDestDPO()) {
					throw new CalculationNotPossibleException("Either the origin or destination ZIP is DPO. Calculation not possible with supplied data");
				} else {				
					return;//progradeOffset and retrogradeOffset are 0
				}
			}

			if (records.size() == 1) {//If one record is found
				initAPOFPODPOData(records.get(0));
				retrogradeOffset = retrogradeOffsetFromRecord; 
				queryTuples.put(QueryStrings.ORIGIN_ZIP, retrogradeZip);
				retrogradeZip = originZip;
				return;
			} else { //TODO figure out where to get retrograde arrival time in this branch -- TYPO IN CHART??
				//TODO THIS LINE IS REQUIRED FOR PROGRAM ACCORDING TO FLOWCHART BUT YOU CANNOT HAVE A RETROGRADE_ARRIVAL_TIME WITHOUT A RECORD FOUND -- TYPO IN CHART??
			//	queryTuples.put("dropOffTime",retrogradeArrivalTime); //TODO THIS LINE IS REQUIRED FOR PROGRAM ACCORDING TO FLOWCHART BUT YOU CANNOT HAVE A RETROGRADE_ARRIVAL_TIME WITHOUT A RECORD FOUND
				throw new CalculationNotPossibleException("Failed to find a record during lookup with supplied Origin ZIP and Mail Class. Calculation not possible with supplied data");
			}

		} else {//Path if origin zip is not within military range
			if (isZipInRange(destZip)) {
				if (records.size() == 1) {//If one record is found
					initAPOFPODPOData(records.get(0));
					if (isHFPUOrPOBox()) {
						throw new CalculationNotPossibleException("Destination Type is either PO Box or HFPU. Calculation is not possible with the supplied data.");
					} else {
						progradeOffset = progradeOffsetFromRecord;
						queryTuples.put(QueryStrings.DEST_ZIP, progradeZip);
						progradeZip = destZip;
						
					}
				}
			} else {
				return;
			}
		} 	
	}

	private boolean isHFPUOrPOBox() {
		return QueryParser.isHFPU(destType) || QueryParser.isPOBox(destType);
	}

	private void initializeValuesFromDataFile(LinkedHashMap<String, String> queryTuples) {
		IDataMaster dm = DataMaster.getInstance();
		RefValue refData = dm.getRefValue();
		APOFPODPO APOData = dm.getAPOFPODPO(); 

		initRanges(refData.getMilitaryZipRanges());
		DPOZips = refData.getDPOZips();

		records = APOData.getRecords(mailClass, originZip);
		dropOffTime = queryTuples.get(QueryStrings.DROP_OFF_TIME);
		originZip = queryTuples.get(QueryStrings.ORIGIN_ZIP);
		destZip = queryTuples.get(QueryStrings.DEST_ZIP);
		mailClass = queryTuples.get(QueryStrings.MAIL_CLASS);
		destType = queryTuples.get(QueryStrings.DEST_TYPE);
	}

	//TODO test this method
	private void initAPOFPODPOData(CSVRecord record) {
		progradeOffsetFromRecord = Integer.parseInt(record.get(APOFPODPO.PRO_OFFSET));
		retrogradeOffsetFromRecord = Integer.parseInt(record.get(APOFPODPO.RETRO_OFFSET));
		progradeZip = record.get(APOFPODPO.PRO_FAC_ZIP);
		retrogradeZip = record.get(APOFPODPO.RETRO_FAC_ZIP);
		retrogradeArrivalTime = record.get(APOFPODPO.RETRO_ARRIVAL);		
	}

	/**
	 * Initializes the variables lowerbounds and upperbounds to contain a list of the parsed values
	 * @param militaryRanges a list of strings in the format "LOWERZIP-UPPERZIP" (i.e. "34000-34099") 
	 * These strings are obtained from the ATF_REF_VALUE file; any tuples with the prefix "APOFPODPO_ZIP_RANGE" are taken to be bounds
	 */
	private void initRanges(ArrayList<String> militaryRanges) {
		String[] bounds;
		for (String s: militaryRanges) {
			bounds = s.split("-");
			lowerbounds.add(Integer.parseInt(bounds[0]));
			upperbounds.add(Integer.parseInt(bounds[1]));
		}

	}

	/**
	 * Calculates whether the given zip code string is numerically within the Military ZIP boundaries specified in ATF_REF_VALUE
	 * @param zip The ZIP Code to be checked against the lower and upper bounds
	 * @return true if the zip code is inclusively between the lower and upper bounds
	 * TODO make private once testing is not needed
	 */
	public boolean isZipInRange(String zip) {
		int intZip = Integer.parseInt(zip);
		boolean inRange = false;

		for (int i = 0; i < lowerbounds.size(); i++) {
			inRange |= ((lowerbounds.get(i) <= intZip) && 
					(upperbounds.get(i) >= intZip));
		}

		return inRange;
	}

	private boolean isOriginOrDestDPO() {
		return DPOZips.contains(originZip) || DPOZips.contains(destZip);
	}

	public int getProgradeOffset() {
		return progradeOffset;
	}

	public int getRetrogradeOffset() {
		return retrogradeOffset;
	}




}
