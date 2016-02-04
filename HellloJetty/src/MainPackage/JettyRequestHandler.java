package MainPackage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import atfImplementation.MainATFImplementation;
 
public class JettyRequestHandler extends AbstractHandler
{
	MainATFImplementation mainLogic;
	HashMap<String, Object> output;
	//This method is called in main when the server receives a request
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
    	HashMap<String, String> queryTuples = new LinkedHashMap<String,String>();
    	
    	response.setContentType("application/xml;charset=utf-8"); //TODO have serializer to dispatch based on content-type in get request
        baseRequest.setHandled(true);
      
             
		try {
			queryTuples = QueryParser.parseStringForTuples(request.getQueryString());
			
	        String xmlResp = "<ExpressMail><OriginZip>" + queryTuples.get(QueryStrings.ORIGIN_ZIP) +
	        		"</OriginZip><Date>" + queryTuples.get(QueryStrings.SHIP_DATE) + 
	        		"</Date><destZip>" + queryTuples.get(QueryStrings.DEST_TYPE) + 
	        		"</destZip><mailClass>" + queryTuples.get(QueryStrings.MAIL_CLASS) +
	        		"</mailClass><destType>" + queryTuples.get(QueryStrings.DEST_TYPE) +
	        		"</destType></ExpressMail>";
	        
	        mainLogic = new MainATFImplementation(queryTuples);
	        mainLogic.execute();
	        
	        output = mainLogic.getOutput();
	        System.out.println("Output size in Jetty: " + output.keySet().size());
	        
	        xmlResp = "<ExpressMail>";
	        for (String s: output.keySet()) {
	        	if (output.get(s) != null) {
	        		xmlResp += "<" + s + ">" + output.get(s).toString() + "</" + s + ">";
	        	} else {
	        		System.out.println("Null key: " + s);
	        	}
	        }
	        xmlResp += "</ExpressMail>";
	        
	        response.setStatus(HttpServletResponse.SC_OK);
	        
	        System.out.println("Sending the following xml response to browser: " + xmlResp);
	        
	        response.getWriter().println(xmlResp);
	        
		} catch (Exception e) {//(InvalidQueryFormatException e) {
		    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().println("<tag>" + e.getMessage() + "</tag>");
		}
    }
}