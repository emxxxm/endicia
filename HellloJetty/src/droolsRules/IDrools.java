package droolsRules;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * This is the interface that Drools rules for SDC will operate on.
 */
public interface IDrools {

	/**
	 * This constant represents a day of week of Sunday.
	 */
	public static final int SUNDAY = Calendar.SUNDAY;
	
	/**
	 * This constant represents a day of week of Monday.
	 */
	public static final int MONDAY = Calendar.MONDAY;
	
	/**
	 * This constant represents a day of week of Tuesday.
	 */
	public static final int TUESDAY = Calendar.TUESDAY;
	
	/**
	 * This constant represents a day of week of Wednesday.
	 */
	public static final int WEDNESDAY = Calendar.WEDNESDAY;
	
	/**
	 * This constant represents a day of week of Thursday.
	 */
	public static final int THURSDAY = Calendar.THURSDAY;
	
	/**
	 * This constant represents a day of week of Friday.
	 */
	public static final int FRIDAY = Calendar.FRIDAY;
	
	/**
	 * This constant represents a day of week of Saturday.
	 */
	public static final int SATURDAY = Calendar.SATURDAY;
	
	/**
	 * This constant represents a day of a USPS Holiday.
	 */
	public static final int HOLIDAY = 8;

	// Getters & Setters
	/**
	 * @return whether or not the guaranteed flag is set
	 */
	boolean isGuarantee();

	/**
	 * Set the guaranteed flag
	 * @param guarantee the {@code} boolean value to set it to
	 */
	void setGuarantee(boolean guarantee);
	
	/**
	 * @return the mail class
	 */
	String getMailClass();

	/**
	 * Set the mail class<br/><br/>
	 * <strong><em>Valid values:</em></strong><br>
	 * PME, PRI, FCM, STD, PER, PKG
	 * @param mailClass the value to set the mail class to.
	 */
	void setMailClass(String mailClass);

	/**
	 * @return the Origin ZIP Code
	 */
	String getOriginZip();

	/**
	 * Set the Origin ZIP Code
	 * @param originZip the Origin ZIP Code
	 */
	void setOriginZip(String originZip);

	/**
	 * @return the Destination ZIP Code
	 */
	String getDestinationZip();

	/**
	 * Set the Destination ZIP Code
	 * @param destinationZip the Destination ZIP Code
	 */
	void setDestinationZip(String destinationZip);

	/**
	 * @return the Acceptance (Drop off) Date
	 * @throws ParseException 
	 */
	Date getAcceptDate() throws ParseException;
	
	/**
	 * Set the Acceptance (Drop off) Date
	 * @param acceptDate the Acceptance (Drop off) Date
	 */
	void setAcceptDate(Date acceptDate);

	/**
	 * @return the Acceptance (Drop off) time.
	 */
	Integer getAcceptTime();

	/**
	 * Set the the Acceptance (Drop off) time.
	 * @param acceptTime the Acceptance (Drop off) time.
	 */
	void setAcceptTime(Integer acceptTime);

	/**
	 * @return The destination type
	 */
	String getDestType();

	/**
	 * Set the destination type.
	 * @param destType the destination type.
	 */
	void setDestType(String destType);

	/**
	 * @return the Customer Delivery Option for PME transactions.
	 */
	String getCustDelivOpts();

	/**
	 * Set the Customer Delivery Option for PME transactions.
	 * @param custDelivOpts the Customer Delivery Option for PME transactions.
	 */
	void setCustDelivOpts(String custDelivOpts);

	/**
	 * @return the Cut-off time.
	 */
	Integer getCutOffTime();

	/**
	 * Set the Cut-off time.
	 * @param cutOffTime the Cut-off time.
	 */
	void setCutOffTime(Integer cutOffTime);

	/**
	 * @return the Effective Acceptance Date.
	 * @throws ParseException 
	 */
	Date getEad() throws ParseException;

	/**
	 * Set the Effective Acceptance Date.
	 * @param ead the Effective Acceptance Date.
	 */
	void setEad(Date ead);

	/**
	 * @return the Service Standard.
	 */
	Integer getSvcStd();

