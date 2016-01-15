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
	 	SDCKnowledgeDTO message = new SDCKnowledgeDTO();
		rules = m.getRulesObject();
	}
	
	@Test //(expected = NumberFormatException.class)
	public void testEmptySDCObject() {
    	try{
		rules.getSessionList().get(RulesObject.DROOLS_TRANSIT).insert(message);
		rules.getSessionList().get(RulesObject.DROOLS_DELIVERY).fireAllRules();
    	} catch(Exception e) {
    		fail();
    		//e.printStackTrace();
    	}
	}

}
