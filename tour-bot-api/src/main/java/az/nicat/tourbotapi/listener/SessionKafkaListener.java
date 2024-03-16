package az.nicat.tourbotapi.listener;

import az.nicat.tourbotapi.dto.SessionDto;
import az.nicat.tourbotapi.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SessionKafkaListener {

    private final ObjectMapper objectMapper;
    private final RequestService requestService;


    @KafkaListener(topics = "session-new-topic", groupId = "telegram-bot")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("Session Received {}", record.value());

        try {
            SessionDto sessionDto = objectMapper.readValue(record.value(), SessionDto.class);
            requestService.saveSessionToRequest(sessionDto);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing JSON: {}", e.getMessage());
        }
    }
}
