package org.koushik.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.koushik.javabrains.messenger.model.ErrorMessage;

/**
 * This is a generic handler for all other exceptions, which were not handled by other handlers.
 * jax-rs maps exceptions to handlers in a similar way, as how exceptions are handled. More specific mappers are tried first,
 * if non is found for an exception, then the generic one (which takes Throwable as the type) will handle it.
 *
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(this.getClass().getName()+": "+ex.getMessage(), 500, "http://javabrains.koushik.org");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(errorMessage)
						.build();
	}

}
