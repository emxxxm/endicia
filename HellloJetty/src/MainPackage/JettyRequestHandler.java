package MainPackage;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import atfImplementation.MainATFImplementation;
 
public class JettyRequestHandler extends AbstractHandler
{
	MainATFImplementation mainLogic;
	HashMap<String, String> output;
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
        
      //  System.out.println("This is the queryString: " + request.getQueryString());
             
		try {
			queryTuples = QueryParser.parseStringForTuples(request.getQueryString());

	        String xmlResp = "<ExpressMail><OriginZip>" + queryTuples.get(QueryStrings.ORIGIN_ZIP) +
	        		"</OriginZip><Date>" + queryTuples.get(QueryStrings.DATE) + 
	        		"</Date><destZip>" + queryTuples.get(QueryStrings.DEST_TYPE) + 
	        		"</destZip><mailClass>" + queryTuples.get(QueryStrings.MAIL_CLASS) +
	        		"</mailClass><destType>" + queryTuples.get(QueryStrings.DEST_TYPE) +
	        		"</destType></ExpressMail>";
	        
	        mainLogic = new MainATFImplementation(queryTuples);
	        mainLogic.execute();
	        
	        output = mainLogic.getOutput();
	        
	        xmlResp = "<ExpressMail>";
	        for (String s: output.keySet()) {
	        	xmlResp += "<" + s + ">" + output.get(s) + "</" + s + ">"; 
	        }
	        xmlResp += "</ExpressMail>";
	        
	        System.out.println(xmlResp);
	        
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.getWriter().println(xmlResp);
	        
		} catch (Exception e) {//(InvalidQueryFormatException e) {
		    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().println("<tag>" + e.getMessage() + "</tag>");
		}
    }
}