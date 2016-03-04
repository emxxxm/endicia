package ApacheMain.outputwrappers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Message")
public class StringOutputWrapper {
	String body;
	
	public StringOutputWrapper() {
		
	}
	
	public StringOutputWrapper(String m) {
		this.body = m;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
