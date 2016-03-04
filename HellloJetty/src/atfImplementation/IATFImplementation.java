package atfImplementation;

import java.text.ParseException;
import java.util.LinkedHashMap;

import ApacheMain.outputwrappers.IOutput;

public interface IATFImplementation {

	void execute() throws CalculationNotPossibleException, NumberFormatException, ParseException;
	IOutput formatOutput() throws CalculationNotPossibleException;
	LinkedHashMap<String, Object> getOutput() throws CalculationNotPossibleException;
}
