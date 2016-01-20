package atfImplementation.nonPMECommitment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.COT_ALL;
import dataHandler.dataFiles.RefValue;

public class PRI_COT {
	ArrayList<String> PRI_COT;
	HashMap<String, String> queryTuples;
	String ZIP, EAD;
	//TODO change the constructor later after format EAD
	public PRI_COT(HashMap<String, String> queryTuples){
		ZIP = queryTuples.get(QueryStrings.DEST_ZIP);
		EAD = queryTuples.get(QueryStrings.EAD);
		
		int DOW = 0;
		//[DataAccess] get USPS_HOL from ATF_REF_VALUE
		ArrayList<String> USPSHoliday = getUSPSHolidays();
		if(USPSHoliday.contains(EAD)){
			DOW = 8;
		}
		else{
			DOW = DateTimeUtilities.getDayOfWeek(EAD);                  
		}

		//[DataAccess]Lookup PRI COT for DOW from ATF_COT
		PRI_COT = get_PRI_COT(DOW, ZIP);
		
		if(PRI_COT.isEmpty()){
			IDataMaster d = DataMaster.getInstance();
			
			PRI_COT.add(d.getRefValue().getDefaultPMCOT(DOW));
		}
		
		
		
		//if PRI_COT found, save PRI_COT
		//else 
			//[DataAccess]lookup DEFAULT_COT_PRI for DOW from ATF_REF_VALUE
			//save PRI_COT
		
		//return PRI_COT;
		
	}
	public ArrayList<String> getPRI_COT(){
		return this.PRI_COT;
	}
	private ArrayList<String> get_PRI_COT(int DOW, String ZIP){
		IDataMaster d = DataMaster.getInstance();
		ArrayList<String> PRI_COT = d.getCotAll().getCot(DOW, ZIP);
		return PRI_COT;
	}
	public ArrayList<String> getUSPSHolidays(){
		IDataMaster d = DataMaster.getInstance();
		RefValue ref = d.getRefValue();
		return ref.getHolidays();
	}

}
