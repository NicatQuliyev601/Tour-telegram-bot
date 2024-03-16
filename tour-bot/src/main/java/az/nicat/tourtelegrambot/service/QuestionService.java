package az.nicat.tourtelegrambot.service;


import az.nicat.tourtelegrambot.entity.Question;

import java.util.Optional;

public interface QuestionService {
    Optional<Question> findById(Long i);

    Optional<Question> findByKey(String key);
}
