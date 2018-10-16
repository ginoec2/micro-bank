package io.empirebank.jee.account.infrastructure.exception;

import io.empirebank.jee.common.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountExceptionManager implements ExceptionMapper<AccountException> {

    public Response toResponse(AccountException accountException) {
        ApiError apiError = new ApiError(Response.Status.BAD_REQUEST.getStatusCode(),accountException.getMessage());
        return Response.ok(apiError).status(Response.Status.BAD_REQUEST).build();
    }
}
