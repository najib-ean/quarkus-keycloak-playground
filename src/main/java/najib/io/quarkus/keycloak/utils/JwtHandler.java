package najib.io.quarkus.keycloak.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.BadRequestException;

import java.util.Base64;
import java.util.Map;

public class JwtHandler {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Map<String, Object> decode(String token) {
        try {

            if (token == null) {
                throw new BadRequestException("Token is null");
            }

            String[] tokenParts = token.split("\\.");
            if (tokenParts.length < 3) {
                throw new BadRequestException("Invalid JWT token format");
            }

            System.out.println("length of tokenParts: " + tokenParts.length);

            byte[] decode = Base64.getUrlDecoder().decode(tokenParts[1]);
            String payload = new String(decode);

            return MAPPER.readValue(payload, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to decode JWT", e);
        }
    }

}
