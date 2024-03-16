package az.nicat.tourbotapi.controller;

import az.nicat.tourbotapi.entity.Request;
import az.nicat.tourbotapi.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/requests")
@RequiredArgsConstructor
@CrossOrigin
public class RequestController {

    private final RequestService requestService;

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        return new ResponseEntity<>(requestService.getAllRequests(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Request>> getRequestById(@PathVariable Long id) {
        return new ResponseEntity<>(requestService.findById(id), HttpStatus.OK);
    }


}
