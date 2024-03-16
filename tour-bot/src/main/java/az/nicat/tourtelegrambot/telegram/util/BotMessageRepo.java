package az.nicat.tourtelegrambot.telegram.util;

import az.nicat.tourtelegrambot.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BotMessageRepo extends JpaRepository<BotMessage, Long> {
    Optional<BotMessage> findByKeyAndLanguage(String key, Language language);
}
