package az.nicat.tourtelegrambot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String key;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    Question question;

    @NonNull
    Long nextQuestionId;
}
