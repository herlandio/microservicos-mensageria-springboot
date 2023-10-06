package br.com.herlandio7cardservice.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.herlandio7cardservice.domain.Card;
import br.com.herlandio7cardservice.domain.CardClient;
import br.com.herlandio7cardservice.domain.CardIssuanceRequest;
import br.com.herlandio7cardservice.infra.repository.CardClientRepository;
import br.com.herlandio7cardservice.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;

@Component // gerenciado pelo spring
@RequiredArgsConstructor
public class IssuingCardsSubscriber {

    private final CardRepository cardRepository;
    private final CardClientRepository cardClientRepository;

    @RabbitListener(queues = "${mq.queues.issuing-cards}") // referenciada no application.yml
    public void receiveIssueRequest(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            CardIssuanceRequest cardIssuanceRequest = mapper.readValue(payload, CardIssuanceRequest.class);
            Card card = cardRepository.findById(cardIssuanceRequest.getId()).orElseThrow();
            CardClient client = new CardClient();
            client.setCard(card);
            client.setCpf(cardIssuanceRequest.getCpf());
            client.setLimitReleased(cardIssuanceRequest.getLimitReleased());
            cardClientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
