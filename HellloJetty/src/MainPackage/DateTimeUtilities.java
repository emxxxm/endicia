package MainPackage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;

public class DateTimeUtilities {
	
	public static String DATE_FORMAT = "dd-MMM-yyyy";
	public static String TIME_ZONE = "UTC";
	
	public static TimeZone getTimeZone() {
		return TimeZone.getTimeZone("UTC");
	}
	/**
	 * Utility function which computes the Date and Time in UTC with the following format:
	 * "yyyy-mm-dd".
	 * @return "yyyy-mm-dd" 
	 */
	public static String getCurrentUTCDate() {
		String formattedDate;
		Calendar utcCalendar = getCalendar();
		SimpleDateFormat formating = new SimpleDateFormat(DATE_FORMAT);
		
		formating.setTimeZone(getTimeZone());
		
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
		Calendar utcCalendar = Calendar.getInstance(getTimeZone());
		return utcCalendar;
	}
	
	public static String incrementDate(String currentDate, int incrementAmount) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat(DateTimeUtilities.DATE_FORMAT);
		Calendar utcCalendar = getCalendar();
		Date newDate = getDateFromString(currentDate);
		utcCalendar.setTime(newDate);
		utcCalendar.add(Calendar.DATE, incrementAmount);
		newDate = utcCalendar.getTime();
		return formating.format(newDate);
	}
	
	public static int getDaysBetweenDates(String dateString1, String dateString2) throws ParseException {
		Calendar utcCalendar1 = Calendar.getInstance(getTimeZone());
		Calendar utcCalendar2 = Calendar.getInstance(getTimeZone());
		Date date1 = getDateFromString(dateString1);
		Date date2 = getDateFromString(dateString2);
		utcCalendar1.clear();
		utcCalendar2.clear();
		utcCalendar1.setTime(date1);
		utcCalendar2.setTime(date2);
		
		long milliDiff = utcCalendar2.getTimeInMillis() - utcCalendar1.getTimeInMillis();
		int days = (int) (milliDiff / (1000*60*60*24));
		System.out.println("Days: " + days);
		return Math.abs(days);
	}
	
	public static Date getDateFromString(String currentDate) throws ParseException {
		SimpleDateFormat formating = new SimpleDateFormat(DateTimeUtilities.DATE_FORMAT);
		Date newDate = formating.parse(currentDate);
		return newDate;
	}
	
	public static int getDayOfWeek(String date){
		DateFormat format = new SimpleDateFormat(DateTimeUtilities.DATE_FORMAT);
		Date calendarDate = new Date();
		try {
			 calendarDate = format.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendarDate);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
}
