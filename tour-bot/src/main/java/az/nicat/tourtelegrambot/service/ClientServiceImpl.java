package az.nicat.tourtelegrambot.service;

import az.nicat.tourtelegrambot.entity.Client;
import az.nicat.tourtelegrambot.repository.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepo clientRepo;
    @Override
    public Optional<Client> getByChatId(long chatId) {
        return clientRepo.getClientByChatId(chatId);
    }

    @Override
    public Client create(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public void delete(long id) {
        clientRepo.deleteById(id);
    }
}
