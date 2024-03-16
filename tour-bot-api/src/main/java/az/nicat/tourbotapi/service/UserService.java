package az.nicat.tourbotapi.service;

import az.nicat.tourbotapi.dto.request.ChangePasswordRequest;
import az.nicat.tourbotapi.dto.request.LoginRequest;
import az.nicat.tourbotapi.dto.request.RegisterRequest;
import az.nicat.tourbotapi.dto.response.LoginResponseDTO;
import az.nicat.tourbotapi.dto.response.Response;
import az.nicat.tourbotapi.entity.Agent;
import az.nicat.tourbotapi.entity.User;
import az.nicat.tourbotapi.exception.AgentNotFoundException;
import az.nicat.tourbotapi.exception.EmailExistException;
import az.nicat.tourbotapi.exception.ErrorCodes;
import az.nicat.tourbotapi.exception.InvalidPasswordException;
import az.nicat.tourbotapi.repository.AgentRepository;
import az.nicat.tourbotapi.repository.UserRepository;
import az.nicat.tourbotapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {


    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AgentRepository agentRepository;


    public ResponseEntity<Response> registerAgent(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailExistException(ErrorCodes.EMAIL_ALREADY_EXIST);
        }

        Agent agent = Agent.builder()
                .agentName(request.getAgentName())
                .voen(request.getVoen())
                .companyName(request.getCompanyName())
                .build();
        agentRepository.save(agent);

        User user = User.builder()
                .password(request.getPassword())
                .username(request.getUsername())
                .email(request.getEmail())
                .agent(agent)
                .confirmed(false)
                .build();
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        String confirmationToken = getConfirmationToken();
        user.setConfirmationToken(confirmationToken);
        userRepository.save(user);

        sendConfirmationEmail(user.getEmail(), user.getUsername(), agent.getCompanyName(), confirmationToken);

        return ResponseEntity.ok(Response.builder().jwt(jwtService.issueToken(user)).build());
    }

    private void sendConfirmationEmail(String email, String username, String companyName, String confirmationToken) {
        String confirmationMessage = constructConfirmationMessage(username, companyName, confirmationToken);
        emailService.sendMail(email, "Confirm Your Registration", confirmationMessage);
    }

    private String constructConfirmationMessage(String username, String companyName, String confirmationToken) {
        return "Dear " + username + ",\n\n" +
                "We're delighted to welcome you to " + companyName + "!\n\n" +
                "Thank you for choosing us as your trusted platform. Your registration marks the beginning of an exciting journey with us. To gain full access to our platform's features and benefits, please click the link below to verify your email address:\n\n" +
                "Confirmation Link:   https://3af9-82-194-17-140.ngrok-free.app/api/auth/confirmation?confirmationToken=" + confirmationToken + "\n\n" +
                "Your satisfaction and success are our top priorities, and we're committed to providing you with a seamless experience. If you have any questions or need assistance during your registration process or beyond, our dedicated support team is here to help. Feel free to reach out to us at any time at [nijatfg@code.edu.az].\n\n" +
                "We're thrilled to have you onboard and are looking forward to partnering with you to achieve your goals. Welcome to the " + companyName + " community!\n\n" +
                "Warmest regards,\n" +
                "The " + companyName + " Team";

         //TODO ngrok linkidi deyisecek
    }

    public ResponseEntity<LoginResponseDTO> loginAgent(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AgentNotFoundException(ErrorCodes.AGENT_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException(ErrorCodes.INVALID_PASSWORD);
        }

        // Return JWT token and Agent ID in the response
        return ResponseEntity.ok(LoginResponseDTO.builder()
                .jwt(jwtService.issueToken(user))
                .agentId(user.getAgent().getId())
                .build());
    }



    public ResponseEntity<?> confirmation(String confirmationToken) {
        Optional<User> userOptional = userRepository.findByConfirmationToken(confirmationToken);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setConfirmed(true);
            userRepository.save(user);

            return ResponseEntity.ok("User confirmed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Confirmation token is invalid");
        }
    }

    private String getConfirmationToken() {
        return UUID.randomUUID().toString();
    }

    public ResponseEntity<Response> changePassword(ChangePasswordRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null || !user.getPassword().equals(request.getCurrentPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response("Invalid credentials"));
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.ok(new Response("Password changed successfully"));
    }
}
