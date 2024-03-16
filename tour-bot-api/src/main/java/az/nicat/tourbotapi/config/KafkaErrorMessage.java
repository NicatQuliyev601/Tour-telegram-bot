package az.nicat.tourbotapi.config;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KafkaErrorMessage<T> {
    T data;
    String error;
}
