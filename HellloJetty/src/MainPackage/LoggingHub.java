package MainPackage;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingHub {

	//This is how you create a logger
	private final static Logger logger = Logger.getLogger(LoggingHub.class.getName());
	private static FileHandler fh = null;
	private static boolean APPEND = true;
	private static boolean NO_APPEND = false;

	public static void initLogger(){
		try {
			fh=new FileHandler(DateTimeUtilities.getCurrentUTCDate() + "log.txt", NO_APPEND);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		Logger l = Logger.getLogger("");
		fh.setFormatter(new SimpleFormatter());
		l.addHandler(fh);
	}
	
}
