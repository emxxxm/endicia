package MainPackage;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class QueryParser {
	public static HashMap<String, String> parseStringForTuples(String queryString) throws InvalidQueryFormatException {
		HashMap<String, String> queryTuples = new HashMap<String, String>();

		// count number of "=" in string
		URI decoder = null;
		try {
			decoder = new URI("/?" + queryString);
		} catch (URISyntaxException e1) {
			throw new InvalidQueryFormatException("Invalid query string syntax");
		}

		queryString = decoder.getQuery();

		if (queryString == null) {
			throw new InvalidQueryFormatException("The query string is null.");
		}
		if (!queryString.contains("&") || !queryString.contains("=")) {
			throw new InvalidQueryFormatException(
					"The query contains at most one parameter. Please refer to documentation for proper format.");
		}

		queryString = queryString.replaceAll("\"", "");
		String[] tuples = queryString.split("&");

		int index;
		for (String tuple : tuples) {
			index = tuple.indexOf("=");
			try {
				queryTuples.put(tuple.substring(0, index).toLowerCase(), tuple.substring(index + 1));
			} catch (Exception e) {
				throw new InvalidQueryFormatException("The Query string's syntax is invalid");
			}
		}

		if (!queryTuples.containsKey(QueryStrings.SHIP_DATE)) {
			queryTuples.put(QueryStrings.SHIP_DATE, DateTimeUtilities.getCurrentUTCDate());
		}

		validateQuery(queryTuples);

		return queryTuples;// getQueryTuplesForNonPME();
	}

	public static HashMap<String, String> getQueryTuplesForNonPME() {
		HashMap<String, String> tuples = getFakeQueryPRITuples();
		return tuples;
	}

	public static void validateQuery(HashMap<String, String> queryTuples) throws InvalidQueryFormatException {
		ArrayList<String> queryParameters;
		boolean hasValidParameters = true;

		if (queryTuples.size() < 6) {
			throw new InvalidQueryFormatException("The query string does not have enough Parameters.");
		}

		queryParameters = QueryStrings.getQueryParameters();

		for (String s : queryParameters) {
			hasValidParameters &= queryTuples.containsKey(s);
		}

		if (!hasValidParameters) {
			throw new InvalidQueryFormatException(
					"The query string has the correct amount of parameters, but some parameter names are incorrect. The following parameters are necessary: originzip, destzip, dropofftime, mailclass, desttype, and an optional date.");
		}

		validateZip(queryTuples.get(QueryStrings.DEST_ZIP));
		validateZip(queryTuples.get(QueryStrings.ORIGIN_ZIP));
		validateDropOffTime(queryTuples.get(QueryStrings.SHIP_TIME));
		validateDate(queryTuples.get(QueryStrings.SHIP_DATE));
		validateMailClass(queryTuples.get(QueryStrings.MAIL_CLASS));
		validateDestType(queryTuples.get(QueryStrings.DEST_TYPE));

		validateNoNullStrings(queryTuples);
	}

	public static boolean isExpress(String mailClass) {
		return mailClass.equals(QueryStrings.MAIL_CLASS_PME);
	}

	private static void validateNoNullStrings(HashMap<String, String> queryTuples) throws InvalidQueryFormatException {

		for (String s : queryTuples.values()) {
			if (s.isEmpty()) {
				throw new InvalidQueryFormatException("No parameters are allowed to be null");
			}
		}

	}

	private static void validateZip(String zip) throws InvalidQueryFormatException {
		try {
			Integer.parseInt(zip);
		} catch (NumberFormatException e) {
			throw new InvalidQueryFormatException(
					"Attempted to parse the ZIP code " + zip + ", but it is in an invalid format");
		}

		if (!(zip.length() == 5 || zip.length() == 9)) {
			throw new InvalidQueryFormatException(
					"Attempted to parse the ZIP code" + zip + ", but it is an improper length.");
		}
	}

	private static void validateDropOffTime(String dropofftime) throws InvalidQueryFormatException {
		int dropOffTimeInt = -1;
		try {
			dropOffTimeInt = Integer.parseInt(dropofftime);
		} catch (NumberFormatException e) {
			throw new InvalidQueryFormatException(
					"Attempted to parse the drop off time " + dropofftime + ", but it is in an invalid format.");
		}

		if (!(dropOffTimeInt >= 0 && dropOffTimeInt <= 2359)) {
			throw new InvalidQueryFormatException("Attempted to parse the drop off time " + dropofftime
					+ ", but it is not within the correct range of values.");
		}
	}

	private static void validateDate(String date) throws InvalidQueryFormatException {
		try {
			DateTimeUtilities.isDateValid(date);
		} catch (ParseException e) {
			throw new InvalidQueryFormatException(
					"Invalid date given. Date must be in the format " + DateTimeUtilities.DATE_FORMAT);
		}
	}

	private static void validateMailClass(String mailclass) throws InvalidQueryFormatException {
		if (!(QueryStrings.getMailClasses().contains(mailclass))) {
			throw new InvalidQueryFormatException(
					"Invalid Mail class given. Please use one of the appropriate mail classes: "
							+ QueryStrings.getMailClasses());
		}
	}

	private static void validateDestType(String desttype) throws InvalidQueryFormatException {
		if (!(QueryStrings.getDestTypes().contains(desttype))) {
			throw new InvalidQueryFormatException(
					"Invalid destination type given. Please use one of the appropriate destination types: "
							+ QueryStrings.getDestTypes());
		}
	}
	
	private static void validateDeliveryOption(String noDeliveryOption) throws InvalidQueryFormatException {
		int testOption = -1;
		try {
			testOption = Integer.parseInt(noDeliveryOption);
		} catch (NumberFormatException e) {
			throw new InvalidQueryFormatException("Attempted to parse the delivery option " + noDeliveryOption 
					+ ", but it is not a valid. Please use an integer in the range 0-7.");
		}
		if (! (testOption >= 0 && testOption <= 7) ) {
			throw new InvalidQueryFormatException("The delivery option is not a valid value. It must be in the range 0-7");
		}	
	}
	
	public static HashMap<String, String> initializeTuples(String originZip, String destZip, String shipDate, String shipTime, String mailClass, String destType, String noDeliveryOption) throws InvalidQueryFormatException {
		HashMap<String, String> tuples = new HashMap<String, String>();
		
		originZip = originZip.replaceAll("\"", "");
		destZip = destZip.replaceAll("\"", "");
		shipDate = shipDate.replaceAll("\"", "");
		mailClass = mailClass.replaceAll("\"", "");
		destType = destType.replaceAll("\"", "");
		noDeliveryOption = noDeliveryOption.replaceAll("\"", "");
		
		validateZip(originZip);
		validateZip(destZip);
		validateDate(shipDate);
		validateDropOffTime(shipTime);
		validateMailClass(mailClass);
		validateDestType(destType);
		validateDeliveryOption(noDeliveryOption);
		
		tuples.put(QueryStrings.ORIGIN_ZIP, originZip);
		tuples.put(QueryStrings.DEST_ZIP, destZip);
		tuples.put(QueryStrings.SHIP_DATE, shipDate);
		tuples.put(QueryStrings.SHIP_TIME, shipTime);
		tuples.put(QueryStrings.MAIL_CLASS, mailClass);
		tuples.put(QueryStrings.DEST_TYPE, destType);
		tuples.put(QueryStrings.NODELIVERY_OPTION, noDeliveryOption);
		
		validateNoNullStrings(tuples);
		
		return tuples;
	}

	public static HashMap<String, String> getFakeQueryTuples() {
		HashMap<String, String> fakeQueryTuples = new HashMap<String, String>();

		fakeQueryTuples.put(QueryStrings.ORIGIN_ZIP, "01609");
		fakeQueryTuples.put(QueryStrings.DEST_ZIP, "90610");
		fakeQueryTuples.put(QueryStrings.SHIP_DATE, DateTimeUtilities.getCurrentUTCDate());
		fakeQueryTuples.put(QueryStrings.SHIP_TIME, "1000");
		fakeQueryTuples.put(QueryStrings.MAIL_CLASS, QueryStrings.MAIL_CLASS_PME);
		fakeQueryTuples.put(QueryStrings.DEST_TYPE, QueryStrings.DESTTYPE_HFPU);
		fakeQueryTuples.put(QueryStrings.EAD, DateTimeUtilities.getCurrentUTCDate());
		fakeQueryTuples.put(QueryStrings.DELIVERY_DATE, DateTimeUtilities.getCurrentUTCDate());
		fakeQueryTuples.put(QueryStrings.NODELIVERY_OPTION, QueryStrings.OPTION_NONE);

		return fakeQueryTuples;
	}

	public static HashMap<String, String> getFakeQueryPRITuples() {
		HashMap<String, String> fakeQueryTuples = new HashMap<String, String>();

		fakeQueryTuples.put(QueryStrings.ORIGIN_ZIP, "32669");
		fakeQueryTuples.put(QueryStrings.DEST_ZIP, "81654");
		fakeQueryTuples.put(QueryStrings.SHIP_DATE, "15-Jan-2016");
		fakeQueryTuples.put(QueryStrings.SHIP_TIME, "1000");
		fakeQueryTuples.put(QueryStrings.MAIL_CLASS, QueryStrings.MAIL_CLASS_PRI);
		fakeQueryTuples.put(QueryStrings.DEST_TYPE, QueryStrings.DESTTYPE_HFPU);
		fakeQueryTuples.put(QueryStrings.EAD, "15-Jan-2016");
		fakeQueryTuples.put(QueryStrings.DELIVERY_DATE, "17-Jan-2016");
		fakeQueryTuples.put(QueryStrings.NODELIVERY_OPTION, QueryStrings.OPTION_NONE);

		return fakeQueryTuples;
	}
	
	public static HashMap<String, String> getFakeQueryPMETuples() {
		HashMap<String, String> fakeQueryTuples = new HashMap<String, String>();

		fakeQueryTuples.put(QueryStrings.ORIGIN_ZIP, "32669");
		fakeQueryTuples.put(QueryStrings.DEST_ZIP, "32669");
		fakeQueryTuples.put(QueryStrings.SHIP_DATE, "01-Feb-2016");
		fakeQueryTuples.put(QueryStrings.SHIP_TIME, "1000");
		fakeQueryTuples.put(QueryStrings.MAIL_CLASS, QueryStrings.MAIL_CLASS_PME);
		fakeQueryTuples.put(QueryStrings.DEST_TYPE, QueryStrings.DESTTYPE_HFPU);
		//fakeQueryTuples.put(QueryStrings, QueryStrings.DESTTYPE_HFPU);
		fakeQueryTuples.put(QueryStrings.EAD, "01-Feb-2016");
		//fakeQueryTuples.put(QueryStrings.DELIVERY_DATE, "17-Jan-2016");
		fakeQueryTuples.put(QueryStrings.NODELIVERY_OPTION, QueryStrings.OPTION_NONE);

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
