package atfImplementation.PMECommitment;

import java.util.HashMap;
import java.util.LinkedHashMap;

import atfImplementation.CalculationNotPossibleException;

public class APO_FPO_DPO_Subroutine {
	
	int progradeOffset=0,retrogradeOffset=0, retrogradeOffsetFromRecord=-1, postgradeOffsetFromRecord;
	int upperBound=10000,lowerBound=20000;
	String originZip="01609", destZip ="90610"; //TODO actually get these from the data
	String retrogradeZip, progradeZip, dropOffTime;
	
	
	public APO_FPO_DPO_Subroutine(LinkedHashMap<String,String> queryTuples) throws CalculationNotPossibleException {
		HashMap<String,String> dataFromFile = new HashMap<String,String>();
		//TODO [DataAccess] get APOFPODPO data
		
		initializeValuesFromDataFile();
		originZip = queryTuples.get("originZip");
		destZip = queryTuples.get("destZip");
		
		int intOriginZip = Integer.parseInt(originZip), intDestZip=Integer.parseInt(destZip);
	
		//TODO [DataAccess] lookup Military ZIP ranges in ATF_REF_VALUE
		if (intOriginZip > lowerBound && intOriginZip < upperBound) {
			
			if (intDestZip > lowerBound && intDestZip < upperBound) {
				if (originOrDestDPO()) {
					throw new CalculationNotPossibleException("Either the origin or destination is DPO; calculation not possible");
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
			}
		
		}
	}
	
	private void initializeValuesFromDataFile() {
		//TODO [DataAccess]
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
