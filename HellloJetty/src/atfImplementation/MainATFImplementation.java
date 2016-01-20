package atfImplementation;

import java.util.ArrayList;
import java.util.HashMap;

import MainPackage.QueryStrings;

public class MainATFImplementation  {
	
	ArrayList<String> output = new ArrayList<String>();
	IATFImplementation mainLogic; 
	
	public MainATFImplementation(HashMap<String, String> queryTuples) throws CalculationNotPossibleException {
	
	
	if (queryTuples.get(QueryStrings.MAIL_CLASS).equals(QueryStrings.MAIL_CLASS_PME)) {
		mainLogic = new MainPMEImplementation(queryTuples);
	} else {
		mainLogic = new MainNonPMEImplementation(queryTuples);
	}

	mainLogic.execute();
	
	
		
		
	}
}
