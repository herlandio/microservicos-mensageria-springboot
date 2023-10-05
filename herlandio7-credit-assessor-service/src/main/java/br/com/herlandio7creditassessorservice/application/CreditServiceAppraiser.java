package br.com.herlandio7creditassessorservice.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.herlandio7creditassessorservice.application.exception.DataClientNotFoundException;
import br.com.herlandio7creditassessorservice.application.exception.ErrorComunicationMicroservicesException;
import br.com.herlandio7creditassessorservice.domain.model.ApprovedCard;
import br.com.herlandio7creditassessorservice.domain.model.Card;
import br.com.herlandio7creditassessorservice.domain.model.CardClient;
import br.com.herlandio7creditassessorservice.domain.model.CustomerSituation;
import br.com.herlandio7creditassessorservice.domain.model.DataClient;
import br.com.herlandio7creditassessorservice.domain.model.FeedbackCustomerEvaluation;
import br.com.herlandio7creditassessorservice.infra.clients.CardResourceClient;
import br.com.herlandio7creditassessorservice.infra.clients.ClientResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditServiceAppraiser {

    private final ClientResourceClient clients;
    private final CardResourceClient cards;

    public CustomerSituation getCustomerSituation(String cpf)
            throws DataClientNotFoundException, ErrorComunicationMicroservicesException {
        // Realiza consulta no microserviço de cartões e clientes
        try {
            ResponseEntity<DataClient> dataClientResponse = clients.dataClient(cpf);
            ResponseEntity<List<CardClient>> cardByClientResponse = cards.getCardByClient(cpf);

            return CustomerSituation
                    .builder()
                    .client(dataClientResponse.getBody())
                    .cards(cardByClientResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DataClientNotFoundException();
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }
    }

    public FeedbackCustomerEvaluation makeEvaluation(String cpf, Long income)
            throws DataClientNotFoundException, ErrorComunicationMicroservicesException {
        try {
            ResponseEntity<DataClient> dataClientResponse = clients.dataClient(cpf);
            ResponseEntity<List<Card>> cardByClientResponse = cards.getIncomeCardUpTo(income);

            List<Card> cards = cardByClientResponse.getBody();
            // mapeia dados
            var listApprovedCards = cards.stream().map(card -> {

                DataClient dataClient = dataClientResponse.getBody();

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageBD = BigDecimal.valueOf(dataClient.getAge());

                var divide = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = divide.multiply(basicLimit);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setFlag(card.getFlag());
                approvedCard.setApprovedLimit(approvedLimit);

                return approvedCard;
            }).collect(Collectors.toList()); // retorna uma lista de cartoes

            return new FeedbackCustomerEvaluation(listApprovedCards);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DataClientNotFoundException();
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }
    }
}
