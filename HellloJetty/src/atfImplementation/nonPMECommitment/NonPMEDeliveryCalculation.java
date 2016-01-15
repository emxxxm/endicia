package atfImplementation.nonPMECommitment;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.AddressClose;

public class NonPMEDeliveryCalculation {
	//Take in Delivery Date which is initally set in Main Flow
	public NonPMEDeliveryCalculation(LinkedHashMap<String, String> queryTuples){
		int closeTime = 0;
		String deliveryDate = "2016/01/14";
		while(closeTime == 0){
			//[Drools] Execute Rules Engine for Delivery Date Rules 
			
			if(isPO_HFPU()){
				int deliveryDOW = getDayOfWeek(queryTuples.get("deliveryDate"));
				closeTime = getCloseTimeOnDOW(deliveryDOW);
				if(closeTime!=0){
					break;
				}
				else{
					deliveryDate = incrementDeliveryDate(deliveryDate);
				}
				
			}
			 
		}
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
		return deliveryDate;
	}
	public int getDayOfWeek(String date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
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
	public int getCloseTimeOnDOW(int DOW){
		
		IDataMaster d = DataMaster.getInstance();
		AddressClose ac = d.getAddressClose();
		
		return 0;//TODO ac.getCloseTime(int DOW, String address);
	}
		
		
			

}
