package az.nicat.tourbotapi.service;

import az.nicat.tourbotapi.entity.AgentRequest;
import az.nicat.tourbotapi.entity.Request;
import az.nicat.tourbotapi.entity.Status;
import az.nicat.tourbotapi.exception.ErrorCodes;
import az.nicat.tourbotapi.exception.RequestNotFoundException;
import az.nicat.tourbotapi.repository.AgentRequestRepository;
import az.nicat.tourbotapi.repository.RequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchivingService {

    private final RequestRepository requestRepository;
    private final AgentRequestRepository agentRequestRepository;

    @Transactional
    public void sendRequestToArchiveById(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(ErrorCodes.REQUEST_NOT_FOUND));

        request.setActive(false);
        requestRepository.save(request);

        // Find and update the associated agent request status to archived
        AgentRequest agentRequest = agentRequestRepository.findByRequest(request);
        if (agentRequest != null) {
            agentRequest.setStatus(Status.ARCHIVED);
            agentRequestRepository.save(agentRequest);
        }
    }
}
