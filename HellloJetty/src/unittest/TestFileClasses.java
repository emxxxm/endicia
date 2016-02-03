package unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;
import org.junit.BeforeClass;
import org.junit.Test;

import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.nonPMECommitment.NonPMEDeliveryCalculation;
import atfImplementation.nonPMECommitment.NonPMEServiceStandard;
import atfImplementation.nonPMECommitment.PRI_COT;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.APOFPODPO;
import dataHandler.dataFiles.PMEDestSchedule;
import dataHandler.dataFiles.PMEDispSchedule;
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
	public void testGetDefaultNonPMCOT() throws CalculationNotPossibleException {
		assertEquals("1700",refVal.getDefaultNonPMCOT(Calendar.MONDAY, QueryStrings.MAIL_CLASS_PKG));
	}
	
	@Test
	public void testGetDefaultPMCOT() {
		assertEquals("1700",refVal.getDefaultPMCOT(Calendar.MONDAY));
	}
	
	@Test
	public void testIsUSPSHoliday() throws ParseException {
		String knownHoliday = "01-Jan-2016";
		assertTrue(refVal.isUspsHoliday(knownHoliday));
	}
	
	@Test
	public void testIsHolidayEve() throws ParseException {
		String knownHolidayEve = "01-Mar-2015";
		assertTrue(refVal.isHolidayEve(knownHolidayEve));
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
	
	/*****************Test PRI_COTsubroutine
	 * @throws ParseException ******************************/
	@Test
	public void testTemporaryPRICOTsubroutine() throws CalculationNotPossibleException, ParseException {
		PRI_COT cot = new PRI_COT(QueryParser.getFakeQueryPRITuples());

		assertEquals("1630", cot.getPRI_COT());
	} 
	
	/*****************Test NonPMEDeliveryCalculationsubroutine
	 * @throws ParseException ******************************/
	@Test 
	public void testNonPMEDeliveryCalculation() throws CalculationNotPossibleException, ParseException{
		HashMap<String, String> q = QueryParser.getFakeQueryPRITuples();
		System.out.println("Dest ZIP" + q.get(QueryStrings.DEST_ZIP));
		NonPMEDeliveryCalculation nonPMEdelivery = new NonPMEDeliveryCalculation(q);
		assertEquals("19-Jan-2016", nonPMEdelivery.getDeliveryTime());
	}
	/*****************Test Get Non PME Service Standard******************************/
	@Test
	public void testGetNonPMEServiceStandard() throws CalculationNotPossibleException {
		NonPMEServiceStandard ssd = new NonPMEServiceStandard(QueryParser.getFakeQueryPRITuples());
		
		assertEquals(3, ssd.getTransitTime());
	} 
	
	/****************Test PME_DISP_SCHEDULE file access***************/
	@Test
	public void testPMEDISPLookup() {
		String originFacID = "RKS", destFacID = "DLH";
		int resultDepart, resultArr, resultPrefferedInd;
		
		PMEDispSchedule disp = DataMaster.getInstance().getPMEDisp();
		HashMap<Integer, Integer> result = disp.getDestFacilityInfo(originFacID, destFacID);
		
		resultDepart = result.get(PMEDispSchedule.DEPART_TIME);
		resultArr = result.get(PMEDispSchedule.ARR_TIME);
		resultPrefferedInd = result.get(PMEDispSchedule.PREFFERED_INDICATOR);
		
		assertEquals(1745, resultDepart);
		assertEquals(815, resultArr);
		assertEquals(1, resultPrefferedInd);
		
		int sundayIndicator = disp.getDOWINDforTransitDateDow("31-Jan-2016");
		assertEquals(0, sundayIndicator);
	}
	
	/***********Test PME_DEST_SCHEDULE data access***************/
	@Test
	public void testPMEDestLookup() {
		String destZip = "53527", destType = QueryStrings.DESTTYPE_HFPU;
		String knownFACID = "535", knownCET = "325", knownRankID = "2", knownDeliveryTime = "1500";
				//"53527","535","325","3","1500","2"
		String resultFACID, resultCET, resultRankID, resultDeliveryTime;
		
		PMEDestSchedule dest = DataMaster.getInstance().getPMEDest();
		HashMap<Integer, String> result = dest.getDestFacilityInfo(destZip, destType);
		
		resultFACID = result.get(PMEDestSchedule.FAC_ID);
		resultCET = result.get(PMEDestSchedule.CET_ID);
		resultRankID = result.get(PMEDestSchedule.RANK_ID);
		resultDeliveryTime = result.get(PMEDestSchedule.DELIVERY_TIME_ID);
		
		assertEquals(knownFACID, resultFACID);
		assertEquals(knownCET, resultCET);
		assertEquals(knownDeliveryTime, resultDeliveryTime);
		assertEquals(knownRankID, resultRankID);
	}

	@Test
	public void testGetTransitTime() throws NumberFormatException, CalculationNotPossibleException{
		HashMap<String, String> fakeQueryTuples = QueryParser.getFakeQueryPRITuples();
		fakeQueryTuples.put(QueryStrings.MAIL_CLASS, QueryStrings.MAIL_CLASS_FCM);
		fakeQueryTuples.put(QueryStrings.SHIP_DATE,"27-Jan-2016");
		fakeQueryTuples.put(QueryStrings.ORIGIN_ZIP, "32666");
		fakeQueryTuples.put(QueryStrings.DEST_ZIP, "32668");
		fakeQueryTuples.put(QueryStrings.DEST_TYPE, "2");
		
		NonPMEServiceStandard ssd = new NonPMEServiceStandard(fakeQueryTuples);
		assertEquals(ssd.getTransitTime(), 1);
	    
	}

}
