package az.nicat.tourtelegrambot.repository;

import az.nicat.tourtelegrambot.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SessionRepo extends JpaRepository<Session, UUID> {
    List<Session> findByClient_ChatId(long chatId);
    List<Session> findAllByClient_ChatId(long chatId);

    Session findBySessionId(UUID sessionId);
}
