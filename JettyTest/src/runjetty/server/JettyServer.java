package runjetty.server;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class JettyServer {

	private Server server;
	
	public JettyServer() {
<<<<<<< HEAD:JettyTest/src/JettyServer.java
		this(8080);
=======
		this(8995);
>>>>>>> cf8754ed5d9ccde2d8c1e4fb269505cdea6d3b28:JettyTest/src/runjetty/server/JettyServer.java
	}
	
	public JettyServer(Integer runningPort) {
		server = new Server(runningPort);
	}
	
	public void setHandler(ContextHandlerCollection contexts) {
		server.setHandler(contexts);
	}
	
	public void start() throws Exception {
		server.start();
	}
	
	public void stop() throws Exception {
		server.stop();
		server.join();
	}
	
	public boolean isStarted() {
		return server.isStarted();
	}
	
	public boolean isStopped() {
		return server.isStopped();
	}
}