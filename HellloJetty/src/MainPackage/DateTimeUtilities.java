package MainPackage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;

public class DateTimeUtilities {
	
	public static String DATE_FORMAT = "yyyy-MMM-dd";
	/**
	 * Utility function which computes the Date and Time in UTC with the following format:
	 * "yyyy-mm-dd".
	 * @return "yyyy-mm-dd" 
	 */
	public static String getCurrentUTCDate() {
		String formattedDate;

		TimeZone tz = TimeZone.getTimeZone("UTC");
		Calendar utcCalendar = getCalendar();
		SimpleDateFormat formating = new SimpleDateFormat(DATE_FORMAT);
		
		formating.setTimeZone(tz);
		
		formattedDate = formating.format(utcCalendar.getTime());
		
		return formattedDate;
	}
	
	public static void isDateValid(String dateToValidate) throws ParseException {
		isDateValid(dateToValidate, DATE_FORMAT);
	}
	
	public static void isDateValid(String dateToValidate, String dateFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
	
		sdf.parse(dateToValidate);
	}
	
	public static Calendar getCalendar() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		Calendar utcCalendar = Calendar.getInstance(tz);
		
		return utcCalendar;
	}
}
