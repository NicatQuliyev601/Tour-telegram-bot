package az.nicat.tourtelegrambot.repository;


import az.nicat.tourtelegrambot.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionRepository  extends JpaRepository<Option, Long> {
    Optional<Option> findByKey(String key);
    List<Option> findByQuestion_Id(Long questionId);

}
