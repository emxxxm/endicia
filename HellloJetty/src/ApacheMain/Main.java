package ApacheMain;


import org.eclipse.jetty.server.Server;  
import org.eclipse.jetty.server.handler.HandlerList;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import org.springframework.web.context.ContextLoaderListener;

public class Main {

	public static void main(String[] args) {
		
		Server server = new Server(4651);
		
		HandlerList handlerList = new HandlerList();
		
		//final JettyRequestHandler context1 = new JettyRequestHandler();
		final ServletHolder servletHolder = new ServletHolder( new CXFServlet());
		
		final ServletContextHandler context2 = new ServletContextHandler();
		//context.setContextPath("/");
		context2.addServlet(servletHolder, "/rest/*");
		context2.addEventListener(new ContextLoaderListener());
		
		context2.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
		context2.setInitParameter("contextConfigLocation",  AppConfig.class.getName());
		
		//handlerList.addHandler(context1);
		handlerList.addHandler(context2);

		
		try {
			server.setHandler(handlerList);
			server.start();
			server.join();
		} catch (Exception e) {
			System.out.println("Level.SEVERE" + e.getMessage() + e);
		} finally {
			try {
				server.stop();
			} catch (Exception e) {
				System.out.println("Level.SEVERE" + e.getMessage() + e);
			}
		}
	}

}
