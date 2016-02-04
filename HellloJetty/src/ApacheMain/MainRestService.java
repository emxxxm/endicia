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
import javax.xml.bind.annotation.XmlElement;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import ApacheMain.outputwrappers.DazzleOutputMain;
import MainPackage.DateTimeUtilities;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.Location;
import atfImplementation.MainATFImplementation;

//@Path( "/people" ) 
public class MainRestService {
    @Inject private OutputService outputService;
    MainATFImplementation mainLogic;
    
    @Path("/people")
    @Produces( { "application/xml"} )
   // @Consumes( {"application/x-www-form-urlencoded"} )
    @GET
    public Collection<DazzleOutputMain> getPeople( @QueryParam( QueryStrings.ORIGIN_ZIP) String originZip, 
    									   @QueryParam(QueryStrings.DEST_ZIP) String destZip,
    									   @QueryParam(QueryStrings.SHIP_DATE) @DefaultValue("") String shipDate,
    									   @QueryParam(QueryStrings.SHIP_TIME) String dropOffTime,
    									   @QueryParam(QueryStrings.MAIL_CLASS) String mailClass, 
    									   @QueryParam(QueryStrings.DEST_TYPE) String destType) throws NumberFormatException, CalculationNotPossibleException, ParseException {
    	if(shipDate.isEmpty()) {
    		shipDate = DateTimeUtilities.getCurrentUTCDate();
    	}
    	HashMap<String, String> queryTuples = QueryParser.initializeTuples(originZip, destZip, shipDate, dropOffTime, mailClass, destType);
        return outputService.getVerbose(queryTuples);
    }
    
    //Works for post body if in the form originzip=11111&destzip=22222 IF CONTENT_TYPE = form-urlencoded
    @Path("/people.xml")
    @Produces( {"application/xml"} )
    @Consumes({"application/xml","application/json","application/x-www-form-urlencoded"})
    @POST
    public Collection<Output> getPeopleXMLFromForm(@FormParam("originzip") String originZip, @FormParam("destzip") String destZip) {
    	System.out.println(originZip);
    	System.out.println(destZip);
    	return null;
    }
    
    @Path("/people.json")
    @Produces( {"application/json"} )
    @Consumes( {"application/xml"} )
    @POST
    public Collection<DazzleOutput> getPeopleJSONPost(Location input) {
    	System.out.println("Inside the POST xml body path");
    	System.out.println(input.getFacCity());
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
			   @QueryParam(QueryStrings.DEST_TYPE) String destType) throws NumberFormatException, CalculationNotPossibleException, ParseException {
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