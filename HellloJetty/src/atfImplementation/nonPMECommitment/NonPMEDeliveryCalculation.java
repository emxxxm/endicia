package atfImplementation.nonPMECommitment;

import java.text.ParseException;

import java.util.HashMap;
import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import dataHandler.DataMaster;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.RulesObject;
import droolsRules.SDCKnowledgeDTO;

public class NonPMEDeliveryCalculation {
	
	SDCKnowledgeDTO droolsMsg = new SDCKnowledgeDTO();
	RulesObject rules = DataMaster.getInstance().getRulesObject();
	
	//Take in Delivery Date which is initally set in Main Flow
	public NonPMEDeliveryCalculation(HashMap<String, String> q) throws ParseException{
		int closeTime = 0;
		droolsMsg = initializeDroolsMsg(q);
		while(closeTime == 0){
			//[Drools] Execute Rules Engine for Delivery Date Rules 
			rules.insertAndFire(droolsMsg, RulesObject.DROOLS_DELIVERY);
			
			if(QueryParser.isHFPU(q.get(QueryStrings.DEST_TYPE))) {
				int deliveryDOW = DateTimeUtilities.getDayOfWeek(droolsMsg.deliveryDate);
				closeTime = AddressClose.getCloseTimeOnDOWWrapper(deliveryDOW, droolsMsg.destinationZip);

				if(closeTime!=0){
					break;
				}
				else{
					droolsMsg.deliveryDate = DateTimeUtilities.incrementDate(droolsMsg.deliveryDate, 1);
				}	
			}
		}
	}
	public String getDeliveryTime(){
		return droolsMsg.deliveryDate;
	}
	
	public SDCKnowledgeDTO initializeDroolsMsg(HashMap<String, String> q) {
		//TODO actually set these values
		SDCKnowledgeDTO droolsMsg = new SDCKnowledgeDTO();
		droolsMsg = SDCKnowledgeDTO.getFakeDroolsMsg();
		droolsMsg.destinationZip = q.get(QueryStrings.DEST_ZIP);
		//TODO PUT DELIVERYDATE in queryTuples+
		droolsMsg.deliveryDate = q.get(QueryStrings.DELIVERY_DATE);
		droolsMsg.mailClass = q.get(QueryStrings.MAIL_CLASS);
		droolsMsg.noExpressMail = droolsMsg.isNotExpressMail();
		droolsMsg.ead = q.get(QueryStrings.EAD);
		return droolsMsg;
	}
		

}
