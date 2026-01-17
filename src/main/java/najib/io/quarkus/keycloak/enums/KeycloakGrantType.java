package najib.io.quarkus.keycloak.enums;

public enum KeycloakGrantType {
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");

    private final String label;

    KeycloakGrantType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
