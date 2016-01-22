package unittest;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.drools.core.command.runtime.rule.GetRuleRuntimeEventListenersCommand;
import org.junit.BeforeClass;
import org.junit.Test;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.RulesObject;
import droolsRules.SDCKnowledgeDTO;

public class TestRules {
	static RulesObject rules;
	static SDCKnowledgeDTO message;
	
	@BeforeClass
	public static void setUp() {
		IDataMaster m = DataMaster.getInstance();
	 	message = SDCKnowledgeDTO.getFakeDroolsMsg();
		rules = m.getRulesObject();
	}
	
	@Test 
	public void testDroolsDeliveryRules() {
		rules.insertAndFire(message, RulesObject.DROOLS_DELIVERY);
	}
	
	@Test //This test rule #70104 in DROOLS_DELIVERY
	public void testDroolsDeliveryIncrementDate() throws ParseException {
		System.out.println("__________TEST testDroolDeliveryIncrementDate _____________________");
		message.deliveryDate = "04-Jul-2011";
		message.ead = "07-Jul-2011";
		message.mailClass = QueryStrings.MAIL_CLASS_PRI;
		message.noExpressMail = message.isNotExpressMail();
		System.out.println("Delivery Date before " + message.deliveryDate);
		System.out.println("Days Between " +  message.getDaysBetweenDates(message.ead, message.deliveryDate));
		rules.insertAndFire(message, RulesObject.DROOLS_DELIVERY);
		System.out.println("Days Between post fire " + message.getDaysBetweenDates(message.ead, message.deliveryDate));
		System.out.println("Delivery Date after " + message.deliveryDate);
		System.out.println("__________TEST END _____________________");

		assertTrue(message.deliveryDate.equals("05-Jul-2011"));
	}
	
	@Test 
	public void testDroolsDeliveryIncrementDate2() throws ParseException {
		message.deliveryDate = "04-Jul-2011";
		message.ead = "05-Aug-2011";
		message.mailClass = QueryStrings.MAIL_CLASS_PRI;
		message.noExpressMail = message.isNotExpressMail();
		System.out.println("Days Between " + message.getDaysBetweenDates(message.ead, message.deliveryDate));
		rules.insertAndFire(message, RulesObject.DROOLS_DELIVERY);
		System.out.println("DeliveryDate after rules Test2 " + message.deliveryDate );
		assertTrue(message.deliveryDate.equals("04-Jul-2011"));
	}
	
	@Test 
	public void testDroolsTransitRules() {
		rules.insertAndFire(message, RulesObject.DROOLS_TRANSIT);
	}
	
	@Test 
	public void testDroolsAcceptanceRules() {
		rules.insertAndFire(message, RulesObject.DROOLS_ACCEPTANCE);
	}
	
	@Test
	public void testDroolsPostProcessingRules() {
		rules.insertAndFire(message, RulesObject.DROOLS_POSTPROCESSING);
	}
	
	@Test
	public void testMailClassNoExpress() {
		message.mailClass = QueryStrings.MAIL_CLASS_PRI;
		message.noExpressMail = message.isNotExpressMail();
		assertTrue(message.noExpressMail == true);
	}
	
	@Test
	public void testMailClassIsExpress() {
		message.mailClass = QueryStrings.MAIL_CLASS_PME;
		message.noExpressMail = message.isNotExpressMail();
		assertTrue(message.noExpressMail == false);
	}
	
	@Test
	public void testDaysBetweenSameMonth() throws ParseException {
		String date1 = "05-Jan-2015";
		String date2 = "10-Jan-2015";
		int daysBetween = DateTimeUtilities.getDaysBetweenDates(date1, date2);
		assertTrue(daysBetween == 5);
	}
	
	@Test
	public void testDaysBetweenDifferentMonth() throws ParseException {
		String date1 = "01-Jan-2016";
		String date2 = "01-Mar-2016";
		int daysBetween = DateTimeUtilities.getDaysBetweenDates(date1, date2);
		assertTrue(daysBetween == 60);
	}
	
	@Test 
	public void testDaysBetweenDiffYear() throws ParseException {
		String date1 = "31-Dec-2015";
		String date2 = "05-Jan-2016";
		int daysBetween = DateTimeUtilities.getDaysBetweenDates(date1, date2);
		assertTrue(daysBetween == 5);
	}
	
	@Test
	public void testIncrementDate() throws ParseException {
		String date = "01-Jan-2015";
		date = DateTimeUtilities.incrementDate(date, 1);
		assertTrue(date.equals("02-Jan-2015"));
	}
	
	@Test
	public void testIncrementDateByYear() throws ParseException {
		String date = "31-Dec-2015";
		date = DateTimeUtilities.incrementDate(date, 1);
		assertTrue(date.equals("01-Jan-2016"));
	}

	@Test
	public void testPrintSDCObject() {
		message.print();
	}
	
	@Test
	public void testGettersAndSetters() {
		int newTime = 5;
		String newMsg = "testMsg";
		int svcStd = message.svcStd;
		int decInterval = 1;
		
		message.setDeliveryTime(newTime);
		assertEquals(message.deliveryTime, newTime);
		
		message.setTransitTime(newTime);
		assertEquals(newTime, message.getTransitTime());
		message.incrementTransitTime(decInterval);
		assertEquals(newTime + decInterval, message.getTransitTime());
		
		message.setSvcStdMsg(newMsg);
		assertEquals(newMsg, message.svcStdMsg);
		
		message.decreaseServiceStandard(decInterval);
		assertEquals(svcStd - decInterval, message.getSvcStd());
		

		message.increaseNonPmeServiceStandard(decInterval);
		assertEquals(svcStd, message.getSvcStd());
		
		message.increasePmeServiceStandard(decInterval);
		assertEquals(svcStd + decInterval, message.getSvcStd());
		
		message.increasePriServiceStandard(decInterval);
		assertEquals(svcStd + ( 2* decInterval), message.getSvcStd());
	}
	
	@Test
	public void testMethodsWithNoTestableEffect() {
		message.setMissedCot(true);
		message.setEadDow(3);
		message.setNoExpressMail(true);

		message.isNotGuaranteed();
	}
	
	@Test
	public void testDestInRange() {
		String destString = message.destinationZipAs5Digit;
		assertTrue(message.isDestinationZipWithinRange(destString, destString));
	}
	
	@Test
	public void testIncrementEad() throws ParseException {
		String ead = message.ead;
		int decInterval = 1;
		
		message.incrementEad(decInterval);
		assertNotEquals(ead, message.ead);
	}
	
}
