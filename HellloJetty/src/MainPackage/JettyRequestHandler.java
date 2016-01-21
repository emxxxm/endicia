package MainPackage;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import atfImplementation.MainATFImplementation;
 
public class JettyRequestHandler extends AbstractHandler
{
	MainATFImplementation results;
	//This method is called in main when the server receives a request
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
    	HashMap<String, String> queryTuples = new LinkedHashMap<String,String>();
        String xmlResp = "<ExpressMail><OriginZip>90201</OriginZip><Date></Date><Location><City>Mountains</City><State>CA</State></Location></ExpressMail>";
    	
    	response.setContentType("application/xml;charset=utf-8"); //TODO have serializer to dispatch based on content-type in get request
        baseRequest.setHandled(true);
        
      //  System.out.println("This is the queryString: " + request.getQueryString());
             
		try {
			queryTuples = QueryParser.parseStringForTuples(request.getQueryString());
			
	        results = new MainATFImplementation(queryTuples);
	        
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.getWriter().println(xmlResp);
	        
		} catch (Exception e) {//(InvalidQueryFormatException e) {
		    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().println("<tag>" + e.getMessage() + "</tag>");
		}
    }
}