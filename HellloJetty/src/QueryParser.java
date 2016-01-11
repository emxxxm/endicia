import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class QueryParser {
	
	public static Map<String, String> parseStringForTuples(String queryString) throws UnsupportedEncodingException, InvalidQueryFormatException {
		Map<String, String> queryTuples = new LinkedHashMap<String, String>();
		
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
			System.out.println("current calendar instance: " + Calendar.getInstance().toString());
			//TODO actually add date into queryString queryTuples.put("Date", Calendar.getInstance())
		}
		return queryTuples;
	}

	public static boolean validateQuery(Map<String, String> queryTuples) {
		return true; //TODO make actual validation
	}	
}
