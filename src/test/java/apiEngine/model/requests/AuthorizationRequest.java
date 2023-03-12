package apiEngine.model.requests;

public class AuthorizationRequest {

    public String code;
    public String message;
    public String name;


    public AuthorizationRequest(String code, String message, String name) {
        this.code = code;
        this.message = message;
        this.name = name;
    }
}
