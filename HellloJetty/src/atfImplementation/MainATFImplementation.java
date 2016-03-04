package atfImplementation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import ApacheMain.outputwrappers.IOutput;
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
	public LinkedHashMap<String, Object> getOutput() throws CalculationNotPossibleException {
		LinkedHashMap<String, Object> output = mainLogic.getOutput();
		return output;
	}

	@Override
	public IOutput formatOutput() throws CalculationNotPossibleException {
		return this.formatOutput();
	}
}
