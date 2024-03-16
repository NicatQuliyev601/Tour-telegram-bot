package az.nicat.tourtelegrambot.service;


import az.nicat.tourtelegrambot.entity.Language;

public interface LocalizationService {
    String translate(String key, Language language);

    String findByValue(String value);
}
