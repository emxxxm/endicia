package droolsRules;

public class SDCKnowledgeDTO {
	//Delivery Drools File
	private String deliveryDate;
	private String ead;
	private int mailClass;
	private int transitTime;
	public int deliveryDow;
	public int originZipAs5Digit;
	public int destinationZipAs5Digit;
	public int originZip;
	public int destinationZip;
	public boolean noExpressMail;
	public int progradeZip;

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
	
	
	//Actual methods
	
	//Acceptance File
	public void incrementEad(int number) {
		
	}
	
	//Transit File
	public void addDaysToDate(String date, int numDays) {
		
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
	public boolean isDpo(int destinationZip) {
		return true;
	}
	
	public boolean isPtfas(int originZip) {
		return true;
	}
	
	//From Delivery Drools File
	public boolean isUspsHoliday(String deliveryDate) {
		return true;
	}
	
	public boolean isApoFpoDpo(int destinationZip) {
		return true;
	}
	
	public boolean isOriginZipWithinRange(String lowerBound, String upperBound) {
		return true;
	}
	
	public boolean isDestinationZipWithinRange(String lowerBound, String upperBound) {
		return true;
	}
	
	public void incrementDeliveryDate(int increment) {
		
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
	
	public int getDaysBetweenDates(String ead, String deliverydate) {
		return -1;
	}

	public int getMailClass() {
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
		
	}
	
	//From Delivery Drools File
	public void setNoExpressMail(boolean newValue) {
		noExpressMail = newValue;
	}
	
	public void setTransitTime(int newTime) {
		transitTime = newTime;
	}
	
	

}

