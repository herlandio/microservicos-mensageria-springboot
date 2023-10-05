package br.com.herlandio7creditassessorservice.application;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.herlandio7creditassessorservice.domain.model.CardClient;
import br.com.herlandio7creditassessorservice.domain.model.CustomerSituation;
import br.com.herlandio7creditassessorservice.domain.model.DataClient;
import br.com.herlandio7creditassessorservice.infra.clients.CardResourceClient;
import br.com.herlandio7creditassessorservice.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditServiceAppraiser {

    private final ClientResourceClient clients;
    private final CardResourceClient cards;

    public CustomerSituation getCustomerSituation(String cpf) {
        // Realiza consulta no microserviço de cartões e clientes
        ResponseEntity<DataClient> dataClientResponse = clients.dataClient(cpf);
        ResponseEntity<List<CardClient>> cardByClientResponse = cards.getCardByClient(cpf);

        return CustomerSituation
                .builder()
                .client(dataClientResponse.getBody())
                .cards(cardByClientResponse.getBody())
                .build();
    }
}
