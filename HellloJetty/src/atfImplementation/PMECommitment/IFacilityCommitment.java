package atfImplementation.PMECommitment;

import java.text.ParseException;
import java.util.HashMap;

public interface IFacilityCommitment {
	void initValues();

	void incrementValues() throws ParseException, ReturnToMainLogicException;
	
	void setCommitmentValues(HashMap<String,String> queryTuples) throws ParseException, ReturnToMainLogicException;

}
