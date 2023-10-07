package br.com.herlandio7creditassessorservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.issuing-cards}")
    private String queueCardissuance;

    @Bean
    public Queue queueIssuingCards() {
        return new Queue(queueCardissuance, true);
    }
}
