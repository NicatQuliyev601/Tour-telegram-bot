package az.nicat.tourtelegrambot.repository;

import az.nicat.tourtelegrambot.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> getClientByChatId(long chatId);
}
