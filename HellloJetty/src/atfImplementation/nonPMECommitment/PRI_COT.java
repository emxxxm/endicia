package atfImplementation.nonPMECommitment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.COT_ALL;
import dataHandler.dataFiles.RefValue;

public class PRI_COT {
	ArrayList<String> PRI_COT;
	//TODO format EAD in main logic 
	//TODO change the constructor later after format EAD
	public PRI_COT(String ZIP, String EAD){
		int DOW = 0;
		//[DataAccess] get USPS_HOL from ATF_REF_VALUE
		ArrayList<String> USPSHoliday = getUSPSHolidays();
		if(USPSHoliday.contains(EAD)){
			DOW = 8;
		}
		else{
			DOW = getDayOfWeek(EAD);                  
		}
		//if(EAD is a USPS_HOL)
			//DOW = 8
		//else DOW=EAD_DOW
		if(USPSHoliday.contains(EAD)){
			DOW = 8;
		}
		else DOW = getDayOfWeek(EAD);
		
		//[DataAccess]Lookup PRI COT for DOW from ATF_COT
		PRI_COT = get_PRI_COT(DOW, ZIP);
		
		if(PRI_COT.isEmpty()){
			IDataMaster d = DataMaster.getInstance();
			
			PRI_COT.addAll(d.getRefValue().getDefaultPRICOT(DOW));
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
	//TODO optimization. This function may also be used in other subroutine
	public int getDayOfWeek(String date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date calendarDate = new Date();
		try {
			 calendarDate = format.parse(date);
		} catch (ParseException e) {
			
			System.out.println(e.getMessage());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendarDate);
		return calendar.get(Calendar.DAY_OF_WEEK);
		
	}

}
