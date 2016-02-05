package unittest;

import static org.junit.Assert.*;

import java.net.HttpURLConnection;
import java.net.URL;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Before;
import org.junit.Test;

public class TestJetty
{


    @Before
    public void startJetty() throws Exception{
        // Create Server
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler();
        ServletHolder defaultServ = new ServletHolder("default", DefaultServlet.class);
        defaultServ.setInitParameter("resourceBase",System.getProperty("user.dir"));
        defaultServ.setInitParameter("dirAllowed","true");
        context.addServlet(defaultServ,"/");
        server.setHandler(context);

        // Start Server
        server.start();
    }

    @Test
    public void testGet() throws Exception{
        // Test GET
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8080/").openConnection();
        http.connect();
        assertEquals("Response Code", http.getResponseCode(), HttpStatus.OK_200);
    }
}