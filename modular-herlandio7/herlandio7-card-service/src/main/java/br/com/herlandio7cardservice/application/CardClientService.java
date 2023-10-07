package br.com.herlandio7cardservice.application;

import java.util.List;

import org.springframework.stereotype.Service;
import br.com.herlandio7cardservice.domain.CardClient;
import br.com.herlandio7cardservice.infra.repository.CardClientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardClientService {
    private final CardClientRepository repository;

    public List<CardClient> listCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
