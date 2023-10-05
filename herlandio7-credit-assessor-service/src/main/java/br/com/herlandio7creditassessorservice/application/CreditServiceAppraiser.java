package br.com.herlandio7creditassessorservice.application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.herlandio7creditassessorservice.domain.model.CustomerSituation;
import br.com.herlandio7creditassessorservice.domain.model.DataClient;
import br.com.herlandio7creditassessorservice.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditServiceAppraiser {

    private final ClientResourceClient clients;

    public CustomerSituation getCustomerSituation(String cpf) {
        // Realiza consulta no microserviço de cartões e clientes
        ResponseEntity<DataClient> dataClientResponse = clients.dataClient(cpf);
        return CustomerSituation
                .builder()
                .client(dataClientResponse.getBody())
                .build();
    }
}
