package atfImplementation;

import java.util.ArrayList;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Location")
public class Location {
	private String zipCode, facAddress, facState, facCity;

	public Location() {
		throw new WebApplicationException("Called wrong location constructor");
	}
	
	//TODO sort out what fields are needed to output
	public Location(String zip, String facName, String facAddress, String facCity, String facState, String cutOffTime) {
		init(zip, facAddress, facCity, facState);
		//this.cutOffTime = cutOffTime;//TODO needed?
		//this.facName = facName; //TODO what is
	}
	
	public Location(String zip, String facAddress, String facCity, String facState) {
		init(zip, facAddress, facCity, facState);
	//	this.cutOffTime = null;
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
		/*if (cutOffTime != null) {
			locString += "<COT> " + cutOffTime + " </COT>";
		}*/
		locString += "</location>";
		
		return locString;
	}
	
	public static Location getHFPULocation(String HFPUAddress) {
		String[] locationContents = HFPUAddress.split(",");
		Location HFPULoc = new Location(locationContents[0], locationContents[4], locationContents[2], locationContents[1]);
		
		ArrayList<Location> locationList = new ArrayList<Location>();
		locationList.add(HFPULoc);
		
		return HFPULoc;		
	}
	
	public static String printLocationList(ArrayList<Location> locationList) {
		String output = "";
		for(Location r : locationList) {
			output += r.toXMLString();
		}
		return output;
	}
	
	public String toString() {
		return toXMLString();
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getFacAddress() {
		return facAddress;
	}

	public String getFacState() {
		return facState;
	}

	public String getFacCity() {
		return facCity;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setFacAddress(String facAddress) {
		this.facAddress = facAddress;
	}

	public void setFacState(String facState) {
		this.facState = facState;
	}

	public void setFacCity(String facCity) {
		this.facCity = facCity;
	}

}