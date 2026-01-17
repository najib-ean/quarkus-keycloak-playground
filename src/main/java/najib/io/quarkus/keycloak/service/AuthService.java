package najib.io.quarkus.keycloak.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import najib.io.quarkus.keycloak.dto.KeycloakTokenResponse;
import najib.io.quarkus.keycloak.enums.KeycloakGrantType;
import najib.io.quarkus.keycloak.restclient.KeycloakTokenClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AuthService {

    @RestClient
    KeycloakTokenClient keycloakTokenClient;

    @ConfigProperty(name = "keycloak.realm")
    String realm;

    @ConfigProperty(name = "keycloak.client-id")
    String clientId;

    @ConfigProperty(name = "keycloak.client-secret")
    String clientSecret;

    public KeycloakTokenResponse login(String email, String password) {
        try {
            return keycloakTokenClient.token(
                    realm,
                    clientId,
                    clientSecret,
                    KeycloakGrantType.PASSWORD.getLabel(),
                    email,
                    password,
                    null
            );
        } catch (WebApplicationException e) {
            if (e.getResponse().getStatus() == Response.Status.UNAUTHORIZED.getStatusCode()) {
                throw new NotAuthorizedException("Invalid email or password", e);
            }
            throw new RuntimeException(e);
        }
    }

    public KeycloakTokenResponse refreshToken(String refreshToken) {
        return keycloakTokenClient.token(
                realm,
                clientId,
                clientSecret,
                KeycloakGrantType.REFRESH_TOKEN.getLabel(),
                null,
                null,
                refreshToken
        );
    }

}
