package MainPackage;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;

public class QueryParser {
	public static LinkedHashMap<String, String> parseStringForTuples(String queryString) throws UnsupportedEncodingException, InvalidQueryFormatException {
		LinkedHashMap<String, String> queryTuples = new LinkedHashMap<String, String>();
		
		if (queryString == null) {
			throw new InvalidQueryFormatException("The query string is null.");
		} else if (!queryString.contains("&") || !queryString.contains("=")) {
			throw new InvalidQueryFormatException("The query contains at most one parameter. Please refer to documentation for proper format.");
		}
		
		String[] tuples = queryString.split("&");
		
		int index;
		for (String tuple: tuples) {
			index = tuple.indexOf("=");
			queryTuples.put(URLDecoder.decode(tuple.substring(0,index), "UTF-8"),URLDecoder.decode(tuple.substring(index+1), "UTF-8")); 
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
	public static void validateQuery(LinkedHashMap<String, String> queryTuples) throws InvalidQueryFormatException {
		if (queryTuples.size() != 6) {
			throw new InvalidQueryFormatException("The query string does not have enough Parameters. You gave " + queryTuples.size() + ". Six are required.");
		}
	}
	
	public static LinkedHashMap<String, String> getFakeQueryTuples() {
		LinkedHashMap<String, String> fakeQueryTuples = new LinkedHashMap<String,String>();
		
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
