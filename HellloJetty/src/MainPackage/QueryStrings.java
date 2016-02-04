package MainPackage;

import java.util.ArrayList;

import atfImplementation.CalculationNotPossibleException;

public class QueryStrings {

	public static final String DESTTYPE_STREET_ADDRESS = "1";
	public static final String DESTTYPE_PO_BOX = "2";
	public static final String DESTTYPE_HFPU = "3";
	public static final String HPFU_LOCATIONS = "HFPU";
	
	public static final String MAIL_CLASS_PME = "PME";
	public static final String MAIL_CLASS_PRI = "PRI";
	public static final String MAIL_CLASS_FCM = "FCM";
	public static final String MAIL_CLASS_STD = "STD";
	public static final String MAIL_CLASS_PER = "PER";
	public static final String MAIL_CLASS_PKG = "PKG";
	
	
	public static final String ORIGIN_ZIP = "originzip";
	public static final String DEST_ZIP = "destzip";
	public static final String SHIP_DATE = "shipdate";
	public static final String SHIP_TIME = "dropofftime";
	public static final String MAIL_CLASS = "mailclass";
	public static final String DEST_TYPE = "desttype";
	public static final String EAD = "ead";
	public static final String DELIVERY_DATE = "deliverydate"; //SDD
	public static final String CUTOFF_TIME = "cutofftime";
	public static final String TRANSIT_TIME = "transittime";
	
	public static final String RETROGRADE_ZIP = "retrogradezip";
	public static final String PROGRADE_ZIP = "progradezip";
	
	public static final String DEST_CITY = "destcity";
	public static final String DEST_STATE = "deststate";
	public static final String ORIGIN_CITY = "origincity";
	public static final String ORIGIN_STATE = "originstate";
	public static final String LOCATION = "location";
	public static final String DELIVERY_TIME = "deliverytime";
	public static final String COMMITMENT = "commitment";
	
	public static ArrayList<String> getQueryParameters() {
		ArrayList<String> queryParameters = new ArrayList<String>();
		
		queryParameters.add(ORIGIN_ZIP);
		queryParameters.add(DEST_ZIP);
		queryParameters.add(SHIP_DATE);
		queryParameters.add(SHIP_TIME);
		queryParameters.add(MAIL_CLASS);
		queryParameters.add(DEST_TYPE);
		
		return queryParameters;
	}
	
	public static String destTypeToString(String destType) throws CalculationNotPossibleException {
		String destString;
		switch (destType) {
		case DESTTYPE_STREET_ADDRESS:
			destString = "streetaddress";
			break;
		case DESTTYPE_PO_BOX:
			destString = "pobox";
			break;
		case DESTTYPE_HFPU:
			destString = "hfpu";
			break;
		default:
			throw new CalculationNotPossibleException("destType is invalid");
		} 
		return destString;
	}
	
	public static String mapMailClassToInt(String mailClass) throws CalculationNotPossibleException {
		String mailClassNum;
		System.out.println("Mail class is: " + mailClass);
		switch(mailClass) {
		case MAIL_CLASS_PME:
			mailClassNum = "1";
			break;
		case MAIL_CLASS_PRI:
			mailClassNum = "2";
			break;
		case MAIL_CLASS_FCM:
			mailClassNum = "3";
			break;
		case MAIL_CLASS_STD:
			mailClassNum = "4";
			break;
		case QueryStrings.MAIL_CLASS_PER:
			mailClassNum = "5";
			break;
		case QueryStrings.MAIL_CLASS_PKG:
			mailClassNum = "6";
			break;
		default:
			throw new CalculationNotPossibleException("mail class" + mailClass + "is invalid");
		}
		return mailClassNum;
	}
	
	public static ArrayList<String> getMailClasses() {
		ArrayList<String> mailClasses = new ArrayList<String>();
		
		mailClasses.add(MAIL_CLASS_PER);
		mailClasses.add(MAIL_CLASS_PME);
		mailClasses.add(MAIL_CLASS_FCM);
		mailClasses.add(MAIL_CLASS_STD);
		mailClasses.add(MAIL_CLASS_PKG);
		mailClasses.add(MAIL_CLASS_PRI);
		
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
