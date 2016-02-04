package ApacheMain;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import ApacheMain.outputwrappers.DazzleOutputMain;
import MainPackage.DateTimeUtilities;
import MainPackage.InvalidQueryFormatException;
import MainPackage.LoggingHub;
import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.Location;
import atfImplementation.MainATFImplementation;

//@Path( "/people" ) 
public class MainRestService {
	@Inject private OutputService outputService;
	MainATFImplementation mainLogic;
	
	public static final String BASE_PATH = "/mail";
	public static final String APPLICATION = "application/";
	public static final String JSON = "json";
	public static final String URL_JSON = "." + JSON;
	public static final String XML = "xml";
	public static final String URL_XML = "." + XML;
	
	public static final String API = "v1";
	public static final String BASE_CONTEXT = "/webtools/";

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

	@Path(BASE_PATH + URL_JSON)
	@Produces( {APPLICATION + JSON} )
	@GET
	public Collection<DazzleOutputMain> getMailJSON(  @QueryParam( QueryStrings.ORIGIN_ZIP) String originZip, 
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

	@Path(BASE_PATH + URL_XML)
	@Produces( {APPLICATION + XML} )
	@GET
	public Collection<DazzleOutputMain> getMailXML(  @QueryParam( QueryStrings.ORIGIN_ZIP) String originZip, 
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
}