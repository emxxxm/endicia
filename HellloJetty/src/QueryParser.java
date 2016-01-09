import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class QueryParser {
	
	public static Map<String, String> parseStringForTuples(String queryString) throws UnsupportedEncodingException {
		Map<String, String> query_tuples = new LinkedHashMap<String, String>();
		
		String[] tuples = queryString.split("&");
		
		int index;
		for (String tuple: tuples) {
			index = tuple.indexOf("=");
			query_tuples.put(URLDecoder.decode(tuple.substring(0,index), "UTF-8"),URLDecoder.decode(tuple.substring(index+1), "UTF-8")); 
		}
		
		return query_tuples;
	}
	
	
	
	
}
