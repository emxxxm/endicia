import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
import java.util.Enumeration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
 
public class HelloWorld extends AbstractHandler
{
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
        String xmlResp = "<ExpressMail><OriginZip>90201</OriginZip><Date></Date><Location><City>Mountains</City><State>CA</State></Location></ExpressMail>";
        	
    	
    	response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println(xmlResp);
        
        System.out.println("This is the queryString: " + request.getQueryString());;
    }
 
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(4651);
        server.setHandler(new HelloWorld());
 
        server.start();
        server.join();
    }
}