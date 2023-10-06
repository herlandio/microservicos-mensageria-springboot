package br.com.herlandio7cardservice.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component // gerenciado pelo spring
public class IssuingCardsSubscriber {

    @RabbitListener(queues = "${mq.queues.issuing-cards}") // referenciada no application.yml
    public void receiveIssueRequest(@Payload String payload) {
        System.out.println(payload);
    }
}
