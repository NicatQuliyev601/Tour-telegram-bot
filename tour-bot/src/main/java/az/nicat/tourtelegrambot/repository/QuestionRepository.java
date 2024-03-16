package az.nicat.tourtelegrambot.repository;

import az.nicat.tourtelegrambot.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByKey(String key);
}
