package az.nicat.tourtelegrambot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@Slf4j
@EnableKafka
@EnableCaching
public class TurAlTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurAlTelegramBotApplication.class, args);
    }

}
