package az.nicat.tourbotapi.repository;

import az.nicat.tourbotapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmationToken(String confirmationToken);

    Boolean existsByEmail(String email);

    User findByUsername(String username);
}
