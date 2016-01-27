package atfImplementation;

import java.util.ArrayList;

public class Location {
	private String zipCode;
	private String facName;
	private String facAddress;
	private String facState;
	private String cutOffTime;
	private String facCity;

	public Location(String zip, String facName, String facAddress, String facCity, String facState, String cutOffTime) {
		init(zip, facAddress, facCity, facState);
		this.cutOffTime = cutOffTime;
		this.facName = facName;
	}
	
	public Location(String zip, String facAddress, String facCity, String facState) {
		init(zip, facAddress, facCity, facState);
		this.cutOffTime = null;
	}
	
	private void init(String zip, String facAddress, String facCity, String facState) {
		this.zipCode = zip;
		this.facCity = facCity;
		this.facAddress = facAddress;
		this.facState = facState;
	}
	

	private String toXMLString() {
		String locString = "<location>";
		locString += "<Zip_Code> " + zipCode + " </Zip_Code>";
		locString += "<FAC_Address> " + facAddress + " </FAC_Address>";
		locString += "<FAC_State> " + facState + " </FAC_State>";
		locString += "<FAC_City>" + facCity + " </FAC_City>";
		if (cutOffTime != null) {
			locString += "<COT> " + cutOffTime + " </COT>";
		}
		locString += "</location>";
		
		return locString;
	}
	
	public static String printHFPULocation(String HFPUAddress) {
		String[] locationContents = HFPUAddress.split(",");
		
		for (String s: locationContents) {
			System.out.println(s);
		}
		
		Location HFPULoc = new Location(locationContents[0], locationContents[1], locationContents[2], locationContents[4]);
		
		ArrayList<Location> locationList = new ArrayList<Location>();
		locationList.add(HFPULoc);
		
		return printLocationList(locationList);		
	}
	
	public static String printLocationList(ArrayList<Location> locationList) {
		String output = "";
		for(Location r : locationList) {
			output += r.toXMLString();
		}
		return output;
	}

}