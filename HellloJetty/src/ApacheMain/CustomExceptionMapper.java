package ApacheMain;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.impl.WebApplicationExceptionMapper;

public class CustomExceptionMapper extends WebApplicationExceptionMapper {
	
	@Override
	public Response toResponse(WebApplicationException e) {
		String error = e.getClass().getName();
		Response r = null;
		
		System.out.println(e.getMessage());
		if(e.getMessage().equals("HTTP 404 Not Found")) {
			r = Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		} else {
			r = Response.status(Response.Status.BAD_REQUEST).entity("<BadRequest_400>" + e.getMessage() + "</BadRequest_400>").build();
		}
		
		System.out.println("In custom exception handler");
        return r;
	}
	
	
	
}
