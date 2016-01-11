import org.eclipse.jetty.server.Server;

public class Main {
	
    public static void main(String[] args) throws Exception
    {
    	Server server = new Server(4651);
        try {
	        server.setHandler(new EmbeddedJetty());
	        server.start();
	        server.join();
        } catch (Exception e) {
        	e.printStackTrace(); //TODO REMOVE THIS LINE BEFORE PUTTING INTO PRODUCTION
        	//TODO set up some logging
        }
        finally {
        	server.stop();
        }
    }
    
}
