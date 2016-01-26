package atfImplementation.nonPMECommitment;
import java.util.ArrayList;
import java.util.HashMap;
import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.RefValue;

public class PRI_COT {
	String PRI_COT;
	String ZIP, EAD;
	//TODO change the constructor later after format EAD
	public PRI_COT(HashMap<String, String> queryTuples){
		ZIP = queryTuples.get(QueryStrings.ORIGIN_ZIP);
		EAD = queryTuples.get(QueryStrings.EAD);
		
		int DOW = 0;
		//[DataAccess] get USPS_HOL from ATF_REF_VALUE
		ArrayList<String> USPSHoliday = DateTimeUtilities.getUSPSHolidays();
		if(USPSHoliday.contains(EAD)){
			DOW = 8;
		}
		else{
			DOW = DateTimeUtilities.getDayOfWeek(EAD);                  
		}

		//if(EAD is a USPS_HOL)
			//DOW = 8
		//else DOW=EAD_DOW
		
		//[DataAccess]Lookup PRI COT for DOW from ATF_COT
		PRI_COT = get_PRI_COT(DOW, ZIP); 
		
		if(PRI_COT.isEmpty()){
			IDataMaster d = DataMaster.getInstance();
			PRI_COT=d.getRefValue().getDefaultPMCOT(DOW);
		}
		
		
	}
	public String getPRI_COT(){
		return this.PRI_COT;
	}
	private String get_PRI_COT(int DOW, String ZIP){
		IDataMaster d = DataMaster.getInstance();
		String PRI_COT = d.getCotAll().getCot(DOW, ZIP); //TODO return all
		return PRI_COT;
	}


}
