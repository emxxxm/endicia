package MainPackage;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVParser;
import org.eclipse.jetty.server.Server;

import dataHandler.DataFileParser;

public class Main {

	private final static Logger logger = Logger.getLogger(LoggingHub.class.getName());
	
	public static void main(String[] args) {
		
		LoggingHub.initLogger();
		
		Server server = new Server(4651);
		try {
			server.setHandler(new JettyRequestHandler());
			server.start();
			server.join();
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
