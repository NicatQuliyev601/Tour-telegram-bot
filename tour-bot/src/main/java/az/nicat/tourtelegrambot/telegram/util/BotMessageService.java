package az.nicat.tourtelegrambot.telegram.util;


import az.nicat.tourtelegrambot.entity.Language;

import java.util.Optional;

public interface BotMessageService {
    Optional<BotMessage> getBy(String key, Language language);
}
