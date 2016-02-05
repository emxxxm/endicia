package unittest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import MainPackage.DateTimeUtilities;
import MainPackage.InvalidQueryFormatException;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;

public class TestQueryParsing {
	
	static HashMap<String, String> queryTuples;
	
	@Before
	public void setUp() {
		queryTuples = QueryParser.getFakeQueryTuples();
	}
	
//	@Test //TODO FIX
//	public void testParseTuples() throws InvalidQueryFormatException {
//		HashMap<String, String> result, expected;
//		result = QueryParser.parseStringForTuples("originZip=%2201609%22&destZip=%2290610%22&dropOffTiMe=%221100%22&mailClass=%22PME%22&destType=%223%22");
//		expected = queryTuples;
//		assertEquals(expected, result);
//	}
	
	@Test
	public void testDateTimeFormat() {
		System.out.println(DateTimeUtilities.getCurrentUTCDate());
	}
	
	@Test
	public void testValidNumberOfParametersAndValidNames() {
		try {
			QueryParser.validateQuery(queryTuples);
		} catch (InvalidQueryFormatException e) {
			fail("An exception should not have been thrown for a valid query.");
		}
	}

	@Test
	public void testInvalidNumberOfParameters() {
		queryTuples.remove(QueryStrings.DEST_ZIP);
		queryTuples.remove(QueryStrings.SHIP_DATE);
		queryTuples.remove(QueryStrings.DELIVERY_DATE);
		queryTuples.remove(QueryStrings.ORIGIN_ZIP);
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for a query with an invalid number of parameters");
		} catch (InvalidQueryFormatException e) {
			assertEquals("The query string does not have enough Parameters.", e.getMessage());
		}
	}
	
	@Test
	public void testInvalidParameterName() {
		queryTuples.remove(QueryStrings.ORIGIN_ZIP);
		queryTuples.put("invalidfield", "invalidValue");
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for a query with an invalid parameter name");
		} catch (InvalidQueryFormatException e) {

		}
	}
	
	@Test 
	public void testZipValidation() {
		String zipFormat = "z12";
		String zipLength = "019303";
		String zipLength2 = "1";
		queryTuples.put(QueryStrings.DEST_ZIP, zipFormat);
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an invalid zip format.");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Attempted to parse the ZIP code " + zipFormat + ", but it is in an invalid format", e.getMessage());
		}
		
		queryTuples.put(QueryStrings.DEST_ZIP, zipLength);
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an invalid zip length");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Attempted to parse the ZIP code" + zipLength + ", but it is an improper length.", e.getMessage());
		}
		
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an invalid zip length");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Attempted to parse the ZIP code" + zipLength + ", but it is an improper length.", e.getMessage());
		}
		
	}
	
	@Test
	public void testMailClassValidation() {
		queryTuples.put(QueryStrings.MAIL_CLASS, "invalid");
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an invalid mail class");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Invalid Mail class given. Please use one of the appropriate mail classes: " + QueryStrings.getMailClasses(), e.getMessage());
		}		
	}
	
	@Test
	public void testDestTypeValidation() {
		queryTuples.put(QueryStrings.DEST_TYPE, "invalid");
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an invalid destination type");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Invalid destination type given. Please use one of the appropriate destination types: " + QueryStrings.getDestTypes(), e.getMessage());
		}		
	}
	
	@Test
	public void testDropOffTimeValidation() {
		String lowTime = "-1", highTime = "2400", invalidFormat = "z204";
		
		queryTuples.put(QueryStrings.SHIP_TIME, invalidFormat);
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an invalid formatted drop off time.");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Attempted to parse the drop off time " + invalidFormat + ", but it is in an invalid format." , e.getMessage());
		}
		
		queryTuples.put(QueryStrings.SHIP_TIME, lowTime);
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an out of range drop off time.");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Attempted to parse the drop off time " + lowTime + ", but it is not within the correct range of values.", e.getMessage());
		}
		
		queryTuples.put(QueryStrings.SHIP_TIME, highTime);
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an out of range drop off time.");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Attempted to parse the drop off time " + highTime + ", but it is not within the correct range of values.", e.getMessage());
		}
	}
	
	@Test
	public void testInvalidDate() {
		queryTuples.put(QueryStrings.SHIP_DATE, DateTimeUtilities.DATE_FORMAT);
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an invalid date");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Invalid date given. Date must be in the format " + DateTimeUtilities.DATE_FORMAT, e.getMessage());
		}
		
	}
}
