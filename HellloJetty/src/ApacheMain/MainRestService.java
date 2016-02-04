package ApacheMain;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import ApacheMain.outputwrappers.DazzleOutputMain;
import MainPackage.DateTimeUtilities;
import MainPackage.InvalidQueryFormatException;
import MainPackage.LoggingHub;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.MainATFImplementation;

//@Path( "/people" ) 
public class MainRestService {
    @Inject private OutputService outputService;
    MainATFImplementation mainLogic;

	private final static Logger logger = Logger.getLogger(LoggingHub.class.getName());
	
    
    @Path("/people")
    @Produces( { "application/xml"} )
   // @Consumes( {"application/x-www-form-urlencoded"} )
    @GET
    public Collection<DazzleOutputMain> getPeople( @QueryParam( QueryStrings.ORIGIN_ZIP) String originZip, 
    									   @QueryParam(QueryStrings.DEST_ZIP) String destZip,
    									   @QueryParam(QueryStrings.SHIP_DATE) @DefaultValue("") String shipDate,
    									   @QueryParam(QueryStrings.SHIP_TIME) String dropOffTime,
    									   @QueryParam(QueryStrings.MAIL_CLASS) String mailClass, 
    									   @QueryParam(QueryStrings.DEST_TYPE) String destType) throws NumberFormatException, CalculationNotPossibleException, ParseException, InvalidQueryFormatException {
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
    
    @Path("/people.xml")
    @Produces( {"application/xml"} )
    @Consumes("")
    @POST
    public Collection<Output> getPeopleXMLFromForm() {
    	
    	return null;
    }
    
    @Path("/people.xml")
    @Produces( {"application/xml"} )
    @Consumes( {"application/x-www-form-urlencoded"} )
    @POST
    public Collection<DazzleOutput> getPeopleJSONPost( @Multipart(value = "originzip", type = "text/xml") String originZip) {
    	System.out.println(originZip);
    	return null;
    }
    
    @Path("/people.json")
    @Produces( { "application/json"} )
    @GET
    public Collection<DazzleOutputMain> getPeopleJSON(  @QueryParam( QueryStrings.ORIGIN_ZIP) String originZip, 
			   @QueryParam(QueryStrings.DEST_ZIP) String destZip,
			   @QueryParam(QueryStrings.SHIP_DATE) @DefaultValue("") String shipDate,
			   @QueryParam(QueryStrings.SHIP_TIME) String dropOffTime,
			   @QueryParam(QueryStrings.MAIL_CLASS) String mailClass, 
			   @QueryParam(QueryStrings.DEST_TYPE) String destType) throws NumberFormatException, CalculationNotPossibleException, ParseException, InvalidQueryFormatException {
    	if(shipDate.isEmpty()) {
    		shipDate = DateTimeUtilities.getCurrentUTCDate();
    	}
    	HashMap<String, String> queryTuples = QueryParser.initializeTuples(originZip, destZip, shipDate, dropOffTime, mailClass, destType);
    	Collection<DazzleOutputMain> output = outputService.getVerbose(queryTuples);
    	return output;
    }
    
    @Path("/people.xml")
    @Produces( { "application/xml"} )
    @GET
    public Collection< Output > getPeopleXML( @QueryParam( "page") @DefaultValue( "1" ) final int page ) {
        return outputService.getPeople( page, 5 );
    }
    
}