package az.nicat.tourbotapi.service;

import az.nicat.tourbotapi.entity.AgentRequest;
import az.nicat.tourbotapi.entity.Offer;
import az.nicat.tourbotapi.entity.Request;
import az.nicat.tourbotapi.entity.Status;
import az.nicat.tourbotapi.repository.AgentRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentRequestService {
    private final AgentRequestRepository agentRequestRepository;
    private final OfferService offerService;

    public AgentRequest findByRequestId(Long requestId) {
        return agentRequestRepository.findByRequestId(requestId);
    }

    public void update(long offerId) {
        Offer offer = offerService.findById(offerId);
        if (offer == null) {
            log.error("Offer with ID {} not found.", offerId);
            return;
        }
        Request request = offer.getRequest();

        AgentRequest agentRequest = agentRequestRepository.findByRequestId(request.getId());
        if (agentRequest == null) {
            log.error("Agent request for request ID {} not found.", request.getId());
            return;
        }

        agentRequest.setStatus(Status.ACCEPTED);
        agentRequest.setStatusTime(LocalDateTime.now());
        agentRequestRepository.save(agentRequest);
        log.info("Agent request status updated to 'accepted' for request ID: {}", request.getId());

    }
}
