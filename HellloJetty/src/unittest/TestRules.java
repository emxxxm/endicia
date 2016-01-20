package unittest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import dataHandler.dataFiles.RulesObject;
import droolsRules.SDCKnowledgeDTO;

public class TestRules {
	static RulesObject rules;
	static SDCKnowledgeDTO message;
	
	@BeforeClass
	public static void setUp() {
		IDataMaster m = DataMaster.getInstance();
	 	message = new SDCKnowledgeDTO();
		message.deliveryDate = "31-Jan-2020";
		message.ead = "28-Feb-2020";
		message.mailClass = "PME";
		message.transitTime = 3;
		message.deliveryDow = 5;
		message.originZipAs5Digit= "01609";
		message.destinationZipAs5Digit = "94043";
		message.originZip = "01609";
		message.destinationZip = "94043";
		message.noExpressMail = false;
		message.progradeZip = "12345";
		System.out.println("Finished initializing message field values");
//		//Post Processing Drools File
//		message.svcStd;
//		message.acceptDate;
//		message.deliveryTime;
//		//Transit File
//		message.eadDow;
//		message.missedCot;
//		message.destType;
//		//Acceptance file
//		message.acceptTime;
//		message.cutOffTime;
		rules = m.getRulesObject();
		
	}
	
	@Test 
	public void testEmptySDCObject() {
    	try{
    	System.out.println("Before firing rules");
		//rules.getSessionList().get(RulesObject.DROOLS_DELIVERY).insert(message);
		System.out.println("After insert message");
		//rules.getSessionList().get(RulesObject.DROOLS_DELIVERY).fireAllRules();
		rules.getSessionList().get(RulesObject.DROOLS_DELIVERY).execute(message);
		System.out.println("After firing rules");
		message.print();
		System.out.println("After print line");
    	} catch(Exception e) {
    		e.printStackTrace();
    		fail();
    	}
	}

}
