package atfImplementation.nonPMECommitment;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import atfImplementation.Location;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;

public class PRI_COT {
	String PRI_COT;
	String ZIP, EAD;
	ArrayList<Location> locationList;
	//TODO change the constructor later after format EAD
	public PRI_COT(HashMap<String, String> queryTuples) throws ParseException{
		ZIP = queryTuples.get(QueryStrings.ORIGIN_ZIP);
		EAD = queryTuples.get(QueryStrings.EAD);
		
		int DOW = 0;
		//[DataAccess] get USPS_HOL from ATF_REF_VALUE
		if(DataMaster.getInstance().getRefValue().isUspsHoliday(EAD)){
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
		String PRI_COT = d.getCotAll().getCot(DOW, ZIP, QueryStrings.MAIL_CLASS_PRI); //TODO return all
		locationList = d.getCotAll().getLocationList();
		return PRI_COT;
	}
	
	public ArrayList<Location> getLocationList() {
		return locationList;
	}

}
