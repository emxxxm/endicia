package dataHandler.dataFiles;

import java.util.ArrayList;

import org.apache.commons.csv.CSVRecord;

import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;

public class ServiceStandardAll extends AbsDataFile{
	int tupleID = 0, valueID = 4;
	ArrayList<CSVRecord> ori5Dest5 = new ArrayList<CSVRecord>();
	ArrayList<CSVRecord> ori3Dest5 = new ArrayList<CSVRecord>();;
	ArrayList<CSVRecord> ori5Dest3 =new ArrayList<CSVRecord>();;
	ArrayList<CSVRecord> ori3Dest3 =new ArrayList<CSVRecord>();;
	
	public ServiceStandardAll(){
		for(CSVRecord r: recordsList){
			if(r.get(tupleID).length()==5 && r.get(tupleID + 1).length() == 5){
				ori5Dest5.add(r);
			}
			if(r.get(tupleID).length()==3 && r.get(tupleID + 1).length() == 5){
				ori3Dest5.add(r);
			}
			if(r.get(tupleID).length()==5 && r.get(tupleID + 1).length() == 3){
				ori5Dest3.add(r);
			}
			if(r.get(tupleID).length()==3 && r.get(tupleID + 1).length() == 3){
				ori3Dest3.add(r);
			}
		}
	}
	@Override
	public String getFileName() {
	
		return FilenameConstants.SERVICE_STANDARD_ALL;
	}
	//TODO index and determine ONE or FOUR arraylists
	public String getServiceStandard(String orginZIP, String destZIP, String mailClass) throws CalculationNotPossibleException{
		switch(mailClass){
			case QueryStrings.MAIL_CLASS_PRI:
				valueID = 2;
				break;
			case QueryStrings.MAIL_CLASS_FCM:
				valueID = 3;
				break;
			case QueryStrings.MAIL_CLASS_STD:
				valueID = 4;
				break;
			case QueryStrings.MAIL_CLASS_PER:
				valueID = 5;
				break;
			case QueryStrings.MAIL_CLASS_PKG:
				valueID = 6;
				break;
			default:
				break;
		}
		String service = "";
		//TODO assume input and output are 5 digits
		for(CSVRecord r: ori5Dest5){
			if(r.get(tupleID).equals(orginZIP) &&
					r.get(tupleID+1).equals(destZIP))
				service = r.get(valueID);
		}
		if(service.equals("")){
			for(CSVRecord r: ori3Dest5){
				if(orginZIP.contains(r.get(tupleID)) &&
						r.get(tupleID+1).equals(destZIP))
					service = r.get(valueID);
			}
		}
		if(service.equals("")){
			for(CSVRecord r: ori5Dest3){
				if(r.get(tupleID).equals(orginZIP) &&
						destZIP.contains(r.get(tupleID+1)))
					service = r.get(valueID);
			}
		}
		if(service.equals("")){
			for(CSVRecord r: ori3Dest3){
				if(orginZIP.contains(r.get(tupleID)) &&
						destZIP.contains(r.get(tupleID+1)))
					service = r.get(valueID);
			}
		}
		if(service.equals("")){
			throw new CalculationNotPossibleException("Cannot find corresponding ZIP pairs");
		}
		else return service;
	}
	
	

}
