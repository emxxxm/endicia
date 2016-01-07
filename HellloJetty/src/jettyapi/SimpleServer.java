package jettyapi;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
 
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
public class SimpleServer {
	public static void main(String[] args) throws Exception{
		Server server = new Server(8888);
		server.start();
		server.join();
	}
}
