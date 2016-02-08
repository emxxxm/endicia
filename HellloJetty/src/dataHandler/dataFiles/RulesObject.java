package dataHandler.dataFiles;

import java.util.ArrayList; 
import java.util.HashMap;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

import droolsRules.SDCKnowledgeDTO;

public class RulesObject implements IDataFile {
	public static final String SERVICE_STD_MSG = "svcstdmsg";
	public static final String GUARANTEE = "guarantee";
	private static ArrayList<String> filenames = new ArrayList<String>();
	public static String DROOLS_DELIVERY = "ATF_DROOLS_DELIVERY.drl";
	public static String DROOLS_POSTPROCESSING = "ATF_DROOLS_POSTPROCESSING.drl";
	public static String DROOLS_TRANSIT = "ATF_DROOLS_TRANSIT.drl";
	public static String DROOLS_ACCEPTANCE = "ATF_DROOLS_ACCEPTANCE.drl";
	private static HashMap<String, StatefulKnowledgeSession> sessionList = new HashMap<String, StatefulKnowledgeSession>();
	
	public RulesObject() {
		addFilenames();
		createAllKnowledgeSessions(filenames);
	}
	
	public void addFilenames() {
		filenames.add(DROOLS_DELIVERY);
		filenames.add(DROOLS_POSTPROCESSING);
		filenames.add(DROOLS_TRANSIT);
		filenames.add(DROOLS_ACCEPTANCE);
	}

	public void createAllKnowledgeSessions(ArrayList<String> filenames) {
		for(int i = 0; i < filenames.size(); i++) {
			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			String fileLocation;
			fileLocation = "droolsRules/" + filenames.get(i);
			kbuilder.add(ResourceFactory.newClassPathResource(fileLocation),ResourceType.DRL);
			KnowledgeBuilderErrors errors = kbuilder.getErrors();
			if (errors.size() > 0) {
				for (KnowledgeBuilderError error: errors) {
					System.err.println(error);
				}
				throw new IllegalArgumentException("Could not parse knowledge.");
			}
			KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
    		getSessionList().put(filenames.get(i), kbase.newStatefulKnowledgeSession());
		}
    	//StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
	}

	public HashMap<String, StatefulKnowledgeSession> getSessionList() {
		return sessionList;
	}

	public void insertAndFire(SDCKnowledgeDTO message, String filename) {
		FactHandle handler = getSessionList().get(filename).insert(message);
		getSessionList().get(filename).fireAllRules();
		getSessionList().get(filename).retract(handler);
	}
	
	@Override
	public String getFileName() {
		return null;
	}
}

