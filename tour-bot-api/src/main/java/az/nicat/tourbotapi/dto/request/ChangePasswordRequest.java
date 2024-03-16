package az.nicat.tourbotapi.dto.request;


import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String username;
    private String currentPassword;
    private String newPassword;
}
