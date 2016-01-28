package ApacheMain;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import MainPackage.JettyRequestHandler;
import MainPackage.LoggingHub;

public class Main {

	private final static Logger logger = Logger.getLogger(LoggingHub.class.getName());
	public static JettyRequestHandler jRequest;
	public static void main(String[] args) {
		
		LoggingHub.initLogger();
		
		Server server = new Server(4651);
		
		final ServletHolder servletHolder = new ServletHolder( new CXFServlet());
		final ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		context.addServlet(servletHolder, "/rest/");
		System.out.println("Before addEventListener");
		context.addEventListener(new ContextLoaderListener());
		
		System.out.println("Before SetInitParameter");
		context.setInitParameter("contextClass", AnnotationConfigApplicationContext.class.getName());
		context.setInitParameter("contextConfigLocation",  AppConfig.class.getName());
		System.out.println("After setInitParameter");
		
		try {
			server.setHandler(context);
			System.out.println("Before Server Start");
			server.start();
			System.out.println("After Server Start");
			server.join();
			System.out.println("After server join");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			try {
				server.stop();
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}
	}

}
