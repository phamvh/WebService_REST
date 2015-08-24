package org.koushik.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.koushik.javabrains.messenger.model.ErrorMessage;



/**
 * Annotation @Provider tells jax-rs that this is a class that I provides to jax-rs.
 * So that whenever an exception happens, jax-rs should look into this Provider, which in this case,
 * is a mapper to an exception.
 * 
 * Specifically, when an DataNotFoundException is thrown somewhere, jax-rs looks into all the providers
 * to find one that handle the exception. This mapper simply says that I can handle DataNotFoundException,
 * which is specified in the generic brackets ExceptionMapper<DataNotFoundException>
 *
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "http://javabrains.koushik.org");
		return Response.status(Status.NOT_FOUND)
						.entity(errorMessage)
						.build();
	}
	

}
