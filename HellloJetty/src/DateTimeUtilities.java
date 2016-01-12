import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTimeUtilities {
	private final static Logger logger = Logger.getLogger(Main.class.getName());
	/**
	 * Utility function which computes the Date and Time in UTC with the following format:
	 * "yyyy-mm-ddThh:mm:ssZ", where T and Z are literals within the String.
	 * The T separates the date from the time and the Z indicates the time is UTC.
	 * @return "yyyy-mm-ddThh:mm:ssZ"
	 */
	public static String getCurrentUTCDateTime() {
		String formattedDate;
		TimeZone tz = TimeZone.getTimeZone("UTC");
		Calendar utcCalendar = Calendar.getInstance(tz);
		SimpleDateFormat formating = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		
		formating.setTimeZone(tz);
		
		formattedDate = formating.format(utcCalendar.getTime());
				
		//Strip the unwanted precision from the string
		formattedDate = formattedDate.substring(0,formattedDate.indexOf(".")) + "Z"; 
		
		return formattedDate;
	}
	
	public static String getDateForLogger() {
		String d = getCurrentUTCDateTime();
		d = d.replace(":", "-");
		return d;
	}
	
	public static void logStuff() {
		logger.log(Level.WARNING, "Masterful cross-architecture logging");
	}
}
