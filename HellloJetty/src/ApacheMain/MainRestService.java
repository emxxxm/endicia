package ApacheMain;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ApacheMain.outputwrappers.DazzlePMEOutput;
import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.MainATFImplementation;

//@Path( "/people" ) 
public class MainRestService {
    @Inject private OutputService outputService;
    MainATFImplementation mainLogic;
    
    @Path("/people")
    @Produces( { "application/xml"} )
   // @Consumes( {"application/x-www-form-urlencoded"} )
    @GET
    public Collection< DazzlePMEOutput > getPeople( @QueryParam( QueryStrings.ORIGIN_ZIP) String originZip, 
    									   @QueryParam(QueryStrings.DEST_ZIP) String destZip,
    									   @QueryParam(QueryStrings.SHIP_DATE) @DefaultValue("") String shipDate,
    									   @QueryParam(QueryStrings.SHIP_TIME) String dropOffTime,
    									   @QueryParam(QueryStrings.MAIL_CLASS) String mailClass, 
    									   @QueryParam(QueryStrings.DEST_TYPE) String destType) throws NumberFormatException, CalculationNotPossibleException, ParseException {
    	if(shipDate.isEmpty()) {
    		shipDate = DateTimeUtilities.getCurrentUTCDate();
    	}
    	
    	HashMap<String, String> queryTuples = QueryParser.initializeTuples(originZip, destZip, shipDate, dropOffTime, mailClass, destType);
   
        return outputService.getVerbose(queryTuples);//peopleService.getPeople( 1, 5 );
    }
    
    @Path("/people.xml")
    @Produces( {"application/xml"} )
    @Consumes("")
    @POST
    public Collection<Output> getPeopleXMLFromForm() {
    	
    	return null;
    }
    
    @Path("/people.json")
    @Produces( { "application/json"} )
    @GET
    public Collection<Output> getPeopleJSON( @QueryParam( "page") @DefaultValue( "1" ) final int page ) {
        return outputService.getPeople( page, 5 );
    }
    
    @Path("/people.xml")
    @Produces( { "application/xml"} )
    @GET
    public Collection< Output > getPeopleXML( @QueryParam( "page") @DefaultValue( "1" ) final int page ) {
        return outputService.getPeople( page, 5 );
    }
    
}