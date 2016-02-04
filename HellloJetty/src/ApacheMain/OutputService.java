package ApacheMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;

import org.springframework.stereotype.Service;

import ApacheMain.outputwrappers.DazzleOutputMain;
import MainPackage.LoggingHub;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.MainATFImplementation;

@Service
public class OutputService {
	private final static Logger logger = Logger.getLogger(LoggingHub.class.getName());
	
    public Collection< Output > getPeople( int page, int pageSize ) {
    	System.out.println("Getting output");
        Collection< Output > persons = new ArrayList< Output >( pageSize );

       // for( int index = 0; index < pageSize; ++index ) {
     //       persons.add( new Output( String.format( "person+%d@at.com", ( pageSize * ( page - 1 ) + index + 1 ) ) ) );
     //   }

        return persons;
    }
    
    public Collection<DazzleOutputMain> getVerbose(HashMap<String, String> queryTuples) throws CalculationNotPossibleException {
    	MainATFImplementation mainLogic = new MainATFImplementation(queryTuples);
    	LinkedHashMap<String, Object> output;
   	
    	LoggingHub.initLogger();
    	try {
    		mainLogic.execute();
        	output = mainLogic.getOutput();
    	} catch(Exception e) {
    		logger.log(Level.SEVERE, e.getMessage(), e);
    		throw new WebApplicationException(e.getMessage());
    	}
    	
    	Collection<DazzleOutputMain> outputCollection = new ArrayList< DazzleOutputMain >();
    	outputCollection.add(new DazzleOutputMain(output));
    	
    	return outputCollection;
    	
    }
}
