package atfImplementation.PMECommitment;

import java.text.ParseException;
import java.util.HashMap;

import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import dataHandler.DataMaster;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.RulesObject;
import droolsRules.SDCKnowledgeDTO;

public class PMEDeliveryDate {
	//take in initially set in Main Flow, return update Delivery Date
	
	SDCKnowledgeDTO droolsMsg;
	int dow;
	int closeTime=0;
	String retroZip;
	String proZip;
	
	public PMEDeliveryDate(HashMap<String, String> queryTuples) throws ParseException, NumberFormatException, CalculationNotPossibleException{
		while(true){
			
			//[Drools] Execute Rules Engine for Delivery Date Rules
			droolsMsg = SDCKnowledgeDTO.initializeDroolsMsg(queryTuples, new SDCKnowledgeDTO()); 
			DataMaster.getInstance().getRulesObject().insertAndFire(droolsMsg, RulesObject.DROOLS_DELIVERY);
			
			if(DateTimeUtilities.getDaysBetweenDates(droolsMsg.deliveryDate, droolsMsg.ead) > 7) {
				droolsMsg.deliveryDate = DateTimeUtilities.incrementDate(droolsMsg.ead, 7);
				return;
			}
			else {
				dow = DateTimeUtilities.getDayOfWeek(droolsMsg.deliveryDate);
				if(QueryParser.isHFPU(queryTuples.get(QueryStrings.DEST_TYPE))) {
					if(DateTimeUtilities.getUSPSHolidays().contains(droolsMsg.deliveryDate)) {
						dow = 8;
					}
					closeTime = AddressClose.getCloseTimeOnDOWWrapper(dow, droolsMsg.destinationZip);
					if(closeTime == 0) {
						droolsMsg.deliveryDate = DateTimeUtilities.incrementDate(droolsMsg.deliveryDate, 1);
						continue;
					}
					retroZip = queryTuples.get(QueryStrings.RETROGRADE_ZIP);
					
					if(retroZip != null) {
						queryTuples.put(QueryStrings.ORIGIN_ZIP, retroZip);
					}
					else if(proZip != null) {
						queryTuples.put(QueryStrings.DEST_ZIP, proZip);
					}
					return;
				}
				else {
					if(dow == 6 || dow == 7) {
						if(isNoDeliverySelected(dow)){
							droolsMsg.deliveryDate = DateTimeUtilities.incrementDate(droolsMsg.deliveryDate, 1);
							continue;
						}
					}
					else if(DateTimeUtilities.getUSPSHolidays().contains(droolsMsg.deliveryDate)) {
						if(isNoDeliverySelected(dow)) {
							droolsMsg.deliveryDate = DateTimeUtilities.incrementDate(droolsMsg.deliveryDate, 1);
							continue;
						}
						else {
							dow = 8;
						}
					}
					if(queryTuples.get(QueryStrings.DEST_TYPE) == QueryStrings.DESTTYPE_PO_BOX) {
						DataMaster.getInstance().getAddressClose();
						closeTime = AddressClose.getCloseTimeOnDOWWrapper(dow, queryTuples.get(QueryStrings.DEST_ZIP));
						if(closeTime == 0){
							droolsMsg.deliveryDate = DateTimeUtilities.incrementDate(droolsMsg.deliveryDate, 1);
							continue;
						}
						if(retroZip != null) {
							queryTuples.put(QueryStrings.ORIGIN_ZIP, retroZip);
						}
						else if(proZip != null) {
							queryTuples.put(QueryStrings.DEST_ZIP, proZip);
						}	
						return;
					}
				}
			}
		}
	}
			
			//if(Delivery Date > 7 Days after the EAD){
					//Set Delivery Date = EAD + 7 Days
					//return;}	
			
			//else{
				//Set DOW = Delivery date DOW
				//if(Destination == HFPU){
					//if(Delivery Date is a holiday)
					//Set DOW = 8
					
					//[DataAccess] Lookup Dow Close Time from [ATF_ADDRESS_CLOSE]
					
					//if(Close Time = 0){
						//Set Delivery Date = Delivery Date + 1
						//continue;
					//}

					//if Retrograde ZIP is set 
						//Origin ZIP = Retrograde ZIP
					
					//else if(Prograde ZIP set)
						//Destination ZIP = Prograde ZIP
					  
					//return;
				//} 
			
				//else{
					//if(DOW==Saturday or Sunday){
						//if(User select "No Delivery" on this DOW){
							//Delivery Date = Delivery Date + 1
							//continue;
						//}
					//}
				
					//else if(Delivery Date is a holiday){
						//if(User select "No Delivery" on Holidays){
							//Set Delivery date = Delivery Date + 1
							//continue;
							//}
						//else DOW = 8;
					//}
			
					//if(Destination is PO Box){ 	
						//Lookup DOW Close Time from ATF_ADDRESS_CLOSE
						//if(Close Time = 0){
							//Set Delivery Date = Delivery Date + 1
							//continue;
						//}

						//if Retrograde ZIP is set 
							//Origin ZIP = Retrograde ZIP
						
						//else if(Prograde ZIP set)
							//Destination ZIP = Prograde ZIP
						//return;
					//}
				//}
			//}
	
	public String getDeliveryDate() {
		return droolsMsg.deliveryDate;
	}
	
	//TODO actually implement
	public boolean isNoDeliverySelected(int dow) {
		return false;
	}
	
			
}
