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
import org.drools.runtime.StatelessKnowledgeSession;

public class RulesObject implements IDataFile {
	private static ArrayList<String> filenames = new ArrayList<String>();
	public static String DROOLS_DELIVERY = "ATF_DROOLS_DELIVERY.drl";
	public static String DROOLS_POSTPROCESSING = "ATF_DROOLS_POSTPROCESSING.drl";
	public static String DROOLS_TRANSIT = "ATF_DROOLS_TRANSIT.drl";
	public static String DROOLS_ACCEPTANCE = "ATF_DROOLS_ACCEPTANCE.drl";
	private static HashMap<String, StatelessKnowledgeSession> sessionList = new HashMap<String, StatelessKnowledgeSession>();
	
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
			//System.out.println(fileLocation);
			kbuilder.add(ResourceFactory.newClassPathResource(fileLocation), ResourceType.DRL);
			KnowledgeBuilderErrors errors = kbuilder.getErrors();
			if (errors.size() > 0) {
				for (KnowledgeBuilderError error: errors) {
					System.err.println(error);
				}
				throw new IllegalArgumentException("Could not parse knowledge.");
			}
			KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
    		getSessionList().put(filenames.get(i), kbase.newStatelessKnowledgeSession());
		}
    	//StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
	}

	public HashMap<String, StatelessKnowledgeSession> getSessionList() {
		return sessionList;
	}

	@Override
	public String getFileName() {
		return null;
	}
}

