package atfImplementation.nonPMECommitment;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import MainPackage.DateTimeUtilities;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.RulesObject;
import droolsRules.SDCKnowledgeDTO;

public class NonPMEDeliveryCalculation {
	
	SDCKnowledgeDTO droolsMsg = new SDCKnowledgeDTO();
	RulesObject rules = new RulesObject();
	IDataMaster m = DataMaster.getInstance();
	
	//Take in Delivery Date which is initally set in Main Flow
	public NonPMEDeliveryCalculation(HashMap<String, String> q){
		int closeTime = 0;
		droolsMsg.destinationZip = "96850";
		//TODO PUT DELIVERYDATE in queryTuples
		droolsMsg.deliveryDate = "17-Jan-2016";
		droolsMsg.mailClass = "PRI";
		droolsMsg.ead = "15-Jan-2016";
		while(closeTime == 0){
			//[Drools] Execute Rules Engine for Delivery Date Rules 
			rules = m.getRulesObject();
			rules.getSessionList().get(RulesObject.DROOLS_DELIVERY).execute(droolsMsg);
			
			if(isPO_HFPU()){
				int deliveryDOW = getDayOfWeek(droolsMsg.deliveryDate);
				closeTime = getCloseTimeOnDOW(deliveryDOW, droolsMsg.destinationZip);
				if(closeTime!=0){
					break;
				}
				else{
					try {
						droolsMsg.deliveryDate = DateTimeUtilities.incrementDate(droolsMsg.deliveryDate, 1);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
		}
	}
	public String getDeliveryTime(){
		return droolsMsg.deliveryDate;
	}
	//CLOSE TIME = 0
	//while(CLOSE_TIME==0){
	
		//[Drools] Execute Rules Engine for Delivery Date Rules 
		
		//if the Destination is a PO Box or HFPU{
			//[DataAccess]Read delivery DOW Close Time from [ATF_ADDRESS_CLOSE]
			//CLOSE_TIME = delivery DOW Close Time
			//if(CLOSE_TIME!=0)
				//break;
			//else DeliveryDate++
		//}
	
		//else return Modified Delivery Date;}
	//return Modified Delivery Date;
	
	//TODO decide whether is PO or HFPU
	public boolean isPO_HFPU(){
		return true;
	}
	//TODO deliveryDate++
	public String incrementDeliveryDate(String deliveryDate){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date calendarDate = new Date();
		try {
			 calendarDate = format.parse(deliveryDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendarDate);
		calendar.add(Calendar.DATE, 1);
		String updatedDeliveryDate = format.format(calendar.getTime());
		return updatedDeliveryDate;
	}
	public int getDayOfWeek(String date){
		int dow = 0;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date calendarDate = new Date();
		try {
			 calendarDate = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendarDate);
		return calendar.get(Calendar.DAY_OF_WEEK);

		
	}
	//[DateAccess] get the closeTime on given DOW
	public int getCloseTimeOnDOW(int DOW, String destZIP){
		int closeTime = 0;
		IDataMaster d = DataMaster.getInstance();
		AddressClose ac = d.getAddressClose();
		try {
			closeTime = Integer.parseInt(ac.getCloseTimeOnDow(DOW, destZIP));
		} catch (NumberFormatException | CalculationNotPossibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return closeTime;
		
	}
		
		
			

}
