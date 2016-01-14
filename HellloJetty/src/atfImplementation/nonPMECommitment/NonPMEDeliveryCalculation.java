package atfImplementation.nonPMECommitment;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

public class NonPMEDeliveryCalculation {
	//Take in Delivery Date which is initally set in Main Flow
	public NonPMEDeliveryCalculation(LinkedHashMap<String, String> queryTuples){
		int closeTime = 0;
		
		while(closeTime == 0){
			//[Drools] Execute Rules Engine for Delivery Date Rules 
			
			if(isPO_HFPU()){
				int deliveryDOW = getDayOfWeek(queryTuples.get("deliveryDate"));
				
				
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
	
	public boolean isPO_HFPU(){
		return true;
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
		
		
			

}
