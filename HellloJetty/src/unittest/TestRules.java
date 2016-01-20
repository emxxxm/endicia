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
	 	message = SDCKnowledgeDTO.getFakeDroolsMsg();
		System.out.println("Finished initializing message field values");
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
