package az.nicat.tourbotapi.listener;

import az.nicat.tourbotapi.service.AgentRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class AcceptOfferKafkaListener {

    private final AgentRequestService service;
    private static final Pattern OFFER_ID_PATTERN = Pattern.compile("offerId: (\\d+)");

    @KafkaListener(topics = "accept-offer-topic", groupId = "telegram-bot")
    public void listen(ConsumerRecord<String, String> record) {
        String messageValue = record.value();

        Matcher matcher = OFFER_ID_PATTERN.matcher(messageValue);
        if (matcher.find()) {
            long offerId = Long.parseLong(matcher.group(1));
            log.info("Offer received {} ", messageValue);

            service.update(offerId);
        }
    }
}

