package az.nicat.tourtelegrambot.service;


import az.nicat.tourtelegrambot.entity.Client;

import java.util.Optional;

public interface ClientService {
    Optional<Client> getByChatId(long chatId);
    Client create(Client client);
    void delete(long id);
}
