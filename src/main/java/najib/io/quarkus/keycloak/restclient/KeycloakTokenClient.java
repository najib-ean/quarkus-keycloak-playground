package najib.io.quarkus.keycloak.restclient;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import najib.io.quarkus.keycloak.dto.KeycloakTokenResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "keycloak-api")
@Path("/realms/{realm}/protocol/openid-connect/token")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface KeycloakTokenClient {

    @POST
    KeycloakTokenResponse token(
            @PathParam("realm") String realm,
            @FormParam("client_id") String clientId,
            @FormParam("client_secret") String clientSecret,
            @FormParam("grant_type") String grantType,
            @FormParam("username") String email,    // request to keycloak use "username" even the value is email.
            @FormParam("password") String password,
            @FormParam("refresh_token") String refreshToken
    );

}
