package atfImplementation;

public class Location {
	public String zipCode;
	public String facName;
	public String facAddress;
	public String facState;
	public String cutOffTime;

	public Location() {
		
	}

	public String toXMLString() {
		String locString = new String();
		locString += "<Location> " + "\n";
		locString += "<Zip_Code> " + zipCode + " </Zip_Code>" + "\n";
		locString += "<FAC_Name> " + facName + " </FAC_NAME" + "\n";
		locString += "<FAC_State> " + facState + " </FAC_State>" + "\n";
		locString += "<COT> " + cutOffTime + " </COT>" + "\n";
		locString += "</Location>" + "\n";
		
		return locString;
	}

}