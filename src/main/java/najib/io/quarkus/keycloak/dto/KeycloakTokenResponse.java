package najib.io.quarkus.keycloak.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeycloakTokenResponse {
    @JsonProperty("access_token")
    public String accessToken;

    @JsonProperty("expires_in")
    public int expiresIn;

    @JsonProperty("refresh_token")
    public String refreshToken;

    @JsonProperty("refresh_expires_in")
    public int refreshExpiresIn;

    @JsonProperty("token_type")
    public String tokenType;

    @JsonProperty("session_state")
    public String sessionState;
}
