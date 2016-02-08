package ApacheMain;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import ApacheMain.outputwrappers.DazzleOutputMain;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.MainATFImplementation;

@Service
public class OutputService {
    public Collection< Output > getPeople( int page, int pageSize ) {
    	System.out.println("Getting output");
        Collection< Output > persons = new ArrayList< Output >( pageSize );

       // for( int index = 0; index < pageSize; ++index ) {
     //       persons.add( new Output( String.format( "person+%d@at.com", ( pageSize * ( page - 1 ) + index + 1 ) ) ) );
     //   }

        return persons;
    }
    
    public Collection<DazzleOutputMain> getVerbose(HashMap<String, String> queryTuples) throws CalculationNotPossibleException, NumberFormatException, ParseException {
    	MainATFImplementation mainLogic = new MainATFImplementation(queryTuples);
    	LinkedHashMap<String, Object> output;
   
    	//try {
    	mainLogic.execute();
        output = mainLogic.getOutput();
    	//} catch(Exception e) {
    	//	logger.log(Level.SEVERE, e.getMessage(), e);
    	//	throw new WebApplicationException(e.getMessage());
    	//}
    	
    	Collection<DazzleOutputMain> outputCollection = new ArrayList< DazzleOutputMain >();
    	outputCollection.add(new DazzleOutputMain(output));
    	
    	return outputCollection;
    	
    }
}
