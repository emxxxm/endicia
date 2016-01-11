import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import atfImplementation.MainATFImplementation;
 
public class EmbeddedJetty extends AbstractHandler
{
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
        String xmlResp = "<ExpressMail><OriginZip>90201</OriginZip><Date></Date><Location><City>Mountains</City><State>CA</State></Location></ExpressMail>";
    	
    	response.setContentType("application/xml;charset=utf-8"); //TODO have serializer to dispatch based on content-type in get request
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        
        System.out.println("This is the queryString: " + request.getQueryString());
        
        Map<String, String> queryTuples = new LinkedHashMap<String,String>();
        
		try {
			queryTuples = QueryParser.parseStringForTuples(request.getQueryString());
		} catch (InvalidQueryFormatException e) {
	        response.getWriter().println("<tag>" + e.getMessage() + "</tag>");
	        return; //TODO implement an exit gracefully function
		}
        
        MainATFImplementation results = new MainATFImplementation(queryTuples);
        
        response.getWriter().println(xmlResp);
        
    }
}