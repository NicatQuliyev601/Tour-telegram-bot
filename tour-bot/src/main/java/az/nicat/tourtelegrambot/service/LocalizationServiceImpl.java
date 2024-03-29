package az.nicat.tourtelegrambot.service;

import az.nicat.tourtelegrambot.entity.Language;
import az.nicat.tourtelegrambot.entity.Locale;
import az.nicat.tourtelegrambot.repository.LocaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {
    private final LocaleRepository localeRepository;

    public String translate(String key, Language language) {
        Optional<Locale> locale = localeRepository.findByKeyAndLanguage(key, language);
        return locale.map(Locale::getValue).orElse(null);
    }

    public String findByValue(String value) {
        if (localeRepository.findByValue(value).isPresent())
            return localeRepository.findByValue(value).get().getKey();
        return null;
    }
}
