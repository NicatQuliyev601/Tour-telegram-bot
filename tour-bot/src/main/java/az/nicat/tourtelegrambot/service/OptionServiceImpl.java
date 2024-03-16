package az.nicat.tourtelegrambot.service;

import az.nicat.tourtelegrambot.entity.Option;
import az.nicat.tourtelegrambot.repository.OptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;

    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<Option> findByQuestionId(Long questionId) {
        return optionRepository.findByQuestion_Id(questionId);
    }

    public Optional<Option> findByKey(String key) {
        return optionRepository.findByKey(key);
    }

}
