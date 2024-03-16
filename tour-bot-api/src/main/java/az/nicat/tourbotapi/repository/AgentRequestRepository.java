package az.nicat.tourbotapi.repository;

import az.nicat.tourbotapi.entity.AgentRequest;
import az.nicat.tourbotapi.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRequestRepository extends JpaRepository<AgentRequest, Long> {

    AgentRequest findByRequest(Request request);

    AgentRequest findByRequestId(Long requestId);
}
