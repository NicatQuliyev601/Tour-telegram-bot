package az.nicat.tourtelegrambot.listeners;

import az.nicat.tourtelegrambot.dto.OfferDto;
import az.nicat.tourtelegrambot.service.OfferService;
import az.nicat.tourtelegrambot.telegram.TelegramBot;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OfferKafkaListener {

    private final ObjectMapper objectMapper;
    private final OfferService offerService;
    private final TelegramBot telegramBot;

    @KafkaListener(topics = "offer-topic", groupId = "telegram-bot")
    public void listen(ConsumerRecord<String, String> record) {
        String jsonString = record.value();
        log.info("Received Kafka message: {}", jsonString);
        try {
            OfferDto offerDto = objectMapper.readValue(jsonString, OfferDto.class);
            offerService.generateImageWithText(offerDto);
            telegramBot.sendPhoto(offerDto);
        } catch (Exception e) {
            log.error("Error processing Kafka message: {}", e.getMessage());
        }
    }
}
