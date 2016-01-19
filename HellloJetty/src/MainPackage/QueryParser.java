package MainPackage;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;

public class QueryParser {
	public static String DESTTYPE_STREET_ADDRESS = "1";
	public static String DESTTYPE_PO_BOX = "2";
	public static String DESTTYPE_HFPU = "3";
	
	public static String MAIL_CLASS_PME = "PME";
	public static String MAIL_CLASS_PRI = "PRI";
	
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
		
		validateQuery(queryTuples);
		
		if (!queryTuples.containsKey("date")) {
			queryTuples.put("date", DateTimeUtilities.getCurrentUTCDateTime());
		}
		
		return getFakeQueryTuples();
	}

	/**
	 * Will have following keys: originZip, destZip, date, dropOffTime, mailClass, destType
	 * @param queryTuples
	 * @return
	 */
	public static boolean validateQuery(LinkedHashMap<String, String> queryTuples) {
		return true; //TODO make actual validation
	}
	
	public static LinkedHashMap<String, String> getFakeQueryTuples() {
		LinkedHashMap<String, String> fakeQueryTuples = new LinkedHashMap<String,String>();
		
		fakeQueryTuples.put("originZip", "01609");
		fakeQueryTuples.put("destZip", "90610");
		fakeQueryTuples.put("date", DateTimeUtilities.getCurrentUTCDateTime());
		fakeQueryTuples.put("dropOffTime", "1000");
		fakeQueryTuples.put("mailClass", MAIL_CLASS_PME);
		fakeQueryTuples.put("destType", DESTTYPE_HFPU);
		
		return fakeQueryTuples;
	}
	
	public static boolean isPOBox(String destType) {
		return destType.equals(DESTTYPE_PO_BOX);
	}
	
	public static boolean isHFPU(String destType) {
		return destType.equals(DESTTYPE_HFPU);
	}
	
	public static boolean isStreetAddress(String destType) {
		return destType.equals(DESTTYPE_STREET_ADDRESS);
	}
	
}
