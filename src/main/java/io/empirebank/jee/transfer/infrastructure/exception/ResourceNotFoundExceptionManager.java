package io.empirebank.jee.transfer.infrastructure.exception;

import io.empirebank.jee.common.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionManager implements ExceptionMapper<ResourceNotFoundException> {

    public Response toResponse(ResourceNotFoundException exception) {
        ApiError apiError = new ApiError(Response.Status.NOT_FOUND.getStatusCode(),exception.getMessage());
        return Response.ok(apiError).status(Response.Status.NOT_FOUND).build();
    }
}
