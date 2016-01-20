package droolsRules;

import java.text.ParseException;
import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import dataHandler.DataMaster;

public class SDCKnowledgeDTO {
	//TODO Change everything back to private
	//TODO Check on the isNotGuarantee method
	//Delivery Drools File
	public String deliveryDate;
	public String ead;
	public String mailClass;
	public int transitTime = 0;
	public int deliveryDow;
	public String originZipAs5Digit;
	public String destinationZipAs5Digit;
	public String originZip;
	public String destinationZip;
	public boolean noExpressMail;
	public String progradeZip;
	public boolean isGuarantee = true;

	//Post Processing Drools File
	public int svcStd;
	public String acceptDate;
	public int deliveryTime;
	public String svcStdMsg;
	
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
		message.mailClass = QueryStrings.MAIL_CLASS_PRI;
		message.transitTime = 3;
		message.deliveryDow = 5;
		message.originZipAs5Digit= "01609";
		message.destinationZipAs5Digit = "94043";
		message.originZip = "01609";
		message.destinationZip = "94043";
		message.noExpressMail = false;
		message.progradeZip = "12345";
		message.noExpressMail = message.isNotExpressMail();
		message.acceptDate = "03-Feb-2020";
		return message;
	}
	
	public boolean isNotExpressMail() {
		return !QueryParser.isExpress(mailClass);
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
		deliveryDate = DateTimeUtilities.incrementDate(ead,  increment);;
		System.out.println(ead);
	}
	
	//Transit File
	public void addDaysToDate(String date, int numDays) throws ParseException {
		String newDate = DateTimeUtilities.incrementDate(date,  numDays);;
		System.out.println(newDate);
	}
	
	public void incrementTransitTime(int number) {
		transitTime += number;
	}
	
	public void decreaseServiceStandard(int number) {
		svcStd -= number;
	}
	
	public void increaseNonPmeServiceStandard(int number) {
		svcStd += number;
	}
	
	public void increasePmeServiceStandard(int number) {
		svcStd += number;
	}
	
	public void increasePriServiceStandard(int number) {
		svcStd += number;
	}
	
	//From Post Processing File
	public boolean isDpo(String destinationZip) {
		return DataMaster.getInstance().getRefValue().getDPOZips().contains(destinationZip);
	}
	
	public boolean isPtfas(String originZip) {
		return DataMaster.getInstance().getRefValue().getPTFASRanges().contains(originZip);
	}
	
	//From Delivery Drools File
	public boolean isUspsHoliday(String deliveryDate) {
		return DataMaster.getInstance().getRefValue().getHolidays().contains(deliveryDate);
	}
	
	public boolean isApoFpoDpo(String destinationZip) {
		return DataMaster.getInstance().getRefValue().isZipInRange(destinationZip);	
	}
	
	public boolean isOriginZipWithinRange(String lowerBound, String upperBound) {
		int lower = Integer.parseInt(lowerBound), upper = Integer.parseInt(upperBound), origin = Integer.parseInt(originZipAs5Digit); 
		return (origin >= lower && origin <= upper);
	}
	
	public boolean isDestinationZipWithinRange(String lowerBound, String upperBound) {
		int lower = Integer.parseInt(lowerBound), upper = Integer.parseInt(upperBound), dest = Integer.parseInt(destinationZipAs5Digit); 
		return (dest >= lower && dest <= upper);
	}
	
	public void incrementDeliveryDate(int increment) throws ParseException {
		deliveryDate = DateTimeUtilities.incrementDate(deliveryDate,  increment);;
	}
	
	public void isNotGuaranteed() {
		isGuarantee = false;
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
		svcStdMsg = newMsg;
	}
	
	//From Delivery Drools File
	public void setNoExpressMail(boolean newValue) {
		noExpressMail = newValue;
	}
	
	public void setTransitTime(int newTime) {
		transitTime = newTime;
	}

}

