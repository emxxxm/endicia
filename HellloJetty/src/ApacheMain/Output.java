package ApacheMain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Personel")
public class Output {
	
    private String email;
    private String firstName;
    private String lastName;

    public Output() {
    }

    public Output( final String email ) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( final String email ) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setFirstName( final String firstName ) {
        this.firstName = firstName;
    }

    public void setLastName( final String lastName ) {
        this.lastName = lastName;
    } 
}
