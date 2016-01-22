package MainPackage;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;

import MainPackage.JettyRequestHandler;
import MainPackage.LoggingHub;

public class Main {

	private final static Logger logger = Logger.getLogger(LoggingHub.class.getName());
	public static JettyRequestHandler jRequest;
	public static void main(String[] args) {
		
		LoggingHub.initLogger();
		
		Server server = new Server(4651);
		try {
			jRequest = new JettyRequestHandler();
			server.setHandler(jRequest);
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
