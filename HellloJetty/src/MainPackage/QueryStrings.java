package MainPackage;

import java.util.ArrayList;

public class QueryStrings {

	public static String DESTTYPE_STREET_ADDRESS = "1";
	public static String DESTTYPE_PO_BOX = "2";
	public static String DESTTYPE_HFPU = "3";
	
	public static final String MAIL_CLASS_PME = "PME";
	public static final String MAIL_CLASS_PRI = "PRI";
	public static final String MAIL_CLASS_FCM = "FCM";
	public static final String MAIL_CLASS_STD = "STD";
	public static final String MAIL_CLASS_PER = "PER";
	public static final String MAIL_CLASS_PKG = "PKG";
	
	
	public static String ORIGIN_ZIP = "originzip";
	public static String DEST_ZIP = "destzip";
	public static String DATE = "date";
	public static String DROP_OFF_TIME = "dropofftime";
	public static String MAIL_CLASS = "mailclass";
	public static String DEST_TYPE = "desttype";
	public static String EAD = "ead";
	
	public static ArrayList<String> getQueryParameters() {
		ArrayList<String> queryParameters = new ArrayList<String>();
		
		queryParameters.add(ORIGIN_ZIP);
		queryParameters.add(DEST_ZIP);
		queryParameters.add(DATE);
		queryParameters.add(DROP_OFF_TIME);
		queryParameters.add(MAIL_CLASS);
		queryParameters.add(DEST_TYPE);
		
		return queryParameters;
	}
	
	public static ArrayList<String> getMailClasses() {
		ArrayList<String> mailClasses = new ArrayList<String>();
		
		mailClasses.add(MAIL_CLASS_PER);
		mailClasses.add(MAIL_CLASS_PME);
		mailClasses.add(MAIL_CLASS_FCM);
		mailClasses.add(MAIL_CLASS_STD);
		mailClasses.add(MAIL_CLASS_PER);
		mailClasses.add(MAIL_CLASS_PKG);
		
		return mailClasses;
	}
	
	public static ArrayList<String> getDestTypes() {
		ArrayList<String> destTypes = new ArrayList<String>();
		
		destTypes.add(DESTTYPE_HFPU);
		destTypes.add(DESTTYPE_STREET_ADDRESS);
		destTypes.add(DESTTYPE_PO_BOX);
		
		return destTypes;
	}
	
}
