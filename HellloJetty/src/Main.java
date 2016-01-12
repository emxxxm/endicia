import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.eclipse.jetty.server.Server;

public class Main {

	private final static Logger logger = Logger.getLogger(Main.class.getName());
	private static FileHandler fh = null;
	private static boolean APPEND = true;
	private static boolean NO_APPEND = false;
	
	public static void main(String[] args) {
		
		initLogger();
		DateTimeUtilities.logStuff();
		
		logger.log(Level.INFO, "hi");
		Server server = new Server(4651);
		try {
			server.setHandler(new JettyRequestHandler());
			server.start();
			server.join();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				server.stop();
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}

	}

	public static void initLogger(){
		String date = DateTimeUtilities.getCurrentUTCDateTime();
		try {
			fh=new FileHandler(date.substring(0, date.indexOf("T")) + "log.txt", NO_APPEND);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		Logger l = Logger.getLogger("");
		fh.setFormatter(new SimpleFormatter());
		l.addHandler(fh);
	}

}
