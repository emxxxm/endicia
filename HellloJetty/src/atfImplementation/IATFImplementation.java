package atfImplementation;

import java.text.ParseException;
import java.util.HashMap;

public interface IATFImplementation {

	void execute() throws CalculationNotPossibleException, NumberFormatException, ParseException;
	HashMap<String, String> getOutput();
}