	/**
	 * Set the Service Standard.
	 * @param svcStd the Service Standard.
	 */
	void setSvcStd(Integer svcStd);

	/**
	 * @return the Service Standard Message.
	 */
	String getSvcStdMsg();

	/**
	 * Set the Service Standard Message.
	 * @param svcStdMsg the Service Standard Message.
	 */
	void setSvcStdMsg(String svcStdMsg);

	/**
	 * @return the Delivery Time.
	 */
	Integer getDeliveryTime();

	/**
	 * Set the Delivery Time.
	 * @param deliveryTime the Delivery Time.
	 */
	void setDeliveryTime(Integer deliveryTime);

	/**
	 * @return the Delivery Date.
	 * @throws ParseException 
	 */
	Date getDeliveryDate() throws ParseException;

	/**
	 * Set the Delivery Date.
	 * @param deliveryDate the Delivery Date.
	 */
	void setDeliveryDate(Date deliveryDate);

	/**
	 * @return the Acceptance (Drop-off) Date's Day of Week.
	 */
	Integer getAcceptDow();

	/**
	 * Set the Acceptance (Drop-off) Date's Day of Week.
	 * @param acceptDow the Acceptance (Drop-off) Date's Day of Week.
	 */
	void setAcceptDow(Integer acceptDow);

	/**
	 * @return the Effective Acceptance Date's Day of Week.
	 */
	Integer getEadDow();

	/**
	 * Set the Effective Acceptance Date's Day of Week.
	 * @param eadDow the Effective Acceptance Date's Day of Week.
	 */
	void setEadDow(Integer eadDow);

	/**
	 * @return the Delivery Date's Day of Week.
	 */
	Integer getDeliveryDow();

	/**
	 * Set the Delivery Date's Day of Week.
	 * @param deliveryDow the Delivery Date's Day of Week.
	 */
	void setDeliveryDow(Integer deliveryDow);

	/**
	 * @return the transit time.
	 */
	Integer getTransitTime();

	/**
	 * Sets the transit time.
	 * @param transitTime the transit time.
	 */
	void setTransitTime(Integer transitTime);

	/**
	 * @return the flag representing whether or not the mail piece is domestic to APO/FPO/DPO or not.
	 */
	Boolean isPrograde();

	/**
	 * Set the flag representing whether or not the mail piece is domestic to APO/FPO/DPO or not.
	 * @param prograde the flag representing whether or not the mail piece is domestic to APO/FPO/DPO or not.
	 */
	void setPrograde(Boolean prograde);

	/**
	 * @return the flag representing whether or not the mail piece is APO/FPO/DPO to domestic or not.
	 */
	Boolean isRetrograde();

	/**
	 * the flag representing whether or not the mail piece is APO/FPO/DPO to domestic or not.
	 * @param retrograde the flag representing whether or not the mail piece is APO/FPO/DPO to domestic or not.
	 */
	void setRetrograde(Boolean retrograde);

	/**
	 * @return the flag indicated whether PME is available for this transaction or not.
	 */
	boolean getNoExpressMail();

	/**
	 * Set the flag indicated whether PME is available for this transaction or not.
	 * @param noExpressMail the flag indicated whether PME is available for this transaction or not.
	 */
	void setNoExpressMail(boolean noExpressMail);

	///////////////////////////////////////////////
	// helper methods for ZIP to int and 5-digit //
	///////////////////////////////////////////////
	/**
	 * @return the Origin ZIP Code as a 5-digit integer.<br/>
	 * Example: if the Origin ZIP Code is {@code "123459998"}, then this method would return {@code 12345}
	 */
	int getOriginZipAsInt();

	/**
	 * @return the Destination ZIP Code as a 5-digit integer.<br/>
	 * Example: if the Destination ZIP Code is {@code "123459998"}, then this method would return {@code 12345}
	 */
	int getDestinationZipAsInt();

	/**
	 * @return the Origin ZIP Code as a 5-character String.<br/>
	 * Example: if the Origin ZIP Code is {@code "123459998"}, then this method would return {@code "12345"}
	 */
	String getOriginZipAs5Digit();

