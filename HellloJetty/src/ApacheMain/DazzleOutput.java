package ApacheMain;

import java.util.HashMap;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.csv.CSVRecord;

import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.Location;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.RulesObject;

@XmlRootElement(name = "Dazzle")
public class DazzleOutput {
	String originZip;
	String destZip;
	String svcStdMsg;
	
	public DazzleOutput(HashMap<String, String> output) {
		originZip = output.get(QueryStrings.ORIGIN_ZIP);
		destZip = output.get(QueryStrings.DEST_ZIP);
		svcStdMsg = output.get(RulesObject.SERVICE_STD_MSG);
	}
	
	public DazzleOutput() {
		throw new WebApplicationException("unimplemented");
	}

	public String getOriginZip() {
		return originZip;
	}

	public String getDestZip() {
		return destZip;
	}

	public String getSvcStdMsg() {
		return svcStdMsg;
	}

	public void setOriginZip(String originZip) {
		this.originZip = originZip;
	}

	public void setDestZip(String destZip) {
		this.destZip = destZip;
	}

	public void setSvcStdMsg(String svcStdMsg) {
		this.svcStdMsg = svcStdMsg;
	}
	
	
	
//	String svcStdMsg = droolsMsg.svcStdMsg;
//	String guarantee = String.valueOf(droolsMsg.isGuarantee);
//	
//	CSVRecord destRecord = locationRecords.get(QueryStrings.DEST_ZIP).get(0), originRecord = locationRecords.get(QueryStrings.ORIGIN_ZIP).get(0);
//	
//	output.put(QueryStrings.DEST_CITY, AddressClose.getCity(destRecord));
//	output.put(QueryStrings.DEST_STATE, AddressClose.getState(destRecord));
//	output.put(QueryStrings.ORIGIN_CITY, AddressClose.getCity(originRecord));
//	output.put(QueryStrings.ORIGIN_STATE, AddressClose.getState(originRecord));
//	
//	output.put(QueryStrings.DEST_TYPE, queryTuples.get(QueryStrings.DEST_TYPE));
//	if (QueryParser.isHFPU(queryTuples.get(QueryStrings.DEST_TYPE))) {
//		output.put(QueryStrings.destTypeToString(QueryStrings.DESTTYPE_HFPU), Location.printHFPULocation(HFPUAddress));
//	}
//	
//	
//	output.put(QueryStrings.ORIGIN_ZIP, queryTuples.get(QueryStrings.ORIGIN_ZIP));
//	output.put(QueryStrings.DEST_ZIP, queryTuples.get(QueryStrings.DEST_ZIP));
//	output.put(QueryStrings.MAIL_CLASS, queryTuples.get(QueryStrings.MAIL_CLASS));
//	output.put(QueryStrings.EAD, queryTuples.get(QueryStrings.EAD));
//	output.put(QueryStrings.DELIVERY_DATE, queryTuples.get(QueryStrings.DELIVERY_DATE)); //SDD
//	output.put(QueryStrings.SHIP_TIME, queryTuples.get(QueryStrings.SHIP_TIME)); //Accept Time and Ship Time
//	output.put(RulesObject.SERVICE_STD_MSG, svcStdMsg);
//	output.put(RulesObject.GUARANTEE, guarantee);
//	
	
}
