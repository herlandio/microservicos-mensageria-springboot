package br.com.herlandio7creditassessorservice.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.herlandio7creditassessorservice.domain.model.CardIssuanceRequest;
import lombok.RequiredArgsConstructor;

@Component // gerenciado pelo spring
@RequiredArgsConstructor
public class IssuingCardsPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueIssuingCards;

    /**
     * The function sends a JSON representation of a CardIssuanceRequest object to a
     * RabbitMQ queue.
     * 
     * @param data The parameter "data" is an object of type "CardIssuanceRequest".
     */
    public void requestCard(CardIssuanceRequest data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueIssuingCards.getName(), json);
    }

    /**
     * The function converts a CardIssuanceRequest object into a JSON string using
     * the ObjectMapper
     * class.
     * 
     * @param data The parameter "data" is of type CardIssuanceRequest, which is an
     *             object containing
     *             information about a card issuance request.
     * @return The method is returning a JSON string representation of the
     *         CardIssuanceRequest object.
     */
    private String convertIntoJson(CardIssuanceRequest data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(data);
    }
}
