package unittest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.PMECommitment.APOFPODPOSubroutine;
import atfImplementation.PMECommitment.PMEDeliveryDate;

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
		queryTuples.put(QueryStrings.DELIVERY_DATE, "01-Jan-2016");
		queryTuples.put(QueryStrings.EAD, "09-Jan-2016");
		PMEDeliveryDate pmeDeliv = new PMEDeliveryDate(queryTuples);
		String delivDate = pmeDeliv.getDeliveryDate();
		assertEquals(delivDate, (DateTimeUtilities.incrementDate(queryTuples.get(QueryStrings.EAD), 7)));
	}
	
	@Test //PMEDELIV Else
	public void testPMEDelivDateElse() {
		
	}
}