	/**
	 * @return the Destination ZIP Code as a 5-character String.<br/>
	 * Example: if the Destination ZIP Code is {@code "123459998"}, then this method would return {@code "12345"}
	 */
	String getDestinationZipAs5Digit();
	
	/////////////////////////////////////////////////////////////////
	/// Helper methods for checking if a ZIP Code is within a range ///
	/////////////////////////////////////////////////////////////////
	/**
	 * This method checks whether the Origin ZIP Code is within a range of ZIP Codes.
	 * @param lowerBound the lower bound of the ZIP Code range. A {@link String} containing only numeric characters.
	 * @param upperBound the lower bound of the ZIP Code range. A {@link String} containing only numeric characters.
	 * @return {@code true} if <code>lowerBound <= Origin ZIP <= upperBound</code>.<br/>
	 * {@code false} otherwise.
	 */
	boolean isOriginZipWithinRange(String lowerBound, String upperBound);
	
	/**
	 * This method checks whether the Destination ZIP Code is within a range of ZIP Codes.
	 * @param lowerBound the lower bound of the ZIP Code range. A {@link String} containing only numeric characters.
	 * @param upperBound the lower bound of the ZIP Code range. A {@link String} containing only numeric characters.
	 * @return {@code true} if <code>lowerBound <= Destination ZIP <= upperBound</code>.<br/>
	 * {@code false} otherwise.
	 */
	boolean isDestinationZipWithinRange(String lowerBound,
			String upperBound);

	/**
	 * This method checks whether the Origin ZIP Code is not within a range of ZIP Codes.
	 * @param lowerBound the lower bound of the ZIP Code range. A {@link String} containing only numeric characters.
	 * @param upperBound the lower bound of the ZIP Code range. A {@link String} containing only numeric characters.
	 * @return {@code true} if <code>Origin ZIP < lowerBound OR upperBound < Origin ZIP</code>.<br/>
	 * {@code false} otherwise.
	 */
	boolean isOriginZipNotWithinRange(String lowerBound,
			String upperBound);
	
	/**
	 * This method checks whether the Destination ZIP Code is not within a range of ZIP Codes.
	 * @param lowerBound the lower bound of the ZIP Code range. A {@link String} containing only numeric characters.
	 * @param upperBound the lower bound of the ZIP Code range. A {@link String} containing only numeric characters.
	 * @return {@code true} if <code>Destination ZIP &lt lowerBound OR upperBound &lt Destination ZIP</code>.<br/>
	 * {@code false} otherwise.
	 */
	boolean isDestinationZipNotWithinRange(String lowerBound,
			String upperBound);

	/**
	 * Set the flag that represents whether the cut-off time was missed or not.
	 * @param missedCot the flag that represents whether the cut-off time was missed or not.
	 */
	void setMissedCot(boolean missedCot);
	
	/**
	 * @return the flag that represents whether the cut-off time was missed or not.
	 */
	boolean isMissedCot();
	
	/**
	 * Increments the Effective Acceptance Date by the passed in number of days.
	 * @param val how many days to increment the Effective Acceptance Date by.
	 * @throws ParseException 
	 */
	void incrementEad(int val) throws ParseException;

	/**
	 * Increments the Delivery Date by the passed in number of days.
	 * @param val how many days to increment the Delivery Date by.
	 * @throws ParseException 
	 */
	void incrementDeliveryDate(int val) throws ParseException;

	/**
	 * Increments the Transit Time by the passed in number of days.
	 * @param val how many days to increment the Transit Time by.
	 */
	void incrementTransitTime(int val);

	/**
	 * Get the number of days between 2 {@link Date} instances.
	 * @param firstDay The first Date.
	 * @param secondDay The second Date.
	 * @return the absolute value of <code>secondDay - firstDay</code> in days. If either passed in object is {@code null}, then {@code -1} is returned.
	 */
	long getDaysBetweenDates(Date firstDay, Date secondDay);

	/**
	 * Increases the Service Standard of a PME mail piece.
	 * @param amountToIncreaseBy the amount to increment the Service Standard by.
	 */
	void increasePmeServiceStandard(int amountToIncreaseBy);
	
