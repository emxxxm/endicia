//package droolsRules;
//
//import java.text.ParseException;
//import java.util.Date;
//import java.util.HashMap;
//
//import MainPackage.DateTimeUtilities;
//import MainPackage.QueryParser;
//import MainPackage.QueryStrings;
//import dataHandler.DataMaster;
//
//public class SDCKnowledgeNew implements IDrools {
//	//TODO Change everything back to private
//	//TODO Check on the isNotGuarantee method
//	//TODO refactor into four classes
//	//Delivery Drools File
//	public String deliveryDate, ead, mailClass, progradeZip, originZipAs5Digit, destinationZipAs5Digit, originZip, destinationZip;
//	public String getMailClass() {
//		return mailClass;
//	}
//
//	public void setMailClass(String mailClass) {
//		this.mailClass = mailClass;
//	}
//
//	public String getProgradeZip() {
//		return progradeZip;
//	}
//
//	public void setProgradeZip(String progradeZip) {
//		this.progradeZip = progradeZip;
//	}
//
//	public String getOriginZipAs5Digit() {
//		return originZipAs5Digit;
//	}
//
//	public void setOriginZipAs5Digit(String originZipAs5Digit) {
//		this.originZipAs5Digit = originZipAs5Digit;
//	}
//
//	public String getDestinationZipAs5Digit() {
//		return destinationZipAs5Digit;
//	}
//
//	public void setDestinationZipAs5Digit(String destinationZipAs5Digit) {
//		this.destinationZipAs5Digit = destinationZipAs5Digit;
//	}
//
//	public String getOriginZip() {
//		return originZip;
//	}
//
//	public void setOriginZip(String originZip) {
//		this.originZip = originZip;
//	}
//
//	public String getDestinationZip() {
//		return destinationZip;
//	}
//
//	public void setDestinationZip(String destinationZip) {
//		this.destinationZip = destinationZip;
//	}
//
//	public Integer getTransitTime() {
//		return transitTime;
//	}
//
//	public void setTransitTime(int transitTime) {
//		this.transitTime = transitTime;
//	}
//
//	public Integer getDeliveryDow() {
//		return deliveryDow;
//	}
//
//	public void setDeliveryDow(int deliveryDow) {
//		this.deliveryDow = deliveryDow;
//	}
//
//	public boolean isNoExpressMail() {
//		return noExpressMail;
//	}
//
//	public void setNoExpressMail(boolean noExpressMail) {
//		this.noExpressMail = noExpressMail;
//	}
//
//	public boolean isGuarantee() {
//		return isGuarantee;
//	}
//
//	public void setGuarantee(boolean isGuarantee) {
//		this.isGuarantee = isGuarantee;
//	}
//
//	public Integer getSvcStd() {
//		return svcStd;
//	}
//
//	public void setSvcStd(int svcStd) {
//		this.svcStd = svcStd;
//	}
//
//	public Integer getDeliveryTime() {
//		return deliveryTime;
//	}
//
//	public void setDeliveryTime(int deliveryTime) {
//		this.deliveryTime = deliveryTime;
//	}
//
//	public Date getAcceptDate() throws ParseException {
//		return DateTimeUtilities.getDateFromString(acceptDate);
//	}
//
//	public void setAcceptDate(String acceptDate) {
//		this.acceptDate = acceptDate;
//	}
//
//	public String getSvcStdMsg() {
//		return svcStdMsg;
//	}
//
//	public void setSvcStdMsg(String svcStdMsg) {
//		this.svcStdMsg = svcStdMsg;
//	}
//
//	public Integer getEadDow() {
//		return eadDow;
//	}
//
//	public void setEadDow(int eadDow) {
//		this.eadDow = eadDow;
//	}
//
//	public String getDestType() {
//		return destType;
//	}
//
//	public void setDestType(String destType) {
//		this.destType = destType;
//	}
//
//	public boolean isMissedCot() {
//		return missedCot;
//	}
//
//	public void setMissedCot(boolean missedCot) {
//		this.missedCot = missedCot;
//	}
//
//	public Integer getAcceptTime() {
//		return acceptTime;
//	}
//
//	public void setAcceptTime(int acceptTime) {
//		this.acceptTime = acceptTime;
//	}
//
//	public Integer getCutOffTime() {
//		return cutOffTime;
//	}
//
//
//	public void setDeliveryDate(String deliveryDate) {
//		this.deliveryDate = deliveryDate;
//	}
//
//	public void setEad(String ead) {
//		this.ead = ead;
//	}
//
//	public int transitTime, deliveryDow;
//	public boolean noExpressMail;
//	public boolean isGuarantee = true;
//
//	//Post Processing Drools File
//	public int svcStd, deliveryTime;
//	//Accept date 
//	public String acceptDate, svcStdMsg ="";
//	
//	//Transit File
//	public int eadDow;
//	String destType;
//	public boolean missedCot;
//	
//	int acceptTime;
//	//Acceptance file
//	//Accept time is drop off time
//	public Integer cutOffTime;
//	
//	public SDCKnowledgeNew() {
//	}
//	
//	public static SDCKnowledgeNew getFakeDroolsMsg() {
//		SDCKnowledgeNew message = new SDCKnowledgeNew();
//		message.deliveryDate = "31-Jan-2020";
//		message.ead = "28-Feb-2020";
//		message.mailClass = QueryStrings.MAIL_CLASS_PRI;
//		message.transitTime = 3;
//		message.deliveryDow = 5;
//		message.originZipAs5Digit= "01609";
//		message.destinationZipAs5Digit = "94043";
//		message.originZip = "01609";
//		message.destinationZip = "94043";
//		message.progradeZip = "12345";
//		message.noExpressMail = message.isNotExpressMail();
//		message.acceptDate = "03-Feb-2020";
//		return message;
//	}
//	
//	public boolean isNotExpressMail() {
//		return !QueryParser.isExpress(mailClass);
//	}
//	
//	public void print() {
//		System.out.println("DeliveryDate: " + deliveryDate);
//		System.out.println("ead: " + ead);
//		System.out.println("mailCalss: " + mailClass);
//		System.out.println("transitTime: " + transitTime);
//		System.out.println("deliveryDow: " + deliveryDow);
//		System.out.println("originZipAs5Digit: " + originZipAs5Digit);
//		System.out.println("destinationZipAs5Digit: " + destinationZipAs5Digit);
//		System.out.println("originZIp: " + originZip);
//		System.out.println("destinationZip: " + destinationZip);
//		System.out.println("noExpressMail: " + noExpressMail);
//		System.out.println("progradeZip: " + progradeZip);
//	}
//	
//	//Actual methods
//	
//	//Acceptance File
//	public void incrementEad(int increment) throws ParseException {
//		ead = DateTimeUtilities.incrementDate(ead,  increment);
//	}
//	
//	//Transit File
//	public String addDaysToDate(String date, int numDays) throws ParseException {
//		return DateTimeUtilities.incrementDate(date,  numDays);
//	}
//	
//	public void incrementTransitTime(int number) {
//		transitTime += number;
//	}
//	
//	public void decreaseServiceStandard(int number) {
//		svcStd -= number;
//	}
//	
//	public void increaseNonPmeServiceStandard(int number) {
//		svcStd += number;
//	}
//	
//	public void increasePmeServiceStandard(int number) {
//		svcStd += number;
//	}
//	
//	public void increasePriServiceStandard(int number) {
//		svcStd += number;
//	}
//	
//	//From Post Processing File
//	public boolean isDpo(String destinationZip) {
//		return DataMaster.getInstance().getRefValue().getDPOZips().contains(destinationZip);
//	}
//	
//	public boolean isPtfas(String originZip) {
//		return DataMaster.getInstance().getRefValue().getPTFASRanges().contains(originZip);
//	}
//	
//	//From Delivery Drools File
//	public boolean isUspsHoliday(String deliveryDate) {
//		return DataMaster.getInstance().getRefValue().getHolidays().contains(deliveryDate);
//	}
//	
//	public boolean isApoFpoDpo(String destinationZip) {
//		return DataMaster.getInstance().getRefValue().isZipInRange(destinationZip);	
//	}
//	
//	public boolean isOriginZipWithinRange(String lowerBound, String upperBound) {
//		int lower = Integer.parseInt(lowerBound), upper = Integer.parseInt(upperBound), origin = Integer.parseInt(originZipAs5Digit); 
//		return (origin >= lower && origin <= upper);
//	}
//	
//	public boolean isDestinationZipWithinRange(String lowerBound, String upperBound) {
//		int lower = Integer.parseInt(lowerBound), upper = Integer.parseInt(upperBound), dest = Integer.parseInt(destinationZipAs5Digit); 
//		return (dest >= lower && dest <= upper);
//	}
//	
//	public void incrementDeliveryDate(int increment) throws ParseException {
//		System.out.println("Incrementing Delivery Date");
//		deliveryDate = DateTimeUtilities.incrementDate(deliveryDate,  increment);
//	}
//	
//	public void isNotGuaranteed() {
//		isGuarantee = false;
//	}
//	
//	//Getters
//	
//	//Acceptance File
//	
//
//
//	
//	public int getDaysBetweenDates(String ead, String deliverydate) throws ParseException {
//		int days = DateTimeUtilities.getDaysBetweenDates(ead, deliverydate);
//		return days;
//	}
//
//	public Date getDeliveryDate() throws ParseException {
//		return DateTimeUtilities.getDateFromString(deliveryDate);
//	}
//
//	public Date getEad() throws ParseException {
//		return DateTimeUtilities.getDateFromString(ead);
//	}
//	
//	
//	
//	public static SDCKnowledgeNew initializeDroolsMsgForTransit(HashMap<String, String> q) {
//		SDCKnowledgeNew droolsMsg = new SDCKnowledgeNew();
//		droolsMsg.svcStd = Integer.parseInt(q.get(QueryStrings.TRANSIT_TIME));
//		droolsMsg.transitTime = Integer.parseInt(q.get(QueryStrings.TRANSIT_TIME));
//		//droolsMsg.deliveryDow = DateTimeUtilities.getDayOfWeek(droolsMsg.deliveryDate);
//		return initializeDroolsMsg(q, droolsMsg);
//	}
//	
//	public static SDCKnowledgeNew initializeDroolsMsgForPost(HashMap<String, String> q) {
//		SDCKnowledgeNew droolsMsg = new SDCKnowledgeNew();
//		droolsMsg.svcStd = Integer.parseInt(q.get(QueryStrings.TRANSIT_TIME));
//		droolsMsg.transitTime = Integer.parseInt(q.get(QueryStrings.TRANSIT_TIME));
//		//droolsMsg.deliveryDow = DateTimeUtilities.getDayOfWeek(droolsMsg.deliveryDate);
//		return initializeDroolsMsg(q, droolsMsg);
//	}
//	
//	public static SDCKnowledgeNew initializeDroolsMsg(HashMap<String, String> q, SDCKnowledgeNew droolsMsg) {
//		droolsMsg.originZipAs5Digit = q.get(QueryStrings.ORIGIN_ZIP);
//		droolsMsg.destinationZipAs5Digit = q.get(QueryStrings.DEST_ZIP);
//		droolsMsg.originZip = q.get(QueryStrings.ORIGIN_ZIP);
//		droolsMsg.destinationZip = q.get(QueryStrings.DEST_ZIP);
//		droolsMsg.deliveryDate = q.get(QueryStrings.DELIVERY_DATE);
//		droolsMsg.mailClass = q.get(QueryStrings.MAIL_CLASS);
//		droolsMsg.noExpressMail = droolsMsg.isNotExpressMail();
//		droolsMsg.ead = q.get(QueryStrings.EAD);
//		//droolsMsg.cutOffTime = q.get(QueryStrings.CUTOFF_TIME);
//		droolsMsg.acceptDate = q.get(QueryStrings.SHIP_DATE);
//		
//		//TODO Add in prograde zip
//		droolsMsg.progradeZip = "00000";
//		return droolsMsg;
//	}
//
//	@Override
//	public void setAcceptDate(Date acceptDate) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setAcceptTime(Integer acceptTime) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public String getCustDelivOpts() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setCustDelivOpts(String custDelivOpts) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setCutOffTime(Integer cutOffTime) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setEad(Date ead) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setSvcStd(Integer svcStd) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setDeliveryTime(Integer deliveryTime) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setDeliveryDate(Date deliveryDate) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Integer getAcceptDow() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setAcceptDow(Integer acceptDow) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setEadDow(Integer eadDow) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setDeliveryDow(Integer deliveryDow) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setTransitTime(Integer transitTime) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Boolean isPrograde() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setPrograde(Boolean prograde) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Boolean isRetrograde() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setRetrograde(Boolean retrograde) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean getNoExpressMail() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public int getOriginZipAsInt() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getDestinationZipAsInt() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public boolean isOriginZipNotWithinRange(String lowerBound, String upperBound) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isDestinationZipNotWithinRange(String lowerBound, String upperBound) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public long getDaysBetweenDates(Date firstDay, Date secondDay) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Date addDaysToDate(Date initialDate, int daysToAdd) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int getCalendarDayOfWeekFromDate(Date date) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getDayOfWeekOrHolidayFromDate(Date date) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public boolean isUspsHoliday(Date date) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isHolidayEve(Date date) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public String getRetrogradeZip() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setRetrogradeZip(String retrogradeZip) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getMonClose() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setMonClose(int monClose) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getTueClose() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setTueClose(int tueClose) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getWedClose() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setWedClose(int wedClose) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getThuClose() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setThuClose(int thuClose) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getFriClose() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setFriClose(int friClose) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getSatClose() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setSatClose(int satClose) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getSunClose() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setSunClose(int sunClose) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getHolClose() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setHolClose(int holClose) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getOriginCloseTime() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getCloseByDow(int dow) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//}
//
