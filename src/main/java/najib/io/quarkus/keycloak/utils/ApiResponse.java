package najib.io.quarkus.keycloak.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.ws.rs.core.Response;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"success", "status_code", "message", "data"})
public class ApiResponse<T> {
    public boolean success;
    public int status_code;
    public String message;
    public T data;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, int status, String message, T data) {
        this.success = success;
        this.status_code = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, Response.Status.OK.getStatusCode(), message, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, Response.Status.OK.getStatusCode(), "Success", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, Response.Status.OK.getStatusCode(), message, data);
    }

    public static <T> ApiResponse<T> successCreate(String message, T data) {
        return new ApiResponse<>(true, Response.Status.CREATED.getStatusCode(), message, data);
    }

    public static <T> ApiResponse<T> fail(int status, String message) {
        return new ApiResponse<>(false, status, message, null);
    }

    public static <T> ApiResponse<T> fail(int status, String message, T data) {
        return new ApiResponse<>(false, status, message, data);
    }
}
