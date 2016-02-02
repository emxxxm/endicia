package ApacheMain;

import java.util.Collection;
import java.util.HashMap;

import javax.ws.rs.WebApplicationException;

import org.springframework.stereotype.Service;

import atfImplementation.MainATFImplementation;

import java.util.ArrayList;

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
    
    public Collection<DazzleOutput> getVerbose(HashMap<String, String> queryTuples) {
    	MainATFImplementation mainLogic = new MainATFImplementation(queryTuples);
    	HashMap<String, String> output;
    	try {
    		mainLogic.execute();
        	output = mainLogic.getOutput();
    	} catch(Exception e) {
    		throw new WebApplicationException(e.getMessage());
    	}
    	
    	Collection<DazzleOutput> outputCollection = new ArrayList< DazzleOutput >();
    	outputCollection.add(new DazzleOutput(output));
    	
    	return outputCollection;
    	
    }
}
