package droolsRules;

import java.text.ParseException;
import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import dataHandler.DataMaster;

public class SDCKnowledgeDTO {
	//TODO Change everything back to private
	//TODO Check on the isNotGuarantee method
	//TODO refactor into four classes
	//Delivery Drools File
	public String deliveryDate, ead, mailClass, progradeZip, originZipAs5Digit, destinationZipAs5Digit, originZip, destinationZip;
	public int transitTime = 0, deliveryDow;
	public boolean noExpressMail;
	public boolean isGuarantee = true;

	//Post Processing Drools File
	public int svcStd, deliveryTime;;
	public String acceptDate, svcStdMsg ="";
	
	//Transit File
	public int eadDow, destType;
	public boolean missedCot;
	
	//Acceptance file
	//Accept time is drop off time
	public String acceptTime, cutOffTime;
	
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
		ead = DateTimeUtilities.incrementDate(ead,  increment);
	}
	
	//Transit File
	public String addDaysToDate(String date, int numDays) throws ParseException {
		return DateTimeUtilities.incrementDate(date,  numDays);
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
		System.out.println("Incrementing Delivery Date");
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
	
	public void printStatement() {
		System.out.println("Firing Rule");
	}
	
	//From Delivery Drools File
	public int getTransitTime() {
		return transitTime;
	}
	
	public int getDaysBetweenDates(String ead, String deliverydate) throws ParseException {
		int days = DateTimeUtilities.getDaysBetweenDates(ead, deliverydate);
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
	
	public static SDCKnowledgeDTO initializeDroolsMsg(HashMap<String, String> q) {
		//TODO actually set these values
		SDCKnowledgeDTO droolsMsg = new SDCKnowledgeDTO();
		droolsMsg = SDCKnowledgeDTO.getFakeDroolsMsg();
		droolsMsg.destinationZip = q.get(QueryStrings.DEST_ZIP);
		//TODO PUT DELIVERYDATE in queryTuples+
		droolsMsg.deliveryDate = q.get(QueryStrings.DELIVERY_DATE);
		droolsMsg.mailClass = q.get(QueryStrings.MAIL_CLASS);
		droolsMsg.noExpressMail = droolsMsg.isNotExpressMail();
		droolsMsg.ead = q.get(QueryStrings.EAD);
		droolsMsg.cutOffTime = q.get(QueryStrings.CUTOFF_TIME);
		
		
		
		
		
		
		//TODO badline
		droolsMsg.svcStd = 1;		
		
				
		
		
		
		
		return droolsMsg;
	}

}

