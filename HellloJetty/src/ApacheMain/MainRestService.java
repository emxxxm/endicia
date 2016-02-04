package ApacheMain;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ApacheMain.outputwrappers.DazzleOutputMain;
import MainPackage.DateTimeUtilities;
import MainPackage.InvalidQueryFormatException;
import MainPackage.LoggingHub;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.Location;

//@Path( "/people" ) 
public class MainRestService extends AbsGetMailOutput {

	public static final String API = "v1";
	public static final String BASE_CONTEXT = "/webtools/";
	public static final String BASE_PATH = "/mail";
	
	public static final String APPLICATION = "application/";
	
	public static final String JSON = "json";
	public static final String URL_JSON = "." + JSON;
	public static final String XML = "xml";
	public static final String URL_XML = "." + XML;

	private final static Logger logger = Logger.getLogger(LoggingHub.class.getName());

	@Path(BASE_PATH)
	@Produces( {APPLICATION + XML} )
	// @Consumes( {"application/x-www-form-urlencoded"} )
	@GET
	public Collection<DazzleOutputMain> getDefaultMailOutput( @QueryParam( QueryStrings.ORIGIN_ZIP) String originZip, 
			@QueryParam(QueryStrings.DEST_ZIP) String destZip,
			@QueryParam(QueryStrings.SHIP_DATE) @DefaultValue("") String shipDate,
			@QueryParam(QueryStrings.SHIP_TIME) String dropOffTime,
			@QueryParam(QueryStrings.MAIL_CLASS) String mailClass, 
			@QueryParam(QueryStrings.DEST_TYPE) String destType) throws NumberFormatException, CalculationNotPossibleException, ParseException, InvalidQueryFormatException {
		return absGetMail(originZip, destZip, shipDate, dropOffTime, mailClass, destType);
	}

	@Path(BASE_PATH + URL_JSON)
	@Produces( {APPLICATION + JSON} )
	@GET
	public Collection<DazzleOutputMain> getMailJSON(  @QueryParam( QueryStrings.ORIGIN_ZIP) String originZip, 
			@QueryParam(QueryStrings.DEST_ZIP) String destZip,
			@QueryParam(QueryStrings.SHIP_DATE) @DefaultValue("") String shipDate,
			@QueryParam(QueryStrings.SHIP_TIME) String dropOffTime,
			@QueryParam(QueryStrings.MAIL_CLASS) String mailClass, 
			@QueryParam(QueryStrings.DEST_TYPE) String destType) throws NumberFormatException, CalculationNotPossibleException, ParseException, InvalidQueryFormatException {
		return absGetMail(originZip, destZip, shipDate, dropOffTime, mailClass, destType);
	}

	@Path(BASE_PATH + URL_XML)
	@Produces( {APPLICATION + XML} )
	@GET
	public Collection<DazzleOutputMain> getMailXML(  @QueryParam( QueryStrings.ORIGIN_ZIP) String originZip, 
			@QueryParam(QueryStrings.DEST_ZIP) String destZip,
			@QueryParam(QueryStrings.SHIP_DATE) @DefaultValue("") String shipDate,
			@QueryParam(QueryStrings.SHIP_TIME) String dropOffTime,
			@QueryParam(QueryStrings.MAIL_CLASS) String mailClass, 
			@QueryParam(QueryStrings.DEST_TYPE) String destType) throws NumberFormatException, CalculationNotPossibleException, ParseException, InvalidQueryFormatException {
		return absGetMail(originZip, destZip, shipDate, dropOffTime, mailClass, destType);
	}
   
    //Works for post body if in the form originzip=11111&destzip=22222 IF CONTENT_TYPE = form-urlencoded
//    @Path("/people.xml")
//    @Produces( {"application/xml"} )
//    @Consumes({"application/x-www-form-urlencoded"})
//    @POST
//    public Collection<Output> getPeopleXMLFromForm(@FormParam("originzip") String originZip, @FormParam("destzip") String destZip) {
//    	System.out.println(originZip);
//    	System.out.println(destZip);
//    	return null;
//    }
    
    @Path(BASE_PATH + URL_JSON)
    @Produces( {"application/json"} )
    @Consumes( {"application/xml"} )
    @POST
    public Collection<DazzleOutputMain> getPeopleJSONPost(XMLInput input) throws InvalidQueryFormatException, NumberFormatException, CalculationNotPossibleException, ParseException {
    	System.out.println("Inside the POST xml body path that produces JSON");
    	if(input.getShipDate() == null) {
    		input.setShipDate(DateTimeUtilities.getCurrentUTCDate());
    	}
    	HashMap<String, String> queryTuples = QueryParser.initializeTuples(input.getOriginZip(), input.getDestZip(), input.getShipDate(), input.getShipTime(), input.getMailClass(), input.getDestType());
    	Collection<DazzleOutputMain> output = outputService.getVerbose(queryTuples);
    	return output;
    }
    
    @Path(BASE_PATH + URL_XML)
    @Produces( {"application/xml"} )
    @Consumes( {"application/xml"} )
    @POST
    public Collection<DazzleOutputMain> getPeopleXMLPost(XMLInput input) throws InvalidQueryFormatException, NumberFormatException, CalculationNotPossibleException, ParseException {
    	System.out.println("Inside the POST xml body path that produces XML");
    	if(input.getShipDate() == null) {
    		input.setShipDate(DateTimeUtilities.getCurrentUTCDate());
    	}
    	HashMap<String, String> queryTuples = QueryParser.initializeTuples(input.getOriginZip(), input.getDestZip(), input.getShipDate(), input.getShipTime(), input.getMailClass(), input.getDestType());
    	Collection<DazzleOutputMain> output = outputService.getVerbose(queryTuples);
    	return output;
    }
}