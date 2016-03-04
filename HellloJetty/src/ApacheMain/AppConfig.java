package ApacheMain;

import java.util.Arrays;   

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { 
    @Bean( destroyMethod = "shutdown" )
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    public Server jaxRsServer() {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint( jaxRsApiApplication(), JAXRSServerFactoryBean.class );
        factory.setServiceBeans( Arrays.< Object >asList( peopleRestService() ) );
        factory.setAddress(factory.getAddress() );
        System.out.println("Factory address: " + factory.getAddress());
        factory.setProviders( Arrays.< Object >asList( jsonProvider() ) );
        factory.setProviders( Arrays.< Object >asList(xmlProvider() ) );
        factory.setProvider(customException());
        return factory.create();
    }
    
    @Bean 
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
    
    @Bean
    public CustomExceptionMapper customException() {
    	return new CustomExceptionMapper();
    }

    @Bean 
    public MainRestService peopleRestService() {
        return new MainRestService();
    }

    @Bean 
    public OutputService peopleService() {
        return new OutputService();
    }
    
    @Bean
    public JAXBElementProvider<Output> xmlProvider() {
    	return new JAXBElementProvider<Output>();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }
    

}