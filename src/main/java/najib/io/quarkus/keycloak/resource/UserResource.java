package najib.io.quarkus.keycloak.resource;

import io.quarkus.oidc.AccessTokenCredential;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import najib.io.quarkus.keycloak.utils.JwtHandler;

import java.util.Map;

@Path("/api/user")
public class UserResource {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @RolesAllowed("default-roles-dive-dev")
    public String user() {
        return "Hello, User!";
    }

    @GET
    @Path("/admin")
    @RolesAllowed({"default-roles-dive-dev", "offline_access", "uma_authorization"})
    public String admin() {
        return "Hello, Admin!";
    }

    // this role "najib-role" is added via Keycloak UI and assigned to the user directly.
    @GET
    @Path("/me")
    @RolesAllowed("najib-role")
    public String me() {
        return "Hello, this is me!";
    }

    @GET
    @Path("/token")
    public Map<String, Object> token() {
        AccessTokenCredential credential = securityIdentity.getCredential(AccessTokenCredential.class);
        String token = credential.getToken();
        return JwtHandler.decode(token);
    }
}
