package az.nicat.tourtelegrambot.telegram.util;

import az.nicat.tourtelegrambot.entity.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotMessageServiceImpl implements BotMessageService {
    private final BotMessageRepo botMessageRepo;

    @Override
    public Optional<BotMessage> getBy(String key, Language language) {
        return botMessageRepo.findByKeyAndLanguage(key, language);
    }

}