	/**
	 * Increases the Service Standard of a PRI mail piece.
	 * @param amountToIncreaseBy the amount to increment the Service Standard by.
	 */
	void increasePriServiceStandard(int amountToIncreaseBy);
	
	/**
	 * Increases the Service Standard of a mail piece for mail classes that are not PME or PRI.
	 * @param amountToIncreaseBy the amount to increment the Service Standard by.
	 */
	void increaseNonPmeServiceStandard(int amountToIncreaseBy);

	/**
	 * Decreases the Service standard by the value passed in
	 * @param amountToDecreaseBy amount to subtract from the Service Standard
	 */
	 void decreaseServiceStandard(int amountToDecreaseBy);

	/**
	 * Method that adds days to a Date object. 
	 * the passed in date is not modified. Takes crossing a Daylight Savings Time boundary into effect.
	 * @param initialDate the starting date
	 * @param daysToAdd how many days to add
	 * @return initialDays + daysToAdd
	 */
	Date addDaysToDate(Date initialDate, int daysToAdd);

	/**
	 * retrieves what day of week a particular Date object is
	 * @param date the Date to get day of week from
	 * @return {@code int} value of {@link #SUNDAY}-{@link #SATURDAY}
	 */
	int getCalendarDayOfWeekFromDate(Date date);

	/**
	 * Retrieves the day of week a particular Date object is OR
	 * returns {@link #HOLIDAY} if passed is date is a USPS Holiday
	 * @param date the Date to get day of week from
	 * @return {@code int} value of {@link #SUNDAY}-{@link #SATURDAY}
	 *         or {@link #HOLIDAY} if passed in date is a USPS Holiday
	 */
	int getDayOfWeekOrHolidayFromDate(Date date);

	/**
	 * Method that checks if passed in date is a USPS Holiday<br/><br/>
	 * <strong><em>USPS Holiday Date values are located in the ATF_REF_VALUE file with:</em></strong><br/>
	 * REF_CATEGORY - HOLIDAY<br/>
	 * REF_NAME - USPS_HOL<br/><br/>
	 * Format of the dates is yyyy-MM-dd. example: <code>2015-12-25</code>
	 * @param date the Date to check if it is a USPS Holiday.
	 * @return true if the passed in Date is a USPS Holiday. false otherwise
	 */
	boolean isUspsHoliday(Date date);

	/**
	 * Method that checks if passed in date is a Holiday Eve<br/><br/>
	 * <strong><em>Holiday Eves Date values are located in the ATF_REF_VALUE file with:</em></strong><br/>
	 * REF_CATEGORY - HOLIDAY<br/>
	 * REF_NAME - HOL_EVE<br/><br/>
	 * Format of the dates is yyyy-MM-dd. example: <code>2015-12-24</code>
	 * @param date the Date to check if it is a Holiday Eve.
	 * @return true if the passed in Date is a Holiday Eve. false otherwise
	 */
	boolean isHolidayEve(Date date);

	/**
	 * Method that checks if the passed in ZIP is APO/FPO/DPO (Military/Diplomatic)<br/><br/>
	 * <strong><em>APO/FPO/DPO ZIP Code ranges are located in the ATF_REF_VALUE file with:</em></strong><br/>
	 * REF_CATEGORY - APOFPODPO_ZIP_RANGE<br/><br/>
	 * Format of the ZIP ranges is <code>lowerBound-upperBound</code> with lowerBound and upperBound being 5-digit numeric values.
	 * @param zip the ZIP Code to check.
	 * @return true if the passed in ZIP Code is within the any ranges in ATF_REF_VALUE. false otherwise.
	 */
	boolean isApoFpoDpo(String zip);

	/**
	 * Method that checks if the passed in ZIP is PTFAS (Offshore)<br/><br/>
	 * <strong><em>PTFAS ZIP Codes are located in the ATF_REF_VALUE file with:</em></strong><br/>
	 * REF_CATEGORY - PTFAS_ZIPS<br/>
	 * REF_VALUE - PTFAS
	 * @param zip the ZIP Code to check.
	 * @return true if the passed in ZIP Code is equal to any of the PTFAS ZIP Codes in ATF_REF_VALUE. false otherwise.
	 */
	boolean isPtfas(String zip);

