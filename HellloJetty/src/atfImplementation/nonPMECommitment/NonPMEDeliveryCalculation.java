package atfImplementation.nonPMECommitment;


import java.text.ParseException;
import java.util.HashMap;
import MainPackage.DateTimeUtilities;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.AddressClose;
import dataHandler.dataFiles.RulesObject;
import droolsRules.SDCKnowledgeDTO;

public class NonPMEDeliveryCalculation {
	
	SDCKnowledgeDTO droolsMsg = new SDCKnowledgeDTO();
	RulesObject rules = DataMaster.getInstance().getRulesObject();
	
	//Take in Delivery Date which is initally set in Main Flow
	public NonPMEDeliveryCalculation(HashMap<String, String> q){
		int closeTime = 0;
		droolsMsg = SDCKnowledgeDTO.getFakeDroolsMsg();
		droolsMsg.destinationZip = "96850";
		//TODO PUT DELIVERYDATE in queryTuples
		droolsMsg.deliveryDate = "17-Jan-2016";
		droolsMsg.mailClass = "PRI";
		droolsMsg.ead = "15-Jan-2016";
		while(closeTime == 0){
			//[Drools] Execute Rules Engine for Delivery Date Rules 
			rules.getSessionList().get(RulesObject.DROOLS_DELIVERY).execute(droolsMsg);
			if(isPO_HFPU()) {
				int deliveryDOW = DateTimeUtilities.getDayOfWeek(droolsMsg.deliveryDate);
				System.out.println("Close Time: " + closeTime);
				closeTime = AddressClose.getCloseTimeOnDOWWrapper(deliveryDOW, droolsMsg.destinationZip);

				if(closeTime!=0){
					break;
				}
				else{
					try {
						droolsMsg.deliveryDate = DateTimeUtilities.incrementDate(droolsMsg.deliveryDate, 1);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
		}
	}
	public String getDeliveryTime(){
		return droolsMsg.deliveryDate;
	}
	
	//TODO decide whether is PO or HFPU
	public boolean isPO_HFPU(){
		return true;
	}

	
		
		

}
