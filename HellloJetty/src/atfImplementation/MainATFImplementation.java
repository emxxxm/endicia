package atfImplementation;

import java.text.ParseException; 
import java.util.ArrayList;
import java.util.HashMap;

//import org.apache.maven.artifact.resolver.filter.IncludesArtifactFilter;

import MainPackage.QueryStrings;

public class MainATFImplementation implements IATFImplementation {

	ArrayList<String> output = new ArrayList<String>();
	IATFImplementation mainLogic; 
	HashMap<String, String> queryTuples;

	public MainATFImplementation(HashMap<String, String> queryTuples) {
		this.queryTuples = queryTuples;
	}
	
	public void execute() throws CalculationNotPossibleException, NumberFormatException, ParseException {
		if (queryTuples.get(QueryStrings.MAIL_CLASS).equals(QueryStrings.MAIL_CLASS_PME)) {
			mainLogic = new MainPMEImplementation(queryTuples);
		} else {
			mainLogic = new MainNonPMEImplementation(queryTuples);
		}

		mainLogic.execute();
	}

	@Override
	public HashMap<String, String> getOutput() throws CalculationNotPossibleException {
		return mainLogic.getOutput();
	}
}
