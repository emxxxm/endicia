package MainPackage;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.sun.nio.zipfs.ZipDirectoryStream;

public class QueryParser {
	public static HashMap<String, String> parseStringForTuples(String queryString) throws UnsupportedEncodingException, InvalidQueryFormatException {
		HashMap<String, String> queryTuples = new HashMap<String, String>();
		
		if (queryString == null) {
			throw new InvalidQueryFormatException("The query string is null.");
		} else if (!queryString.contains("&") || !queryString.contains("=")) {
			throw new InvalidQueryFormatException("The query contains at most one parameter. Please refer to documentation for proper format.");
		}
		
		String[] tuples = queryString.split("&");
		
		int index;
		for (String tuple: tuples) {
			index = tuple.indexOf("=");
			queryTuples.put(URLDecoder.decode(tuple.substring(0,index).toLowerCase(), "UTF-8"),URLDecoder.decode(tuple.substring(index+1), "UTF-8")); 
		}
		
		if (!queryTuples.containsKey(QueryStrings.DATE)) {
			queryTuples.put(QueryStrings.DATE, DateTimeUtilities.getCurrentUTCDateTime());
		}
		
		validateQuery(queryTuples);
		
		return getFakeQueryTuples();
	}

	/**
	 * Will have following keys: originzip, destzip, date, dropofftime, mailclass, desttype
	 * @param queryTuples
	 * @return
	 * @throws InvalidQueryFormatException 
	 */
	public static void validateQuery(HashMap<String, String> queryTuples) throws InvalidQueryFormatException {
		ArrayList<String> queryParameters;
		boolean hasValidParameters = true;
		
		if (queryTuples.size() != 6) {
			throw new InvalidQueryFormatException("The query string does not have enough Parameters. You gave " + queryTuples.size() + ". Six are required.");
		}
		
		queryParameters = QueryStrings.getQueryParameters();
		
		
		for (String s: queryParameters) {
			hasValidParameters &= queryTuples.containsKey(s);
		}
		
		if (!hasValidParameters) {
			throw new InvalidQueryFormatException("The query string has the correct amount of parameters, but some parameter names are incorrect. The following parameters are necessary: originzip, destzip, date, dropofftime, mailclass, desttype");
		}
		
		validateZip(queryTuples.get(QueryStrings.DEST_ZIP));
		validateZip(queryTuples.get(QueryStrings.ORIGIN_ZIP));
		
		
		
		
	}
	
	private static void validateZip(String zip) throws InvalidQueryFormatException {
		try {
			Integer.parseInt(zip);
		} catch (NumberFormatException e) {
			throw new InvalidQueryFormatException("Attempted to parse the zipcode " + zip + ", but it is in an invalid format");
		}
		
		if ( !(zip.length() == 5 || zip.length() == 9) ) {
			throw new InvalidQueryFormatException("Attempted to parse the zip code" + zip + ", but it is an improper length.");
		}
	}
	
	private static void validateDropOffTime() {
		//TODO
	}
	
	private static void validateDate() {
		//TODO make a datetime Utility function and update the get date and time function
	}
	
	private static void validateMailClass() {
		//TODO
	}
	
	private static void validateDestType() {
		
	}
	
	public static HashMap<String, String> getFakeQueryTuples() {
		HashMap<String, String> fakeQueryTuples = new HashMap<String,String>();
		
		fakeQueryTuples.put(QueryStrings.ORIGIN_ZIP, "01609");
		fakeQueryTuples.put(QueryStrings.DEST_ZIP, "90610");
		fakeQueryTuples.put(QueryStrings.DATE, DateTimeUtilities.getCurrentUTCDateTime());
		fakeQueryTuples.put(QueryStrings.DROP_OFF_TIME, "1000");
		fakeQueryTuples.put(QueryStrings.MAIL_CLASS, QueryStrings.MAIL_CLASS_PME);
		fakeQueryTuples.put(QueryStrings.DEST_TYPE, QueryStrings.DESTTYPE_HFPU);
		
		return fakeQueryTuples;
	}
	
	public static boolean isPOBox(String destType) {
		return destType.equals(QueryStrings.DESTTYPE_PO_BOX);
	}
	
	public static boolean isHFPU(String destType) {
		return destType.equals(QueryStrings.DESTTYPE_HFPU);
	}
	
	public static boolean isStreetAddress(String destType) {
		return destType.equals(QueryStrings.DESTTYPE_STREET_ADDRESS);
	}
	
}
