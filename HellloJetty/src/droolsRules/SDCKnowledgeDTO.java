package droolsRules;

import java.text.ParseException;
import MainPackage.DateTimeUtilities;

public class SDCKnowledgeDTO {
	//TODO Change everything back to private
	//Delivery Drools File
	public String deliveryDate;
	public String ead;
	public String mailClass;
	public int transitTime;
	public int deliveryDow;
	public String originZipAs5Digit;
	public String destinationZipAs5Digit;
	public String originZip;
	public String destinationZip;
	public boolean noExpressMail;
	public String progradeZip;

	//Post Processing Drools File
	public int svcStd;
	public String acceptDate;
	public int deliveryTime;
	
	//Transit File
	public int eadDow;
	public boolean missedCot;
	public int destType;
	
	//Acceptance file
	public String acceptTime;
	public String cutOffTime;
	
	
	public SDCKnowledgeDTO() {
		
	}
	
	public static SDCKnowledgeDTO getFakeDroolsMsg() {
		SDCKnowledgeDTO message = new SDCKnowledgeDTO();
		message.deliveryDate = "31-Jan-2020";
		message.ead = "28-Feb-2020";
		message.mailClass = "PME";
		message.transitTime = 3;
		message.deliveryDow = 5;
		message.originZipAs5Digit= "01609";
		message.destinationZipAs5Digit = "94043";
		message.originZip = "01609";
		message.destinationZip = "94043";
		message.noExpressMail = false;
		message.progradeZip = "12345";
		return message;
	}
	
	public void print() {
		System.out.println("DeliveryDate: " + deliveryDate);
		System.out.println("ead: " + ead);
		System.out.println("mailCalss: " + mailClass);
		System.out.println("transitTime: " + transitTime);
		System.out.println("deliveryDow: " + deliveryDow);
		System.out.println("originZipAs5Digit: " + originZipAs5Digit);
		System.out.println("destinationZipAs5Digit: " + destinationZipAs5Digit);
		System.out.println("originZIp: " + originZip);
		System.out.println("destinationZip: " + destinationZip);
		System.out.println("noExpressMail: " + noExpressMail);
		System.out.println("progradeZip: " + progradeZip);
	}
	
	//Actual methods
	
	//Acceptance File
	public void incrementEad(int increment) throws ParseException {
		System.out.println("Incremented EAD Date");
		deliveryDate = DateTimeUtilities.incrementDate(ead,  increment);;
		System.out.println(ead);
	}
	
	//Transit File
	public void addDaysToDate(String date, int numDays) throws ParseException {
		System.out.println("Incremented Delivery Date");
		String newDate = DateTimeUtilities.incrementDate(date,  numDays);;
		System.out.println(newDate);
	}
	
	public void incrementTransitTime(int number) {
		
	}
	
	public void decreaseServiceStandard(int number) {
		
	}
	
	public void increaseNonPmeServiceStandard(int number) {
		
	}
	
	public void increasePmeServiceStandard(int number) {
		
	}
	
	public void increasePriServiceStandard(int number) {
		
	}
	
	//From Post Processing File
	public boolean isDpo(String destinationZip) {
		return true;
	}
	
	public boolean isPtfas(String originZip) {
		return true;
	}
	
	//From Delivery Drools File
	public boolean isUspsHoliday(String deliveryDate) {
		return true;
	}
	
	public boolean isApoFpoDpo(String destinationZip) {
		return true;
	}
	
	public boolean isOriginZipWithinRange(String lowerBound, String upperBound) {
		return true;
	}
	
	public boolean isDestinationZipWithinRange(String lowerBound, String upperBound) {
		return true;
	}
	
	public void incrementDeliveryDate(int increment) throws ParseException {
		System.out.println("Incremented Delivery Date");
		deliveryDate = DateTimeUtilities.incrementDate(deliveryDate,  increment);;
		System.out.println(deliveryDate);
	}
	
	public void isNotGuaranteed() {
		
	}
	
	
	
	
	
	
	//Getters
	
	//Acceptance File
	
	//From Post Processing File
	public int getSvcStd() {
		return svcStd;
	}
	
	//From Delivery Drools File
	public int getTransitTime() {
		return transitTime;
	}
	
	public int getDaysBetweenDates(String ead, String deliverydate) throws ParseException {
		int days = DateTimeUtilities.getDaysBetweenDates(deliverydate, ead);
		System.out.println("Inside SDC Days: " + days);
		return days;
	}

	public String getMailClass() {
		return mailClass;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public String getEad() {
		return ead;
	}
	
	
	
	
	
	
	
	//Setters
	//From Accept File
	public void setMissedCot(boolean newValue) {
		missedCot = newValue;
	}
	
	public void setEadDow(int newInt) {
		eadDow = newInt;
	}
	
	//From PostProcessing Drools File
	public void setDeliveryTime(int newTime) {
		deliveryTime = newTime;
	}
	
	public void setSvcStdMsg(String newMsg) {
		//svcStd = newMsg;
	}
	
	//From Delivery Drools File
	public void setNoExpressMail(boolean newValue) {
		noExpressMail = newValue;
	}
	
	public void setTransitTime(int newTime) {
		transitTime = newTime;
	}
	
	

}

