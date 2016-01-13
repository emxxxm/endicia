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
		
		validateQuery(queryTuples);
		
		if (!queryTuples.containsKey("Date")) {
			queryTuples.put("Date", DateTimeUtilities.getCurrentUTCDateTime());
		}
		return queryTuples;
	}

	public static boolean validateQuery(LinkedHashMap<String, String> queryTuples) {
		return true; //TODO make actual validation
	}	
}
