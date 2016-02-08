package ApacheMain.outputwrappers;

import java.util.HashMap;

import MainPackage.QueryStrings;
import dataHandler.dataFiles.RulesObject;

public abstract class AbsDazzleOutput implements IOutput {
	String originZip;
	String originCity;
	String originState;
	
	String destZip;
	String destCity;
	String destState;
	
	String destType;
	String mailClass;
	
	String cutOffTime;
	String EAD;
	String shipTime;
	String shipDate;
	
	String deliveryDate;
	String svcStdMsg;
	String guarantee;
	
	public AbsDazzleOutput(HashMap<String, Object> output) {
		originZip = (String) output.get(QueryStrings.ORIGIN_ZIP);
		originCity = (String) output.get(QueryStrings.ORIGIN_CITY);
		originState = (String) output.get(QueryStrings.ORIGIN_STATE);
		
		destZip = (String) output.get(QueryStrings.DEST_ZIP);
		destCity = (String) output.get(QueryStrings.DEST_CITY);
		destState = (String) output.get(QueryStrings.DEST_STATE);
		
		destType = (String) output.get(QueryStrings.DEST_TYPE);
		mailClass = (String) output.get(QueryStrings.MAIL_CLASS);
		
		cutOffTime = (String) output.get(QueryStrings.CUTOFF_TIME);
		EAD = (String) output.get(QueryStrings.EAD);
		shipTime = (String) output.get(QueryStrings.SHIP_TIME);
		shipDate = (String) output.get(QueryStrings.SHIP_DATE);
		
		deliveryDate =  (String) output.get(QueryStrings.DELIVERY_DATE);
		guarantee = (String) output.get(RulesObject.GUARANTEE);
		svcStdMsg = (String) output.get(RulesObject.SERVICE_STD_MSG);
	}

	public AbsDazzleOutput() {
	}

	public String getOriginZip() {
		return originZip;
	}

	public String getOriginCity() {
		return originCity;
	}

	public String getOriginState() {
		return originState;
	}

	public String getDestZip() {
		return destZip;
	}

	public String getDestCity() {
		return destCity;
	}

	public String getDestState() {
		return destState;
	}

	public String getDestType() {
		return destType;
	}

	public String getMailClass() {
		return mailClass;
	}

	public String getCutOffTime() {
		return cutOffTime;
	}

	public String getEAD() {
		return EAD;
	}

	public String getShipTime() {
		return shipTime;
	}

	public String getShipDate() {
		return shipDate;
	}

	public String getSvcStdMsg() {
		return svcStdMsg;
	}

	public String getGuarantee() {
		return guarantee;
	}
	
	public String getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setOriginZip(String originZip) {
		this.originZip = originZip;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public void setOriginState(String originState) {
		this.originState = originState;
	}

	public void setDestZip(String destZip) {
		this.destZip = destZip;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public void setDestState(String destState) {
		this.destState = destState;
	}

	public void setDestType(String destType) {
		this.destType = destType;
	}

	public void setMailClass(String mailClass) {
		this.mailClass = mailClass;
	}

	public void setCutOffTime(String cutOffTime) {
		this.cutOffTime = cutOffTime;
	}

	public void setEAD(String eAD) {
		EAD = eAD;
	}

	public void setShipTime(String shipTime) {
		this.shipTime = shipTime;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public void setSvcStdMsg(String svcStdMsg) {
		this.svcStdMsg = svcStdMsg;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

}
