package br.com.herlandio7clientservice.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.herlandio7clientservice.domain.Client;
import br.com.herlandio7clientservice.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // cria constructor em tempo de compilação
public class ClientService {
    private final ClientRepository repository;

    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }

    public Optional<Client> getByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}
