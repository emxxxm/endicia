package unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.HFPULocation;
import atfImplementation.PMECommitment.APOFPODPOSubroutine;
import atfImplementation.PMECommitment.PMEDeliveryDate;
import atfImplementation.PMECommitment.BestPMECommitment;
import atfImplementation.PMECommitment.Commitment;
import dataHandler.DataMaster;


public class TestSubroutines {

	static HashMap<String, String> queryTuples;
	String inRangeOriginZip, outOfRangeOriginZip, inRangeDestZip;
	String inRangeDPOZip;

	@Before
	public void setUp() {
		queryTuples = QueryParser.getFakeQueryTuples();		
		inRangeOriginZip = "09470";
		outOfRangeOriginZip = "1";
		
		inRangeDestZip = "09470";
		inRangeDPOZip = "09213";
	}

	@Test
	public void testAPOFPODPOSubroutine() throws CalculationNotPossibleException {
		APOFPODPOSubroutine AFDsub = new APOFPODPOSubroutine(queryTuples); 
		assertEquals(0, AFDsub.getProgradeOffset());
		assertEquals(0, AFDsub.getRetrogradeOffset());
	}

	@Test
	public void testAPOFPODPOWithOriginInRange() throws CalculationNotPossibleException {
		queryTuples.put(QueryStrings.ORIGIN_ZIP, inRangeOriginZip);

		APOFPODPOSubroutine AFDsub = new APOFPODPOSubroutine(queryTuples);
		//Origin Zip should have been updated
		assertFalse(inRangeOriginZip.equals(queryTuples.get(QueryStrings.ORIGIN_ZIP)));
	}
	
	@Test (expected = CalculationNotPossibleException.class)
	public void testCalculationNotPossibleWithInRangeOriginButNoRecords() throws CalculationNotPossibleException {
		queryTuples.put(QueryStrings.MAIL_CLASS, QueryStrings.MAIL_CLASS_PKG);
		queryTuples.put(QueryStrings.ORIGIN_ZIP, inRangeOriginZip);

		APOFPODPOSubroutine AFDsub = new APOFPODPOSubroutine(queryTuples);
	}
	
	@Test 
	public void testInRangeDestAndOrigin() throws CalculationNotPossibleException {
		queryTuples.put(QueryStrings.ORIGIN_ZIP, inRangeOriginZip);
		queryTuples.put(QueryStrings.DEST_ZIP, inRangeOriginZip);

		APOFPODPOSubroutine AFDsub = new APOFPODPOSubroutine(queryTuples);
	}
	
	@Test (expected = CalculationNotPossibleException.class)
	public void testInRangeOriginandDestwithDPOZip() throws CalculationNotPossibleException {
		queryTuples.put(QueryStrings.ORIGIN_ZIP, inRangeOriginZip);
		queryTuples.put(QueryStrings.DEST_ZIP, inRangeDPOZip);

		APOFPODPOSubroutine AFDsub = new APOFPODPOSubroutine(queryTuples);
	}
	
	@Test
	public void testOnlyDestInRangeNotHFPUorPO() throws CalculationNotPossibleException {
		queryTuples.put(QueryStrings.ORIGIN_ZIP, outOfRangeOriginZip);
		queryTuples.put(QueryStrings.DEST_ZIP, inRangeDestZip);
		queryTuples.put(QueryStrings.DEST_TYPE, QueryStrings.DESTTYPE_STREET_ADDRESS);
		
		APOFPODPOSubroutine AFDsub = new APOFPODPOSubroutine(queryTuples);
	}
	
	@Test (expected = CalculationNotPossibleException.class)
	public void testOnlyDestInRangeHFPUorPO() throws CalculationNotPossibleException {
		queryTuples.put(QueryStrings.ORIGIN_ZIP, outOfRangeOriginZip);
		queryTuples.put(QueryStrings.DEST_ZIP, inRangeDestZip);
		queryTuples.put(QueryStrings.DEST_TYPE, QueryStrings.DESTTYPE_HFPU);
		
		System.out.print("herebtw");
		
		APOFPODPOSubroutine AFDsub = new APOFPODPOSubroutine(queryTuples);
	}
	
	@Test //PMEDeliveryDate First IF
	public void testPMEDeliveryDate() throws NumberFormatException, ParseException, CalculationNotPossibleException {
		String originalDatePlusSeven = "16-Feb-2016";
		queryTuples.put(QueryStrings.DELIVERY_DATE, "01-Feb-2016");
		queryTuples.put(QueryStrings.EAD, "09-Feb-2016");
		PMEDeliveryDate pmeDeliv = new PMEDeliveryDate(queryTuples);
		String delivDate = pmeDeliv.getDeliveryDate();
		assertEquals(originalDatePlusSeven,  delivDate);
	}
	
	
	
	@Test
	public void testIncrementSunday() throws ParseException {
		String sundayDate = "07-Feb-2016";
		String incrementSunday = "08-Feb-2016";
		assertEquals(incrementSunday, DateTimeUtilities.incrementDate(sundayDate, 1));	
	}

	@Test
	public void testBestCommitment() throws ParseException{
		ArrayList<Commitment> commits = new ArrayList<Commitment>();
		Commitment c1 = new Commitment(1, 1, 3, 1600, "29-Jan-2016");
		Commitment c2 = new Commitment(1, 1, 3, 1600, "30-Jan-2016");
		Commitment c3 = new Commitment(2, 1, 3, 1600, "29-Jan-2016");
		Commitment c4 = new Commitment(1, 1, 3, 1600, "31-Jan-2016");
		Commitment c5 = new Commitment(1, 1, 3, 1700, "29-Jan-2016");
		commits.add(c1);
		commits.add(c2);
		commits.add(c3);
		commits.add(c4);
		commits.add(c5);
		BestPMECommitment bs = new BestPMECommitment(commits);
		assertEquals(bs.getBestCommitment(), c1);
	}
	
	/*****************Test getHFPU subroutine******************************/
	@Test
	public void testGetHFPU() throws CalculationNotPossibleException {
		HashMap<String, String> fakeQueryTuples = QueryParser.getFakeQueryPRITuples();
		ArrayList<CSVRecord> destRecord = DataMaster.getInstance().getAddressClose().getAddressRecords(fakeQueryTuples.get(QueryStrings.ORIGIN_ZIP),fakeQueryTuples.get(QueryStrings.DEST_ZIP)).get(QueryStrings.DEST_ZIP);
		HFPULocation loc = new HFPULocation(QueryParser.getFakeQueryPRITuples(), destRecord);
		
		assertEquals(loc.getHFPULocation(), "816549001,SNOWMASS,26900 HIGHWAY 82,SNOWMASS,CO");
	}  

}
