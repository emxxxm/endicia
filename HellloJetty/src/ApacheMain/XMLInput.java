package ApacheMain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="XMLInput")
public class XMLInput {
	String originZip;
	String destZip;
	String shipDate;
	String shipTime;
	String mailClass;
	String destType;
	String noDeliveryOption;
	
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


	public String getNoDeliveryOption() {
		return noDeliveryOption;
	}


	public void setNoDeliveryOption(String noDeliveryOption) {
		this.noDeliveryOption = noDeliveryOption;
	}
	
}
