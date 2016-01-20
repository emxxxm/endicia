package MainPackage;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class QueryParser {
	public static HashMap<String, String> parseStringForTuples(String queryString) throws InvalidQueryFormatException {
		HashMap<String, String> queryTuples = new HashMap<String, String>();
		
		//count number of "=" in string

		URI decoder = null;
		try {
			decoder = new URI("/?" + queryString);
		} catch (URISyntaxException e1) {
			throw new InvalidQueryFormatException("Invalid query string syntax");
		}
		
		queryString = decoder.getQuery();
		
		if (queryString == null) {
			throw new InvalidQueryFormatException("The query string is null.");
		} if (!queryString.contains("&") || !queryString.contains("=")) {
			throw new InvalidQueryFormatException("The query contains at most one parameter. Please refer to documentation for proper format.");
		}
		
		queryString = queryString.replaceAll("\"", "");
		String[] tuples = queryString.split("&");
		
		int index;
		for (String tuple: tuples) {
			index = tuple.indexOf("=");
			try {
				queryTuples.put(tuple.substring(0,index).toLowerCase(), tuple.substring(index+1));
			} catch (Exception e) {
				throw new InvalidQueryFormatException("The Query string's syntax is invalid");
			}
		}
		
		if (!queryTuples.containsKey(QueryStrings.DATE)) {
			queryTuples.put(QueryStrings.DATE, DateTimeUtilities.getCurrentUTCDate());
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
			throw new InvalidQueryFormatException("The query string does not have enough Parameters.");
		}
		
		queryParameters = QueryStrings.getQueryParameters();
		
		
		for (String s: queryParameters) {
			hasValidParameters &= queryTuples.containsKey(s);
		}
		
		if (!hasValidParameters) {
			throw new InvalidQueryFormatException("The query string has the correct amount of parameters, but some parameter names are incorrect. The following parameters are necessary: originzip, destzip, dropofftime, mailclass, desttype, and an optional date.");
		}
		
		validateZip(queryTuples.get(QueryStrings.DEST_ZIP));
		validateZip(queryTuples.get(QueryStrings.ORIGIN_ZIP));
		validateDropOffTime(queryTuples.get(QueryStrings.DROP_OFF_TIME));
		validateDate(queryTuples.get(QueryStrings.DATE));
		validateMailClass(queryTuples.get(QueryStrings.MAIL_CLASS));
		validateDestType(queryTuples.get(QueryStrings.DEST_TYPE));
		
		validateNoNullStrings(queryTuples);
	}
	
	private static void validateNoNullStrings(HashMap<String, String> queryTuples) throws InvalidQueryFormatException {
		
		for (String s: queryTuples.values()) {
			if (s.isEmpty()) {
				throw new InvalidQueryFormatException("No parameters are allowed to be null");
			}
		}
		
	}

	private static void validateZip(String zip) throws InvalidQueryFormatException {
		try {
			Integer.parseInt(zip);
		} catch (NumberFormatException e) {
			throw new InvalidQueryFormatException("Attempted to parse the ZIP code " + zip + ", but it is in an invalid format");
		}
		
		if ( !(zip.length() == 5 || zip.length() == 9) ) {
			throw new InvalidQueryFormatException("Attempted to parse the ZIP code" + zip + ", but it is an improper length.");
		}
	}
	
	private static void validateDropOffTime(String dropofftime) throws InvalidQueryFormatException {
		int dropOffTimeInt = -1; 
		try {
			dropOffTimeInt = Integer.parseInt(dropofftime);
		} catch (NumberFormatException e) {
			throw new InvalidQueryFormatException("Attempted to parse the drop off time " + dropofftime + ", but it is in an invalid format.");
		}
		
		if ( !(dropOffTimeInt >= 0 && dropOffTimeInt <= 2359) ) {
			throw new InvalidQueryFormatException("Attempted to parse the drop off time " + dropofftime + ", but it is not within the correct range of values.");
		}
	}
	
	private static void validateDate(String date) throws InvalidQueryFormatException {
		try {
			DateTimeUtilities.isDateValid(date);
		} catch (ParseException e) {
			throw new InvalidQueryFormatException("Invalid date given. Date must be in the format" + DateTimeUtilities.DATE_FORMAT);
		}
	}
	
	private static void validateMailClass(String mailclass) throws InvalidQueryFormatException {
		if ( !(QueryStrings.getMailClasses().contains(mailclass)) ) {
			throw new InvalidQueryFormatException("Invalid Mail class given. Please use one of the appropriate mail classes: " + QueryStrings.getMailClasses());
		}
	}
	
	private static void validateDestType(String desttype) throws InvalidQueryFormatException {
		if ( !(QueryStrings.getDestTypes().contains(desttype)) ) {
			throw new InvalidQueryFormatException("Invalid destination type given. Please use one of the appropriate destination types: " + QueryStrings.getDestTypes());
		}
	}
	
	public static HashMap<String, String> getFakeQueryTuples() {
		HashMap<String, String> fakeQueryTuples = new HashMap<String,String>();
		
		fakeQueryTuples.put(QueryStrings.ORIGIN_ZIP, "01609");
		fakeQueryTuples.put(QueryStrings.DEST_ZIP, "90610");
		fakeQueryTuples.put(QueryStrings.DATE, DateTimeUtilities.getCurrentUTCDate());
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
