package az.nicat.tourtelegrambot.telegram.util;


import az.nicat.tourtelegrambot.entity.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "botmessages")
public class BotMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String key;
    @Enumerated(EnumType.STRING)
    Language language;
    String message;
}
