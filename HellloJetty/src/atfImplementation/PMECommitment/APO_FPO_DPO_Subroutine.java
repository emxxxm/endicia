package atfImplementation.PMECommitment;

import java.util.HashMap;
import java.util.LinkedHashMap;

import atfImplementation.CalculationNotPossibleException;

public class APO_FPO_DPO_Subroutine {
	
	int progradeOffset=0,retrogradeOffset=0, retrogradeOffsetFromRecord, progradeOffsetFromRecord;
	int upperBound=10000,lowerBound=20000;
	String originZip="01609", destZip ="90610"; //TODO actually get these from the data
	String retrogradeZip, progradeZip, dropOffTime, retrogradeArrivalTime;
	
	
	public APO_FPO_DPO_Subroutine(LinkedHashMap<String,String> queryTuples) throws CalculationNotPossibleException {
		HashMap<String,String> dataFromFile = new HashMap<String,String>();
		//TODO [DataAccess] get APOFPODPO data
		
		initializeValuesFromDataFile();
		originZip = queryTuples.get("originZip");
		destZip = queryTuples.get("destZip");
	
		//TODO [DataAccess] lookup Military ZIP ranges in ATF_REF_VALUE
		if (zipInRange(originZip)) {//Path if origin zip is within military range
			if (zipInRange(destZip)) {
				if (originOrDestDPO()) {
					throw new CalculationNotPossibleException("Either the origin or destination ZIP is DPO. Calculation not possible with supplied data");
		        } else {				
				return;//progradeOffset and retrogradeOffset are 0
		        }
			}
			
			//[DataAccess] look up origin zip and mail class from ATF_APOFPODPO
			if (dataFromFile.size() == 1) {//If one record is found
				retrogradeOffset = retrogradeOffsetFromRecord; //TODO [DataAccess] Retrograde OFFSET FROM RECORD
				queryTuples.put("originZip", retrogradeZip);
				retrogradeZip = originZip;
				return;
			} else {
				dropOffTime = retrogradeArrivalTime;
				throw new CalculationNotPossibleException("Failed to find a record during lookup with supplied Origin ZIP and Mail Class. Calculation not possible with supplied data");
			}
		
		} else {//Path if origin zip is not within military range
			if (zipInRange(destZip)) {
				//[DataAccess] look up origin zip and mail class from ATF_APOFPODPO
				if (dataFromFile.size() == 1) {//If one record is found
					if (isHFPU()) {
						throw new CalculationNotPossibleException("Destination Type is either PO Box or HFPU. Calculation is not possible with the supplied data.");
					} else {
						progradeOffset = progradeOffsetFromRecord;
						queryTuples.put("destZip", progradeZip);
						progradeZip = destZip;
					}
				}
			} else {
				return;
			}
		}	
	}
	
	private boolean isHFPU() {
		return false;
	}

	private void initializeValuesFromDataFile() {
		//TODO [DataAccess]
		dropOffTime = "-1";
		retrogradeArrivalTime= "-1";
		retrogradeOffsetFromRecord=-1;
		progradeOffsetFromRecord=-1;
	}
	
	private boolean zipInRange(String zip) {
		int intZip = Integer.parseInt(zip);
		return intZip > lowerBound && intZip < upperBound;
	}
			
	private boolean originOrDestDPO() {
		return false;
	}
	
	public int getProgradeOffset() {
		return progradeOffset;
	}
	
	public int getRetrogradeOffset() {
		return retrogradeOffset;
	}
	
	
	

}