	/**
	 * Method that checks if the passed in ZIP is DPO<br/><br/>
	 * <strong><em>DPO ZIP Codes are located in the ATF_REF_VALUE file with:</em></strong><br/>
	 * REF_CATEGORY - DPO_ZIPS<br/>
	 * REF_NAME - DPO
	 * @param zip the ZIP Code to check.
	 * @return true if the passed in ZIP Code is equal to any of the DPO ZIP Codes in ATF_REF_VALUE. false otherwise.
	 */
	boolean isDpo(String zip);

	/**
	 * @return The ISC facility ZIP Code for a mail piece that has an APO/FPO/DPO origin and a domestic destination.
	 */
	String getRetrogradeZip();

	/**
	 * Set the ISC facility ZIP Code for a mail piece that has an APO/FPO/DPO origin and a domestic destination.
	 * @param retrogradeZip the ISC facility ZIP Code
	 */
	void setRetrogradeZip(String retrogradeZip);

	/**
	 * @return The ISC facility ZIP Code for a mail piece that has a domestic origin and an APO/FPO/DPO destination.
	 */
	String getProgradeZip();
	
	/**
	 * Set the ISC facility ZIP Code for a mail piece that has a domestic origin and an APO/FPO/DPO destination.
	 * @param progradeZip the ISC facility ZIP Code
	 */
	void setProgradeZip(String progradeZip);
	
	/**
	 * @return the close time for Monday for the origin facility.
	 */
	int getMonClose();

	/**
	 * Sets the close time for Monday of the origin facility.
	 * @param monClose the time the origin facility closes on a Monday.
	 */
	void setMonClose(int monClose);

	/**
	 * @return the close time for Tuesday for the origin facility.
	 */
	int getTueClose();

	/**
	 * Sets the close time for Tuesday of the origin facility.
	 * @param tueClose the time the origin facility closes on a Tuesday.
	 */
	void setTueClose(int tueClose);
	
	/**
	 * @return the close time for Wednesday for the origin facility.
	 */
	int getWedClose();

	/**
	 * Sets the close time for Wednesday of the origin facility.
	 * @param wedClose the time the origin facility closes on a Wednesday.
	 */
	void setWedClose(int wedClose);
	
	/**
	 * @return the close time for Thursday for the origin facility.
	 */
	int getThuClose();

	/**
	 * Sets the close time for Thursday of the origin facility.
	 * @param thuClose the time the origin facility closes on a Thursday.
	 */
	void setThuClose(int thuClose);
	
	/**
	 * @return the close time for Friday for the origin facility.
	 */
	int getFriClose();

	/**
	 * Sets the close time for Friday of the origin facility.
	 * @param friClose the time the origin facility closes on a Friday.
	 */
	void setFriClose(int friClose);
	
	/**
	 * @return the close time for Saturday for the origin facility.
	 */
	int getSatClose();

	/**
	 * Sets the close time for Saturday of the origin facility.
	 * @param satClose the time the origin facility closes on a Saturday.
	 */
	void setSatClose(int satClose);
	
	/**
	 * @return the close time for Sunday for the origin facility.
	 */
	int getSunClose();

	/**
	 * Sets the close time for Sunday of the origin facility.
	 * @param sunClose the time the origin facility closes on a Sunday.
	 */
	void setSunClose(int sunClose);
	
	/**
	 * @return the close time for Holiday for the origin facility.
	 */
	int getHolClose();

	/**
	 * Sets the close time for Holiday of the origin facility.
	 * @param holClose the time the origin facility closes on a Holiday.
	 */
	void setHolClose(int holClose);

	/**
	 * @return origin close by the effective acceptance date day of week.
	 */
	int getOriginCloseTime();

	/**
	 * @param dow the day of week to retrieve the closing time of the origin facility.
	 * @return the origin facility closed time for the passed in day of week.
	 */
	int getCloseByDow(int dow);

	/**
	 * Sets the guarantee flag to false.
	 */
	void isNotGuaranteed();
}
