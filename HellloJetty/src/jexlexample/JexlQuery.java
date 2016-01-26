package jexlexample;

import MainPackage.QueryStrings;

public class JexlQuery {

	String originZip;
	String destZip;
	String mailClass;
	String location;
	String radius;
	
	public static final String TYPE_MAIL_CALC = "mailCalc";
	public static final String TYPE_HFPU_LOC = "HPFULoc";
	
	public static final String HFPU_ID = "HFPU";
	public static final String MAIL_CLASS_ID = "MailClass";
	public static final String TIME_ID = "time";
	
	public static final String LOC_ID = "LOC";
	public static final String RADIUS_ID = "Radius";
	
	public JexlQuery() {
		location = "CA";
		radius = "5"; //in Miles
		
		originZip = "01609";
		destZip = "90210";
		mailClass = QueryStrings.MAIL_CLASS_PRI;
	}
	
	
	public String getSampleQueryResponseString(String calcType) {
		if (calcType.equals(TYPE_MAIL_CALC)) {
			return getMailQueryString();
		} else if (calcType.equals(TYPE_HFPU_LOC)) {
			return getHPFUQueryString();
		} else {
			return null;
		}
		
	}


	public String getHPFUQueryString() {
		return LOC_ID;
	}


	public String getMailQueryString() {
		return "<Response><StcCommit>" + MAIL_CLASS_ID + "</StcCommit><Transit>" + TIME_ID + "+ 100" + "</Transit></Response>";
	}
	
	public String getMailClass() {
		return this.mailClass;
	}
	
	public String getDestZip() {
		return destZip;
	}
	
	public String getOriginZip() {
		return originZip;
	}
	
	public String getRadius() {
		return radius;
	}
	
	public String getLocation() {
		return location;
	}
}
