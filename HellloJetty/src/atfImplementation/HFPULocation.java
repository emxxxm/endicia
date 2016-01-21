package atfImplementation;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import MainPackage.QueryStrings;
import dataHandler.DataMaster;

public class HFPULocation {
	//Input Destination ZIP Code, Mail Class
	String HFPUIndicator;
	String HFPULocation;
	public HFPULocation(HashMap<String, String> queryTuples) throws CalculationNotPossibleException{
		String mailClass = queryTuples.get(QueryStrings.MAIL_CLASS);
		String destZIP = queryTuples.get(QueryStrings.DEST_ZIP);
		if(mailClass.equals(QueryStrings.MAIL_CLASS_PER)|| mailClass.equals(QueryStrings.MAIL_CLASS_STD)){
			throw new CalculationNotPossibleException("The input mailclass does not supoort HFPU");
		}
		else{
			ArrayList<CSVRecord> records = DataMaster.getInstance().getAddressClose().getAddressRecords(destZIP);;
			CSVRecord record = records.get(0);
			if(destZIP.length() == 9){
				if(records.isEmpty()){
					throw new CalculationNotPossibleException("Cannot find destination zip");
				}
				record = records.get(0);
			}
			else{
				for(CSVRecord r: records){
					if(r.get(0).length() == 9){
						record = r;
						break;
					}
				}
			}
			if(mailClass.equals(QueryStrings.MAIL_CLASS_PME)){
				HFPUIndicator = record.get(5);
			}
			else{
				HFPUIndicator = record.get(14);
			}
			if(!HFPUIndicator.equals("")){
				HFPULocation = record.get(1)+"," + record.get(2)+"," + record.get(3)+"," + record.get(4);
			}
		}
	}
	// if the mail is Standard Mail or Periodicals
		//EXCEPTION; STOP-CALCULATION NOT POSSIBLE
	
	//else 
		//if Destination ZIP Code 9-Digits{
			//[DataAccess] if the 9-Digit Destination ZIP Code is NOT in ATF_ADDRESS_CLOSE
			//EXCEPTION; STOP-CALCULATION NOT POSSIBLE}
	
	
		//else{ 
			//[DataAccess]Select a 9-Digit Destination ZIP Code in ATF_ADDRESS_CLOSE}
	
		//if mail class is PME{
			//[DataAccess]Lookup PME HFPU Indicator from ATF_ADDRESS_CLOSE
			//Set HFPU Indicator = PME HFPU Indicator}
		
		//else{
			//[DataAccess]Lookup Non-PME HFPU Indiciator from ATF_ADDRESS_CLOSE
			//set HFPU Indicator = NonPME HFPU Indicator}
		
		//if HFPU Indicator exists{
			//[DataAccess]Lookup HFPU Address from ATF_ADDRESS_CLOSE
			//return HFPU Address;}
		//else
			//Exception; STOP-CALCULATION NOT POSSIBLE
	
	//TODO, this is the output function
	public String getHFPULocation() {
		return "";
	}
}
