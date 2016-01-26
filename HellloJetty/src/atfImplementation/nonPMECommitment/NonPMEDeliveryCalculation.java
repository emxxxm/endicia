package atfImplementation.nonPMECommitment;

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

public class NonPMEDeliveryCalculation {
	
	SDCKnowledgeDTO droolsMsg;
	
	//Take in Delivery Date which is initally set in Main Flow
	public NonPMEDeliveryCalculation(HashMap<String, String> q) throws ParseException, NumberFormatException, CalculationNotPossibleException{
		int closeTime = 0;
		droolsMsg = SDCKnowledgeDTO.initializeDroolsMsg(q, new SDCKnowledgeDTO()); 
		while(closeTime == 0){
			//[Drools] Execute Rules Engine for Delivery Date Rules 
			
			DataMaster.getInstance().getRulesObject().insertAndFire(droolsMsg, RulesObject.DROOLS_DELIVERY);
			
			if(QueryParser.isHFPU(q.get(QueryStrings.DEST_TYPE))) {
				int deliveryDOW = DateTimeUtilities.getDayOfWeek(droolsMsg.deliveryDate);
				closeTime = AddressClose.getCloseTimeOnDOWWrapper(deliveryDOW, droolsMsg.destinationZip);

				if(closeTime!=0){
					break;
				}
				else{
					droolsMsg.deliveryDate = DateTimeUtilities.incrementDate(droolsMsg.deliveryDate, 1);
				}	
			} else {
				break;
			}
		}
	}
	public String getDeliveryTime(){
		return droolsMsg.deliveryDate;
	}
	
}
