package az.nicat.tourbotapi.repository;

import az.nicat.tourbotapi.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {

}
