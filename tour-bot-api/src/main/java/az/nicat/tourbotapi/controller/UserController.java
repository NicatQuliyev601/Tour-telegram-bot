package az.nicat.tourbotapi.controller;

import az.nicat.tourbotapi.dto.request.ChangePasswordRequest;
import az.nicat.tourbotapi.dto.request.LoginRequest;
import az.nicat.tourbotapi.dto.request.RegisterRequest;
import az.nicat.tourbotapi.dto.response.LoginResponseDTO;
import az.nicat.tourbotapi.dto.response.Response;
import az.nicat.tourbotapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerAgent(@RequestBody RegisterRequest request) {
        return userService.registerAgent(request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginAgent(@RequestBody LoginRequest loginRequest) {
        return userService.loginAgent(loginRequest);
    }


    @GetMapping("/confirmation")
    public ResponseEntity<?> confirmation(@RequestParam("confirmationToken") String confirmationToken) {
        return userService.confirmation(confirmationToken);
    }
    @PostMapping("/change-password")
    public ResponseEntity<Response> changePassword(@RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request);
    }

}
