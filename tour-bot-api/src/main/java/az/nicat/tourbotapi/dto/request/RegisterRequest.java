package az.nicat.tourbotapi.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    String email;
    String password;
    String username;
    String voen;
    String agentName;
    String companyName;
}
