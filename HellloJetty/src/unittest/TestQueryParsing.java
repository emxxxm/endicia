package unittest;


import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import MainPackage.InvalidQueryFormatException;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;

public class TestQueryParsing {
	
	static HashMap<String, String> queryTuples;
	
	@Before
	public void setUp() {
		queryTuples = QueryParser.getFakeQueryTuples();
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
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for a query with an invalid number of parameters");
		} catch (InvalidQueryFormatException e) {
			assertEquals("The query string does not have enough Parameters. You gave 5. Six are required.", e.getMessage());
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
			assertEquals("The query string has the correct amount of parameters, but some parameter names are incorrect. The following parameters are necessary: originzip, destzip, date, dropofftime, mailclass, desttype",e.getMessage());
		}
	}
	
	@Test 
	public void testZipParsing() {
		String zipFormat = "z12";
		String zipLength = "019303";
		queryTuples.put(QueryStrings.DEST_ZIP, zipFormat);
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an invalid zip format.");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Attempted to parse the zipcode " + zipFormat + ", but it is in an invalid format", e.getMessage());
		}
		
		queryTuples.put(QueryStrings.DEST_ZIP, zipLength);
		
		try {
			QueryParser.validateQuery(queryTuples);
			fail("An exception should have been thrown for an invalid zip length");
		} catch (InvalidQueryFormatException e) {
			assertEquals("Attempted to parse the zip code" + zipLength + ", but it is an improper length.", e.getMessage());
		}
		
	}
	
}
