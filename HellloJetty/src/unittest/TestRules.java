package unittest;

import static org.junit.Assert.*;

import java.text.ParseException;

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
		rules.getSessionList().get(RulesObject.DROOLS_DELIVERY).execute(message);
	}
	
	@Test //This test rule #70104 in DROOLS_DELIVERY
	public void testDroolsDeliveryIncrementDate() throws ParseException {
		message.deliveryDate = "04-Jul-2011";
		message.ead = "07-Jul-2011";
		message.mailClass = QueryStrings.MAIL_CLASS_PRI;
		message.noExpressMail = message.isNotExpressMail();
		rules.getSessionList().get(RulesObject.DROOLS_DELIVERY).execute(message);
		assertTrue(message.deliveryDate.equals("05-Jul-2011"));
	}
	
	@Test 
	public void testDroolsDeliveryIncrementDate2() {
		message.deliveryDate = "04-Jul-2011";
		message.ead = "05-Aug-2011";
		message.mailClass = QueryStrings.MAIL_CLASS_PRI;
		message.noExpressMail = message.isNotExpressMail();
		rules.getSessionList().get(RulesObject.DROOLS_DELIVERY).execute(message);
		assertTrue(message.deliveryDate.equals("04-Jul-2011"));
	}
	
	@Test 
	public void testDroolsTransitRules() {
		rules.getSessionList().get(RulesObject.DROOLS_TRANSIT).execute(message);
	}
	
	@Test 
	public void testDroolsAcceptanceRules() {
		rules.getSessionList().get(RulesObject.DROOLS_ACCEPTANCE).execute(message);
	}
	
	@Test
	public void testDroolsPostProcessingRules() {
		rules.getSessionList().get(RulesObject.DROOLS_POSTPROCESSING).execute(message);
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

}
