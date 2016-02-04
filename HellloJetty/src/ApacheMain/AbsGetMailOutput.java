package ApacheMain;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import ApacheMain.outputwrappers.DazzleOutputMain;
import MainPackage.DateTimeUtilities;
import MainPackage.InvalidQueryFormatException;
import MainPackage.LoggingHub;
import MainPackage.QueryParser;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.MainATFImplementation;

public class AbsGetMailOutput {
	@Inject protected OutputService outputService;
	MainATFImplementation mainLogic;
	private final static Logger logger = Logger.getLogger(LoggingHub.class.getName());

	public Collection<DazzleOutputMain> absGetMail( String originZip, String destZip, String shipDate, String dropOffTime, String mailClass, String destType) throws NumberFormatException, CalculationNotPossibleException, ParseException, InvalidQueryFormatException {
		Collection<DazzleOutputMain> output = null;
		if(shipDate.isEmpty()) {
			shipDate = DateTimeUtilities.getCurrentUTCDate();
		}
		try {
			HashMap<String, String> queryTuples = QueryParser.initializeTuples(originZip, destZip, shipDate, dropOffTime, mailClass, destType);
			output = outputService.getVerbose(queryTuples);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new WebApplicationException(e.getMessage());
		}
		return output;
	}
	
}
