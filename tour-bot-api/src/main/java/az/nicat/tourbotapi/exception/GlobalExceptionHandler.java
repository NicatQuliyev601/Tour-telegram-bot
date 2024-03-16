package az.nicat.tourbotapi.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailExistException(EmailExistException ex,
                                                                      WebRequest req) {

        ex.printStackTrace();

        return ResponseEntity.status(400).body(ErrorResponseDto.builder()
                .status(400)
                .title("Exception")
                .details("Email already exist")
                .build());
    }

    @ExceptionHandler(AgentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAgentNotFoundException(AgentNotFoundException ex,
                                                                         WebRequest req) {
        ex.printStackTrace();

        return ResponseEntity.status(400).body(ErrorResponseDto.builder()
                .status(400)
                .title("Exception")
                .details("Agent not found")
                .build());
    }

    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRequestNotFoundException(RequestNotFoundException ex,
                                                                         WebRequest req) {
        ex.printStackTrace();

        return ResponseEntity.status(400).body(ErrorResponseDto.builder()
                .status(400)
                .title("Exception")
                .details("Request not found")
                .build());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidPasswordException(InvalidPasswordException ex,
                                                                           WebRequest req) {
        ex.printStackTrace();

        return ResponseEntity.status(404).body(ErrorResponseDto.builder()
                .status(404)
                .title("Exception")
                .details("Invalid password")
                .build());
    }
}
