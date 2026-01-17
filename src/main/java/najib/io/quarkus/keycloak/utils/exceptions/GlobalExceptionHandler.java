package najib.io.quarkus.keycloak.utils.exceptions;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import najib.io.quarkus.keycloak.utils.ApiResponse;
import org.jboss.logging.Logger;

@Provider
@ApplicationScoped
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Inject
    Logger logger;

    @Override
    public Response toResponse(Exception exception) {
        Response.Status status;
        String message = exception.getMessage();

        switch (exception) {
            case BadRequestException badRequestException -> status = Response.Status.BAD_REQUEST;
            case NotFoundException notFoundException -> status = Response.Status.NOT_FOUND;
            case NotAuthorizedException notAuthorizedException ->
                    status = Response.Status.UNAUTHORIZED;
            default -> {
                logger.info(message, exception);
                status = Response.Status.INTERNAL_SERVER_ERROR;
                message = "Internal Server Error";
            }
        }

        return Response.status(status).entity(ApiResponse.fail(status.getStatusCode(), message)).build();
    }
}
