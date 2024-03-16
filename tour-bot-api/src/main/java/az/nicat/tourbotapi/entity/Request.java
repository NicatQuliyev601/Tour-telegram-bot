package az.nicat.tourbotapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    UUID sessionId;
    LocalDateTime creationTime;
    LocalDateTime deadline;
    String fullName;
    String phoneNumber;
    boolean active;

    @JsonProperty("answers")
    @Convert(converter = StringToJsonConverter.class)
    private Map<String, Object> answers;

    @OneToMany(mappedBy = "request")
    private List<Offer> offers;
}
