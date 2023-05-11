package com.store.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GameNotFoundExceptionMapper implements ExceptionMapper<GameNotFoundException> {
    @Override
    public Response toResponse(GameNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(new Message(e.getMessage())).build();
    }
}
