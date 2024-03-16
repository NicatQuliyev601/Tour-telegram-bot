package az.nicat.tourbotapi.controller;

import az.nicat.tourbotapi.dto.request.OfferRequest;
import az.nicat.tourbotapi.dto.response.OfferResponse;
import az.nicat.tourbotapi.entity.Offer;
import az.nicat.tourbotapi.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
@RequiredArgsConstructor
@CrossOrigin
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<OfferResponse> createOffer(@RequestParam Long requestId, @RequestParam Long agentId, @RequestBody OfferRequest offerRequest) {
        return new ResponseEntity<>(offerService.createOffer(requestId, agentId, offerRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getAll() {
        return new ResponseEntity<>(offerService.getAll(), HttpStatus.OK);
    }
}
