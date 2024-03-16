package az.nicat.tourtelegrambot.dto;

import az.nicat.tourtelegrambot.entity.Client;
import lombok.Data;

import java.util.UUID;

@Data
public class SessionDTO {
    private UUID id;
    private Client client;
    private String answers;

}
