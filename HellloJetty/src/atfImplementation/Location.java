package atfImplementation;

import java.util.ArrayList;

public class Location {
	private String zipCode;
	private String facName;
	private String facAddress;
	private String facState;
	private String cutOffTime;

	public Location(String zip, String facName, String facAddress, String facCity, String facState, String cutOffTime) {
		zipCode = zip;
		this.facName = facName;
		this.facAddress = facAddress;
		this.facState = facState;
		this.cutOffTime = cutOffTime;
	}

	public String toXMLString() {
		String locString = new String();
		locString += "<location> ";
		locString += "<Zip_Code> " + zipCode + " </Zip_Code>";
		locString += "<FAC_Name> " + facName + " </FAC_Name>";
		locString += "<FAC_Address> " + facAddress + " </FAC_Address>";
		locString += "<FAC_State> " + facState + " </FAC_State>";
		locString += "<COT> " + cutOffTime + " </COT>";
		locString += "</location>";
		
		return locString;
	}
	
	public static String printLocationList(ArrayList<Location> locationList) {
		String output = "";
		for(Location r : locationList) {
			output += r.toXMLString();
		}
		return output;
	}

}