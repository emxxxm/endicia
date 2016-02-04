package ApacheMain;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryStrings;

@XmlRootElement(name="XMLInput")
public class XMLInput {
	String originZip;
	String destZip;
	String shipDate;
	String shipTime;
	String mailClass;
	String destType;
	
	public XMLInput() {	
	}
	
	
	public String getOriginZip() {
		return originZip;
	}

	public void setOriginZip(String originZip) {
		this.originZip = originZip;
	}

	public void setDestZip(String destZip) {
		this.destZip = destZip;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public void setShipTime(String dropOffTime) {
		this.shipTime = dropOffTime;
	}

	public void setDestType(String destType) {
		this.destType = destType;
	}
	
	public String getDestZip() {
		return destZip;
	}
	
	public String getShipDate() {
		return shipDate;
	}
	
	public String getShipTime() {
		return shipTime;
	}
	
	public String getDestType() {
		return destType;
	}
	
	public String getMailClass() {
		return mailClass;
	}
	
	public void setMailClass(String mailClass) {
		this.mailClass = mailClass;
	}
	
}
