package unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;
import org.junit.BeforeClass;
import org.junit.Test;

import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.PMECommitment.APOFPODPOSubroutine;
import atfImplementation.nonPMECommitment.NonPMEDeliveryCalculation;
import atfImplementation.nonPMECommitment.NonPMEServiceStandard;
import atfImplementation.nonPMECommitment.PRI_COT;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.APOFPODPO;
import dataHandler.dataFiles.RefValue;

public class TestFileClasses{

	static RefValue refVal;
	static APOFPODPO afd;
	static ArrayList<String> holidays;
	static ArrayList<String> range = new ArrayList<String>();
	
	@BeforeClass
	public static void setUp() {
		IDataMaster m = DataMaster.getInstance();
		
		//For testemporaryAPOFPODPOsubroutine
		
		
		
		for (int i = 34000; i <= 34099; i++) {
			range.add(Integer.toString(i));
		}
		
		for (int i = 9000; i <= 9999; i++) {
			range.add(Integer.toString(i));
		}
		
		for (int i = 96200; i <= 96699; i++) {
			range.add(Integer.toString(i));
		}
		
		//For testMilitaryZipRanges
		refVal = m.getRefValue();
		afd = m.getAPOFPODPO();
		holidays = m.getRefValue().getHolidays();
	}

	/*****************Test RefValue Class******************************/
	@Test
	public void testMilitaryZipRanges() {
		assertEquals(3, refVal.getMilitaryZipRanges().size());
	}

	@Test
	public void testSingletonReuseability() {
		assertEquals(3, refVal.getMilitaryZipRanges().size());
	}
	
	@Test
	public void test2() {
		assertEquals(103, holidays.size());
	}
	
	@Test
	public void testGetDPOZips() {
		assertEquals(189, refVal.getDPOZips().size());
	}
	
	@Test
	public void testGetDefaultNonPMCOT() {
		assertEquals("1700",refVal.getDefaultNonPMCOT(Calendar.MONDAY, QueryStrings.MAIL_CLASS_PKG));
	}
	
	@Test
	public void testGetDefaultPMCOT() {
		assertEquals("1700",refVal.getDefaultPMCOT(Calendar.MONDAY));
	}
	

	/*****************Test IDataFile APOFPODPO Class******************************/
	@Test
	public void testAPOFPODPOgetRecords() {
		String stuffedMail = "2";
	    String stuffedZip = "09107";
		ArrayList<CSVRecord> records = afd.getRecords(stuffedMail, stuffedZip);
		
		assertEquals(1, records.size());
	}
	
	
	/*****************Test APOFPODPOsubroutine******************************/
	@Test
	public void testTemporaryAPOFPODPOsubroutine() throws CalculationNotPossibleException {
		HashMap<String, String> q = QueryParser.getFakeQueryTuples();
		
		for (String s: range) {
			assertTrue(refVal.isZipInRange(s));
		}
	}
	
	/*****************Test PRI_COTsubroutine******************************/
	@Test
	public void testTemporaryPRICOTsubroutine() throws CalculationNotPossibleException {
		PRI_COT cot = new PRI_COT(QueryParser.getFakeQueryTuples());
		
		System.out.println(cot.getPRI_COT());
	} 
	
	/*****************Test NonPMEDeliveryCalculationsubroutine******************************/
	@Test 
	public void testNonPMEDeliveryCalculation() throws CalculationNotPossibleException{
		HashMap<String, String> q = QueryParser.getFakeQueryPRITuples();
		NonPMEDeliveryCalculation nonPMEdelivery = new NonPMEDeliveryCalculation(q);
		System.out.println(nonPMEdelivery.getDeliveryTime());
	}
	/*****************Test PRI_COTsubroutine******************************/
	@Test
	public void testGetNonPMEServiceStandard() throws CalculationNotPossibleException {
		NonPMEServiceStandard ssd = new NonPMEServiceStandard(QueryParser.getFakeQueryPRITuples());
		
		System.out.println(ssd.getTransitTime());
	} 
	

}
