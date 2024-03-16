package az.nicat.tourbotapi.repository;

import az.nicat.tourbotapi.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findBySessionId(UUID sessionId);
}
