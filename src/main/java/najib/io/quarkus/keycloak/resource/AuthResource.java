package najib.io.quarkus.keycloak.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import najib.io.quarkus.keycloak.dto.KeycloakTokenResponse;
import najib.io.quarkus.keycloak.dto.LoginRequest;
import najib.io.quarkus.keycloak.service.AuthService;
import najib.io.quarkus.keycloak.utils.ApiResponse;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public Response login(LoginRequest payload) {
        KeycloakTokenResponse keycloakTokenResponse = authService.login(payload.email, payload.password);
        ApiResponse<KeycloakTokenResponse> apiResponse = ApiResponse.success(keycloakTokenResponse);
        return Response.status(Response.Status.OK).entity(apiResponse).build();
    }

    @POST
    @Path("/refresh")
    public Response refresh(@QueryParam("refreshToken") String refreshToken) {
        KeycloakTokenResponse keycloakTokenResponse = authService.refreshToken(refreshToken);
        ApiResponse<KeycloakTokenResponse> apiResponse = ApiResponse.success(keycloakTokenResponse);
        return Response.status(Response.Status.OK).entity(apiResponse).build();
    }
}
